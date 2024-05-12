package com.web.hanu88.user_auth.service;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_auth.model.Account;
import com.web.hanu88.user_auth.model.Role;
import com.web.hanu88.user_auth.model.Session;
import com.web.hanu88.user_auth.repository.AccountRepository;
import com.web.hanu88.user_auth.repository.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class LoginService {
    private final AccountRepository accountRepository;
    private final SessionRepository sessionRepository;
    private final Environment env;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {
        public String username;
        public String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Output {
        public String accessToken;
        public long accountId;
        public Role role;
    }

    public Result<?> login(Input input) {
        Account account = this.accountRepository.findByUsername(input.username);
        if (account == null) {
            return Result.failed("This account not found");
        }
        if(!account.validatePassword(input.password)) {
            return Result.failed("Wrong password");
        }
        Session session = new Session(account.getId(), account.getRole(), Instant.now().plus(2,  ChronoUnit.HOURS));
        String accessToken = session.genAccessToken(env.getProperty("auth.secret"));
        this.sessionRepository.save(session);
        return Result.success(new Output(accessToken, account.getId(), account.getRole()));
    }
}
