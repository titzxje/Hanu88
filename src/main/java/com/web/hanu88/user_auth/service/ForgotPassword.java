package com.web.hanu88.user_auth.service;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_auth.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ForgotPassword {
    private final AccountRepository accountRepository;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String username;
        public String email;
    }

    public Result<?> forgotPassword(Input input) {
        return Result.success("");
    }
}
