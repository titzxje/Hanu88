package com.web.hanu88.user_profile.controller;

import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_profile.service.GetProfileService;
import com.web.hanu88.user_profile.service.UpdateProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {
    @Autowired
    private GetProfileService getProfileService;
    @Autowired
    private UpdateProfileService updateProfileService;

    @PostMapping(value = "/getProfile")
    public Result<?> getProfile(GetProfileService.Input input){
        return getProfileService.getUserProfile(input);
    }

    @PostMapping(value = "/updateProfile")
    public Result<?> updateProfile(UpdateProfileService.Input input){
        return updateProfileService.updateProfile(input);
    }
}
