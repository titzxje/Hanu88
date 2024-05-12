package com.web.hanu88.user_profile.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_profile")
@Getter
@NoArgsConstructor
public class UserProfile {
    @Id
    private long accountId;
    private String email;
    private String displayName;
    private long balancePoint;
    private String avatarUrl;
    private long roundPlayed;

    public static UserProfile create(long accountId, String email) {
        UserProfile userProfile = new UserProfile();
        userProfile.setAccountId(accountId);
        userProfile.setEmail(email);
        return userProfile;
    }

    public void updateProfile(String displayName, String avatarUrl) {
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
    }

    public void increaseBalance(long amount) {
        this.balancePoint += amount;
    }

    public void decreaseBalance(long amount) {
        if (this.balancePoint < amount) {
            throw new Error("Not have enough balance");
        }
        this.balancePoint -= amount;
    }

    public void updateRoundPlayed() {
        this.roundPlayed += 1;
    }

    private void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private void setEmail(String email) {
        this.email = email;
    }
}
