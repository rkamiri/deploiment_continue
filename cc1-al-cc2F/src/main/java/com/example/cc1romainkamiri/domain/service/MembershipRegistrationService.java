package com.example.cc1romainkamiri.domain.service;

import java.time.Duration;
import java.time.LocalDate;

import com.example.cc1romainkamiri.domain.entity.*;
import com.example.cc1romainkamiri.infrastructure.exceptions.*;

public class MembershipRegistrationService {

    private final UserService userService;
    private final MembershipService membershipService;
    private final PaymentInfosService paymentInfosService;
    private final PaymentService paymentService;

    public MembershipRegistrationService(UserService userService, MembershipService membershipService, PaymentInfosService paymentInfosService, PaymentService paymentService) {
        this.userService = userService;
        this.membershipService = membershipService;
        this.paymentInfosService = paymentInfosService;
        this.paymentService = paymentService;
    }

    private Membership apply(MembershipRegistration registration, Id userId, boolean firstRegister) {
        LocalDate registrationDate = LocalDate.now();
        if (pay(registration.getPaymentInfos(), registration.getMembershipInfos().getAmount())) {
            if (firstRegister) {
                paymentInfosService.addPaymentInformation(registration.getPaymentInfos());
            }
            Membership newMembership = Membership.of(membershipService.getNextId(), userId, registration.getMembershipInfos().getAmount(), registrationDate, getRegistrationEndDate(registrationDate, registration.getMembershipInfos().getDurationTime()), true);
            Payment.of(paymentService.getNextId(), userId, registration.getMembershipInfos().getAmount(), registrationDate);
            return this.membershipService.addMembership(newMembership);
        } else {
            throw PaymentException.error();
        }
    }

    public Membership registerForMembership(MembershipRegistration registration) {
        correctInfos(registration);
        User user = userService.getUser(Id.of(registration.getUserId())).get();
        if (!this.membershipService.isUserSubscribed(user.getId())) {
            return apply(registration, user.getId(), true);
        } else {
            throw IsAlreadyMemberException.withId(user.getId());
        }
    }

    public Membership renewMembership(Id userId) {
        if (this.membershipService.isUserSubscribed(userId)) {
            Membership lastMembershipByUserId = this.membershipService.getLastMembershipByUserId(userId);
            if (!this.membershipService.userAlreadyMember(userId)) {
                MembershipRegistration registration = new MembershipRegistration(
                        userId.getId(),
                        MembershipInfos.of(lastMembershipByUserId.getPrice(), getMembershipDuration(lastMembershipByUserId)),
                        paymentInfosService.getPaymentInformationByUserId(userId),
                        LocalDate.now());
                return apply(registration, userId, false);
            } else {
                throw IsAlreadyMemberException.withId(userId);
            }
        } else {
            throw UserNotMemberException.withId(userId);
        }
    }

    public Membership stopAutomaticRenew(Id userId) {
        if (this.membershipService.isUserSubscribed(userId)) {
            if (this.membershipService.userAlreadyMember(userId)) {
                Membership lastMembership = this.membershipService.getLastMembershipByUserId(userId);
                lastMembership.setAutoRenewal(false);
                this.membershipService.saveMembership(lastMembership);
                return this.membershipService.addMembership(lastMembership);
            } else {
                throw stopRenewalMembershipFinished.error();
            }
        } else {
            throw UserNotMemberException.withId(userId);
        }
    }

    private void correctInfos(MembershipRegistration registration) {
        int durationTime = registration.getMembershipInfos().getDurationTime();
        double amount = registration.getMembershipInfos().getAmount();
        if (durationTime < 1 || amount < 0) {
            throw IncorrectMembershipInfos.withInfo(durationTime < 1 ? "duration time" : "amount");
        }
    }

    private boolean pay(PaymentInfos paymentInfos, double amount) {
        return paymentService.pay(paymentInfos, amount);
    }

    public LocalDate getRegistrationEndDate(LocalDate date, int registrationTime) {
        if (registrationTime > 0)
            return date.plusDays(registrationTime);
        else
            return date.plusDays(30);
    }

    public int getMembershipDuration(Membership membership) {
        return (int) Duration.between(membership.getStartDateMembership().atStartOfDay(), membership.getEndDateMembership().atStartOfDay()).toDays();
    }

}
