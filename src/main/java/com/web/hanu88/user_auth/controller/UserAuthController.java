package com.web.hanu88.user_auth.controller;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_auth.model.Role;
import com.web.hanu88.user_auth.model.Session;
import com.web.hanu88.user_auth.service.LoginService;
import com.web.hanu88.user_auth.service.RegisterService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class UserAuthController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private Environment env;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public String username;
        public String email;
        public String password;
    }

    @PostMapping(value = "/login")
    public Result<?> login(@RequestBody LoginService.Input loginInput){
            return loginService.login(loginInput);
    }

    @PostMapping(value = "/register")
    public Result<?> register(@RequestBody Input input){
        RegisterService.Input input1 = new RegisterService.Input(input.username,input.email,input.password, Role.PLAYER);
        return registerService.register(input1);
    }
    @PostMapping(value = "register/admin")
    public Result<?> registerAdmin(@RequestHeader("access-token") String accessToken, @RequestBody Input input) {
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, env.getProperty("auth.secret"));
        if (userData.get("Role") == "ADMIN"){
            RegisterService.Input input1 = new RegisterService.Input(input.username,input.email,input.password, Role.ADMIN);
            return registerService.register(input1);
        }
        return Result.failed("Only admin can call this api");
    }
}
