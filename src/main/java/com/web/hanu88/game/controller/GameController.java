package com.web.hanu88.game.controller;

import com.web.hanu88.game.service.PlayService;
import com.web.hanu88.game.service.StatisticService;
import com.web.hanu88.share.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    @Autowired
    private PlayService playService;
    @Autowired
    private StatisticService statisticService;

    @PostMapping(value = "/bet")
    public Result<?> bet(PlayService.Input input) throws Exception {
        return playService.bet(input);
    }
}
