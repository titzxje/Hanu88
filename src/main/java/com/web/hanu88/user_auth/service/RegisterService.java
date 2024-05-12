package com.web.hanu88.user_auth.service;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_auth.model.Account;
import com.web.hanu88.user_auth.model.Role;
import com.web.hanu88.user_auth.repository.AccountRepository;
import com.web.hanu88.user_profile.model.UserProfile;
import com.web.hanu88.user_profile.repository.UserProfileRepository;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {
    private final AccountRepository accountRepository;
    private final UserProfileRepository userProfileRepository;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String username;
        public String email;
        public String password;
        public Role role;
    }

    public Result<?> register(Input input) {
        Account account = accountRepository.findByUsername(input.username);
        if (account != null) {
            return Result.failed("This username was already exist");
        }
        if (!validatePassword(input.password)) {
            return Result.failed("Invalid password");
        }
        String encryptedPassword = Account.encryptPassword(input.password);
        Account newAccount = new Account(input.username, input.password, input.role);
        UserProfile userProfile = UserProfile.create(newAccount.getId(), input.email);
        this.accountRepository.save(newAccount);
        this.userProfileRepository.save(userProfile);
        return Result.success(null);
    }

    private boolean validatePassword(String password) {
        return password.length() >= 8;
    }
}