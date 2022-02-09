package com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation;

import com.example.cc1romainkamiri.domain.entity.Membership;
import com.example.cc1romainkamiri.domain.entity.PaymentInfos;
import com.example.cc1romainkamiri.domain.entity.Id;
import com.example.cc1romainkamiri.domain.repository.PaymentInformationRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class InMemoryPaymentInformationRepository implements PaymentInformationRepository {

    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<Id, PaymentInfos> paymentInfosDbMimic = new ConcurrentHashMap<>();


    @Override
    public Id getNextId() {
        return Id.of(count.incrementAndGet());
    }


    @Override
    public PaymentInfos getPaymentInformationById(com.example.cc1romainkamiri.domain.entity.Id id) {
        return paymentInfosDbMimic.get(id);
    }

    @Override
    public PaymentInfos savePaymentInformation(PaymentInfos paymentInfos) {
        paymentInfosDbMimic.put(paymentInfos.getId(), paymentInfos);
        return paymentInfos;
    }

    @Override
    public PaymentInfos getPaymentInformationByUserId(com.example.cc1romainkamiri.domain.entity.Id userId) {
        List<PaymentInfos> paymentInfos = paymentInfosDbMimic.values().stream().filter(membership -> membership.getId().getId() == (userId.getId())).collect(Collectors.toList());
        return paymentInfos.size() > 0 ? paymentInfos.get(paymentInfos.size() - 1) : null;
    }
}
