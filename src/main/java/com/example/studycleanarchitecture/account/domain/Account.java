package com.example.studycleanarchitecture.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    private final AccountId accountId;
    private final Money baselineBalance;
    private final ActivityWindow activityWindow;

    @Value
    public static class AccountId {
        private Long value;
    }
}
