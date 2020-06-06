package com.discover.app.service;

import com.discover.app.model.AccountSummary;
import com.discover.app.model.MoneyAccount;
import reactor.core.publisher.Flux;

public interface AccountService {

    AccountSummary getAccountSummary();

    Flux<MoneyAccount> monoAccount();
}
