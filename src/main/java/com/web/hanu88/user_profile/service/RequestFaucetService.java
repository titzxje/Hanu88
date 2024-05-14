package com.web.hanu88.user_profile.service;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_profile.model.UserProfile;
import com.web.hanu88.user_profile.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@AllArgsConstructor
public class RequestFaucetService {
    private final UserProfileRepository userProfileRepository;
    private Environment env;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public long accountId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public long currentBalance;
    }

    public Result<?> requestFaucet(Input input) {
        UserProfile userProfile = this.userProfileRepository.findByAccountId(input.accountId);
        if (userProfile == null) {
            return Result.failed("Not found user to faucet");
        };
        if (!canRequestFaucet(userProfile)) {
            return Result.failed("Request faucet still on cooldown");
        }
        doFaucet(userProfile);
        return Result.success(new Output(userProfile.getBalancePoint()));
    }

    private boolean canRequestFaucet(UserProfile userProfile) {
        Date currentDate = new Date();
        if (userProfile.getLastFaucet() == null) {
            return true;
        }
        Date lastFaucetPLus24Hrs = new Date(userProfile.getLastFaucet().getTime() + (24 * 60 * 60 * 1000));
        return currentDate.after(lastFaucetPLus24Hrs);
    }

    private void doFaucet(UserProfile userProfile) {
        long faucetAmount = Long.parseLong(env.getProperty("faucet.amount"));
        userProfile.increaseBalance(faucetAmount);
    }
}
