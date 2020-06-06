package com.discover.app.service;

import com.discover.app.model.AccountSummary;
import com.discover.app.model.BankAccount;
import com.discover.app.model.MoneyAccount;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    protected Flux<BankAccount> bankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAvailableBalance("234.33");
        bankAccount.setCurrentBalance("234.33");
        bankAccount.setVerified(true);
        bankAccount.setName("Jack");

        BankAccount bankAccount1 = new BankAccount();
        bankAccount1.setAvailableBalance("204.33");
        bankAccount1.setCurrentBalance("224.33");
        bankAccount1.setVerified(true);
        bankAccount1.setName("Jim");

        BankAccount bankAccount2 = new BankAccount();
        bankAccount2.setAvailableBalance("65.33");
        bankAccount2.setCurrentBalance("124.33");
        bankAccount2.setVerified(false);
        bankAccount2.setName("Mike");

        BankAccount bankAccount3 = new BankAccount();
        bankAccount3.setAvailableBalance("65.33");
        bankAccount3.setCurrentBalance("124.33");
        bankAccount3.setVerified(false);
        bankAccount3.setName("Savoy");

        List<BankAccount> bankAccountList = new ArrayList<>();
        bankAccountList.add(bankAccount);
        bankAccountList.add(bankAccount1);
        bankAccountList.add(bankAccount2);
        bankAccountList.add(bankAccount3);
        return Flux.fromIterable(bankAccountList);
    }

    public Flux<MoneyAccount> monoAccount() {

        return bankAccount().filter(BankAccount::getVerified).map(this::checkAccount).log();
    };

    private MoneyAccount checkAccount(BankAccount acct) {

        MoneyAccount moneyAccount = null;

        // for (BankAccount account : acct) {
        // log.info("getiing name: {}", account.getName());
        if (!StringUtils.isEmpty(acct)) {
            moneyAccount =
                    MoneyAccount.builder()
                            .accountBalance(acct.getAvailableBalance())
                            .name(acct.getName())
                            .build();
        }
        // }

        return moneyAccount;
    }

    public AccountSummary getAccountSummary() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAvailableBalance("234.33");
        bankAccount.setCurrentBalance("234.33");
        bankAccount.setVerified(true);
        List<BankAccount> bankAccountList = new ArrayList<>();
        bankAccountList.add(bankAccount);
        BankAccount bankAccount1 =
                BankAccount.builder()
                        .availableBalance("345.43")
                        .currentBalance("345.67")
                        .verified(true)
                        .build();
        bankAccountList.add(bankAccount1);
        BankAccount bankAccount2 =
                BankAccount.builder()
                        .availableBalance("230.03")
                        .currentBalance("125.71")
                        .verified(false)
                        .build();
        bankAccountList.add(bankAccount2);
        AccountSummary accountSummary = new AccountSummary();
        accountSummary.setBankAccountList(
                bankAccountList.stream()
                        .filter(BankAccount::getVerified)
                        .collect(Collectors.toList()));
        accountSummary.setName("James");
        accountSummary.setPartyID("2344");
        List<String> bal =
                bankAccountList.stream()
                        .filter(BankAccount::getVerified)
                        .map(BankAccount::getCurrentBalance)
                        .sorted()
                        .collect(Collectors.toList());
        log.info("Current balance:{}", bal);

        return accountSummary;

        //        .filter(bankAccount3 ->{
        //            return bankAccount3.getVerified().booleanValue() == Boolean.valueOf(true);
        //        } )
    }

    public Mono<AccountSummary> accountSummary2() {

        // Flux<BankAccount>
        // bankAccountFlux=Flux.fromIterable(bankAccount()).filter(BankAccount::getVerified);
        AccountSummary accountSummary = new AccountSummary();
        // accountSummary.setBankAccountList(bankAccountFlux);
        accountSummary.setName("James");
        accountSummary.setPartyID("2344");

        // Mono<List<BankAccount>> bankAccountMono = Mono.just(bankAccount());

        return null;
    }
}
