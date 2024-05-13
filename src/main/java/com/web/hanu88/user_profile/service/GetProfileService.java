package com.web.hanu88.user_profile.service;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_profile.model.UserProfile;
import com.web.hanu88.user_profile.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetProfileService {
    private final UserProfileRepository userProfileRepository;

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
        private String email;
        private String displayName;
        private long balancePoint;
        private String avatarUrl;
        private long roundPlayed;
    }

    public Result<?> getUserProfile(Input input) {
        UserProfile userProfile = this.userProfileRepository.findByAccountId(input.accountId);
        if (userProfile == null) {
            return Result.failed("Not found this profile");
        }
        return Result.success(new Output(
                userProfile.getEmail(),
                userProfile.getDisplayName(),
                userProfile.getBalancePoint(),
                userProfile.getAvatarUrl(),
                userProfile.getRoundPlayed()
                )
        );
    }
}
