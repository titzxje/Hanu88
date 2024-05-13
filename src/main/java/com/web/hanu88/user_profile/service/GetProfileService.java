package com.web.hanu88.user_profile.service;

import com.web.hanu88.user_profile.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetProfileService {
    private final UserProfileRepository userProfileRepository;


}
