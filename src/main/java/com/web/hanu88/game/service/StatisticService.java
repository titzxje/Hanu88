package com.web.hanu88.game.service;

import com.web.hanu88.game.repository.PlayHistoryRepository;
import com.web.hanu88.share.entity.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticService {
    private final PlayHistoryRepository playHistoryRepository;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output {
        public long totalPlayer;
        public long totalGamePlayed;
        public long totalRewardToday;
    }

    public Result<?> statistic() {

        return Result.success("");
    }
}
