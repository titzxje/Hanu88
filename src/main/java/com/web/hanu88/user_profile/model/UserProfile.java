package com.web.hanu88.user_profile.model;

import com.web.hanu88.share.entity.AbstractEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "user_profile")
@Getter
@NoArgsConstructor
public class UserProfile extends AbstractEntity {
    @Id
    private long accountId;
    private String email;
    private String displayName;
    private long balancePoint;
    private String avatarUrl;
    private long roundPlayed;
    private Date lastFaucet;

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

    public void decreaseBalance(long amount) throws Exception {
        if (this.balancePoint < amount) {
            throw new Exception("Not have enough balance");
        }
        this.balancePoint -= amount;
    }

    public void updateRoundPlayed() {
        this.roundPlayed += 1;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private void setEmail(String email) {
        this.email = email;
    }
}
