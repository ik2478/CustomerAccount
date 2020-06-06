package com.discover.app.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoneyAccount {

    private String name;
    private String accountBalance;
}
