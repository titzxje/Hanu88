package com.web.hanu88.user_auth.controller;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_auth.model.Role;
import com.web.hanu88.user_auth.model.Session;
import com.web.hanu88.user_auth.service.LoginService;
import com.web.hanu88.user_auth.service.RegisterService;
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

    public UserAuthController() {
    }

    @PostMapping(value = "/login")
    public Result<?> login(@RequestBody LoginService.Input loginInput){
            return loginService.login(loginInput);
    }

    @PostMapping(value = "/register")
    public Result<?> register(@RequestBody RegisterService.Input registerInput){
        RegisterService.Input input = new RegisterService.Input(registerInput.username,registerInput.email,registerInput.password, Role.PLAYER);
        return registerService.register(input);
    }
    @PostMapping(value = "register/admin")
    public Result<?> registerAdmin(@RequestHeader("access-token") String accessToken, @RequestBody RegisterService.Input registerInput) {
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, env.getProperty("auth.secret"));
        if (userData.get("Role") == "ADMIN"){
            RegisterService.Input input = new RegisterService.Input(registerInput.username,registerInput.email,registerInput.password, Role.ADMIN);
            return registerService.register(input);
        }
        return Result.failed("Only admin can call this api");
    }
}
