package ecommercGesture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import ecommercGesture.application.memberQueriesCommandsEvents.commands.MembershipApply;
import ecommercGesture.application.memberQueriesCommandsEvents.commands.MembershipApplyCommandHandler;
import ecommercGesture.application.memberQueriesCommandsEvents.commands.MembershipRenew;
import ecommercGesture.application.memberQueriesCommandsEvents.commands.MembershipRenewCommandHandler;
import ecommercGesture.application.memberQueriesCommandsEvents.commands.StopAutomaticRenew;
import ecommercGesture.application.memberQueriesCommandsEvents.commands.StopAutomaticRenewCommandHandler;
import ecommercGesture.application.memberQueriesCommandsEvents.queries.RetrieveMembershipById;
import ecommercGesture.application.memberQueriesCommandsEvents.queries.RetrieveMembershipByIdHandler;
import ecommercGesture.application.memberQueriesCommandsEvents.queries.RetrieveMemberships;
import ecommercGesture.application.memberQueriesCommandsEvents.queries.RetrieveMembershipsHandler;
import ecommercGesture.application.userQueriesCommandsEvents.commands.CreateUser;
import ecommercGesture.application.userQueriesCommandsEvents.commands.CreateUserCommandHandler;
import ecommercGesture.application.userQueriesCommandsEvents.commands.ModifyUserPassword;
import ecommercGesture.application.userQueriesCommandsEvents.commands.ModifyUserPasswordCommandHandler;
import ecommercGesture.application.userQueriesCommandsEvents.commands.ModifyUserUserName;
import ecommercGesture.application.userQueriesCommandsEvents.commands.ModifyUserUserNameCommandHandler;
import ecommercGesture.application.userQueriesCommandsEvents.events.CreateUserEvent;
import ecommercGesture.application.userQueriesCommandsEvents.events.CreateUserEventListener;
import ecommercGesture.application.userQueriesCommandsEvents.queries.RetrieveUserById;
import ecommercGesture.application.userQueriesCommandsEvents.queries.RetrieveUserByIdHandler;
import ecommercGesture.application.userQueriesCommandsEvents.queries.RetrieveUsers;
import ecommercGesture.application.userQueriesCommandsEvents.queries.RetrieveUsersHandler;
import ecommercGesture.domain.services.BillingInformationService;
import ecommercGesture.domain.services.ExternalPaymentService;
import ecommercGesture.domain.services.GlobalPaymentService;
import ecommercGesture.domain.services.MembershipService;
import ecommercGesture.domain.services.MembershipApplicationService;
import ecommercGesture.domain.services.PaymentService;
import ecommercGesture.domain.services.UserService;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryBillingInformationRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryMembershipRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryPaymentRepository;
import ecommercGesture.infrastructure.defaultRepositoryImplementation.InMemoryUserRepository;
import kernel.Command;
import kernel.CommandBus;
import kernel.CommandHandler;
import kernel.DefaultEventDispatcher;
import kernel.Event;
import kernel.EventDispatcher;
import kernel.EventListener;
import kernel.Query;
import kernel.QueryBus;
import kernel.QueryHandler;
import kernel.SimpleCommandBus;
import kernel.SimpleQueryBus;

@Configuration
@EnableScheduling
public class UserConfiguration {
	
    @Bean
    public UserService userService() {
        return new UserService(new InMemoryUserRepository());
    }
    
    @Bean
    public MembershipService memberService() {
        return new MembershipService(new InMemoryMembershipRepository());
    }
    
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(new InMemoryPaymentRepository());
    }
    
    @Bean
    public ExternalPaymentService externalPaymentService() {
        return new ExternalPaymentService();
    }
    
    @Bean
    public BillingInformationService billingInformationService() {
        return new BillingInformationService(new InMemoryBillingInformationRepository());
    }
    
    @Bean
    public GlobalPaymentService globalPaymentService() {
        return new GlobalPaymentService(paymentService(),externalPaymentService());
    }
    
    @Bean
    public MembershipApplicationService membershipApplicationService() {
        return new MembershipApplicationService(userService(),memberService(),globalPaymentService(), billingInformationService());
    }
    
    @Bean
    public EventDispatcher<Event> eventEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(CreateUserEvent.class, List.of(new CreateUserEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }  

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateUser.class, new CreateUserCommandHandler(userService(),eventEventDispatcher()));
        commandHandlerMap.put(ModifyUserUserName.class, new ModifyUserUserNameCommandHandler(userService(),eventEventDispatcher()));
        commandHandlerMap.put(ModifyUserPassword.class, new ModifyUserPasswordCommandHandler(userService(),eventEventDispatcher()));
        commandHandlerMap.put(MembershipApply.class, new MembershipApplyCommandHandler(membershipApplicationService(),billingInformationService() ,eventEventDispatcher()));
        commandHandlerMap.put(MembershipRenew.class, new MembershipRenewCommandHandler(membershipApplicationService(),eventEventDispatcher()));
        commandHandlerMap.put(StopAutomaticRenew.class, new StopAutomaticRenewCommandHandler(membershipApplicationService(),eventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(RetrieveUserById.class, new RetrieveUserByIdHandler(userService()));
        queryHandlerMap.put(RetrieveUsers.class, new RetrieveUsersHandler(userService()));
        queryHandlerMap.put(RetrieveMembershipById.class, new RetrieveMembershipByIdHandler(memberService()));
        queryHandlerMap.put(RetrieveMemberships.class, new RetrieveMembershipsHandler(memberService()));
        return new SimpleQueryBus(queryHandlerMap);
    }
}
