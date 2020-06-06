package com.discover.app.model;

import java.util.List;
import lombok.Data;

@Data
public class AccountSummary {

    private String name;
    private String partyID;
    private List<BankAccount> bankAccountList;
}
