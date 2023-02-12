package com.example.studycleanarchitecture.account.domain;

import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.*;

public class ActivityWindow {

    private List<Activity> activities;

    public ActivityWindow(@NonNull List<Activity> activities) {
        this.activities = activities;
    }

    public ActivityWindow(@NonNull Activity... activities) {
        this.activities = new ArrayList<>(Arrays.asList(activities));
    }


    public LocalDateTime getStartTimestamp() {

        return activities.stream()
                .min(Comparator.comparing(Activity::getTimstamp))
                .orElseThrow(IllegalStateException::new)
                .getTimstamp();

    }

    public LocalDateTime getEndTimestamp() {

        return activities.stream()
                .max(Comparator.comparing(Activity::getTimstamp))
                .orElseThrow(IllegalStateException::new)
                .getTimstamp();

    }

    public Money calculateBalance(Account.AccountId accountId) {

        Money depositBalance = activities.stream()
                .filter(activity -> activity.getTargetAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        Money withdrawalBalance = activities.stream()
                .filter(activity -> activity.getSourceAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        return Money.add(depositBalance, withdrawalBalance.negate());

    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

}
