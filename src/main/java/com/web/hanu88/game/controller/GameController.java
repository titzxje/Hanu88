package com.web.hanu88.game.controller;

import com.web.hanu88.game.service.PlayService;
import com.web.hanu88.game.service.StatisticService;
import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_auth.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GameController {
    @Autowired
    private PlayService playService;
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private Environment env;

    @PostMapping(value = "/bet")
    public Result<?> bet(@RequestHeader("access-token") String accessToken, @RequestBody PlayService.Input input) throws Exception {
        Map<String, Object> userData = Session.decodeAccessToken(accessToken, env.getProperty("auth.secret"));
        PlayService.Input input1 = new PlayService.Input(Long.parseLong(userData.get("accountId").toString()),input.typeBet,input.amount);
        return playService.bet(input1);
    }
}
