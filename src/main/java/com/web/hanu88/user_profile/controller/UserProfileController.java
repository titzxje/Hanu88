package com.web.hanu88.user_profile.controller;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_auth.model.Session;
import com.web.hanu88.user_profile.service.GetProfileService;
import com.web.hanu88.user_profile.service.UpdateProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserProfileController {
    @Autowired
    private GetProfileService getProfileService;
    @Autowired
    private UpdateProfileService updateProfileService;
    @Autowired
    private Environment env;



    @PostMapping(value = "/getProfile")
    public Result<?> getProfile(@RequestHeader("access-token") String accessToken){
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, env.getProperty("auth.secret"));
        GetProfileService.Input input = new GetProfileService.Input(Long.parseLong(userData.get("accountId").toString()));
        return getProfileService.getUserProfile(input);
    }

    @PostMapping(value = "/updateProfile")
    public Result<?> updateProfile(@RequestHeader("access-token") String accessToken, @RequestBody UpdateProfileService.Input input){
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, env.getProperty("auth.secret"));
        UpdateProfileService.Input input1 = new UpdateProfileService.Input(Long.parseLong(userData.get("accountId").toString()),input.displayName,input.avatarUrl);
        return updateProfileService.updateProfile(input1);
    }
}
