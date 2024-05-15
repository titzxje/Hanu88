package com.web.hanu88.user_profile.service;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_profile.model.UserProfile;
import com.web.hanu88.user_profile.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateProfileService {
    private final UserProfileRepository userProfileRepository;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public long accountId;
        public String displayName;
        public String avatarUrl;

    }

    public Result<?> updateProfile(Input input) {
        UserProfile userProfile = this.userProfileRepository.findByAccountId(input.accountId);
        if (userProfile == null) {
            return Result.failed("Not found user profile to update");
        }
        if (input.displayName != null ) {
            userProfile.setDisplayName(input.displayName);
        }
        if (input.avatarUrl != null) {
            userProfile.setAvatarUrl(input.avatarUrl);
        }
        userProfileRepository.save(userProfile);
        return Result.success("Update Successfully");
    }
}
