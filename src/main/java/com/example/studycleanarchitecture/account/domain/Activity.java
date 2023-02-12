package com.example.studycleanarchitecture.account.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Getter
@RequiredArgsConstructor
public class Activity {

    private ActivityId id;
    @NonNull private final Account.AccountId ownerAccountId;
    @NonNull private final Account.AccountId sourceAccountId;
    @NonNull private final Account.AccountId targetAccountId;
    @NonNull private final LocalDateTime timstamp;
    @NonNull private final Money money;

    public Activity(
            @NonNull Account.AccountId ownerAccountId,
            @NonNull Account.AccountId sourceAccountId,
            @NonNull Account.AccountId targetAccountId,
            @NonNull LocalDateTime timstamp,
            @NonNull Money money
    ) {
        this.id = null;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timstamp = timstamp;
        this.money = money;
    }

    @Value
    public static class ActivityId {
        private final Long value;
    }

}
