package com.example.cc1romainkamiri;


import com.example.cc1romainkamiri.application.memberCQS.commands.*;
import com.example.cc1romainkamiri.application.memberCQS.queries.FindMembershipById;
import com.example.cc1romainkamiri.application.memberCQS.queries.FindMembershipByIdHandler;
import com.example.cc1romainkamiri.domain.service.*;
import com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation.InMemoryPaymentInformationRepository;
import com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation.InMemoryMembershipRepository;
import com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation.InMemoryPaymentRepository;
import com.example.cc1romainkamiri.infrastructure.defaultRepositoryImplementation.InMemoryUserRepository;
import com.example.cc1romainkamiri.kernel.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
public class ProjectConfiguration {
	
    @Bean
    public UserService userService() {
        return new UserService(new InMemoryUserRepository());
    }
    
    @Bean
    public MembershipService membershipService() {
        return new MembershipService(new InMemoryMembershipRepository());
    }
    
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(new InMemoryPaymentRepository(), new ExternalPaymentService());
    }

    @Bean
    public ExternalPaymentService externalPaymentService() {
        return new ExternalPaymentService();
    }
    
    @Bean
    public PaymentInfosService paymentInfosService() {
        return new PaymentInfosService(new InMemoryPaymentInformationRepository());
    }
    
    @Bean
    public MembershipRegistrationService membershipRegistrationService() {
        return new MembershipRegistrationService(userService(), membershipService(), paymentInfosService(), paymentService());
    }
    
    @Bean
    public EventDispatcher<Event> eventEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
/*
        listenerMap.put(CreateUserEvent.class, List.of(new CreateUserEventListener()));
*/
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(ApplyToMembership.class, new ApplyToMembershipCommandHandler(membershipRegistrationService(), paymentInfosService() ,eventEventDispatcher()));
        commandHandlerMap.put(RenewMembership.class, new RenewMembershipCommandHandler(membershipRegistrationService(),eventEventDispatcher()));
        commandHandlerMap.put(StopAutoRenew.class, new StopAutoRenewCommandHandler(membershipRegistrationService(),eventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(FindMembershipById.class, new FindMembershipByIdHandler(membershipService()));
        return new SimpleQueryBus(queryHandlerMap);
    }
}
