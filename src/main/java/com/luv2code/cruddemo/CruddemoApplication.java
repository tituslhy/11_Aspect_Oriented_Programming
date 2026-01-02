package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AccountDAO;
import com.luv2code.cruddemo.dao.MembershipDAO;
import com.luv2code.cruddemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(
            AccountDAO theAccountDAO, MembershipDAO theMembershipDAO, TrafficFortuneService theTrafficFortuneService
    ){
        return runner -> {
//            demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
//            demoTheAfterReturningAdvice(theAccountDAO);
//            demoTheAfterThrowingAdvice(theAccountDAO);
//            demoTheAfterAdvice(theAccountDAO);
            demoTheAroundAdvice(theTrafficFortuneService);
        };
    }

    private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("\nMain program: demoTheAroundAdvice");
        System.out.println("Calling getFortune()");
        String data = theTrafficFortuneService.getFortune();
        System.out.println("\nMy traffic fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
        List<Account> accounts =  null;

        try{
            boolean tripWire = false;
            accounts = theAccountDAO.findAccounts(tripWire);
        }
        catch (Exception exc){
            System.out.println("\n\nMain Program: ... caught exception: " +  exc) ;
        }

        System.out.println("----");
        System.out.println(accounts);
        System.out.println("\n");
    }

    private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
        List<Account> accounts =  null;

        try{
            boolean tripWire = true;
            accounts = theAccountDAO.findAccounts(tripWire);
        }
        catch (Exception exc){
            System.out.println("\n\nMain Program: ... caught exception: " +  exc) ;
        }

        System.out.println("----");
        System.out.println(accounts);
        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
        List<Account> accounts =  theAccountDAO.findAccounts();
        System.out.println("\n\nMain Program: demoTheAfterReturningAdice");
        System.out.println("----");
        System.out.println(accounts);
        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

        // Create new account
        Account theAccount = new Account();
        theAccount.setName("Madhu");
        theAccount.setLevel("Platinum");

        // call the business method
        theAccountDAO.addAccount(theAccount, true);
        theAccountDAO.doWork();
        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        String name = theAccountDAO.getName();
        String code = theAccountDAO.getServiceCode();

        theMembershipDAO.addAccount();
        theMembershipDAO.goToSleep();

    }

}
