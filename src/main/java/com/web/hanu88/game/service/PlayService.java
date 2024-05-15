package com.web.hanu88.game.service;

import com.web.hanu88.game.model.PlayHistory;
import com.web.hanu88.game.model.TypeBet;
import com.web.hanu88.game.repository.PlayHistoryRepository;
import com.web.hanu88.market.model.Effect;
import com.web.hanu88.market.model.Order;
import com.web.hanu88.market.model.UserOrder;
import com.web.hanu88.market.repository.OrderRepository;
import com.web.hanu88.market.repository.UserOrderRepository;
import com.web.hanu88.share.entity.Result;
import com.web.hanu88.user_profile.model.UserProfile;
import com.web.hanu88.user_profile.repository.UserProfileRepository;

import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayService {
    private final PlayHistoryRepository playHistoryRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserOrderRepository userOrderRepository;
    private final OrderRepository orderRepository;
    private final Environment env;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Input {
        public long accountId;
        public TypeBet typeBet;
        public long amount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Output{
        public int[] result;
        public long rewardAmount;
        public long currentBalance;
        public boolean isWinning;
    }

    public Result<?> bet(Input input) throws Exception {
        try {
            UserProfile userProfile = this.userProfileRepository.findByAccountId(input.accountId);
            List<UserOrder> userOrders = this.userOrderRepository.findAllByAccountId(input.accountId);
            long extra = 1;
            if (!userOrders.isEmpty()) {
                for (UserOrder userOrder : userOrders) {
                    Order order = this.orderRepository.findById(userOrder.getOrderId());
                    if(order.getEffect() == Effect.X2) {
                        extra *= 2;
                    }
                    if(order.getEffect() == Effect.X4) {
                        extra *= 4;
                    }
                }
            }
            userProfile.decreaseBalance(input.amount);
            long roundNumber = userProfile.getRoundPlayed() + 1;
            int[] dices = getResult();
            int result = 0;
            for(int dice : dices) {
                result += dice;
            }
            boolean isWinning = false;
            long rewardAmount = 0;
            if ((input.typeBet == TypeBet.UNDER && result <= 6) || (input.typeBet == TypeBet.OVER && result >= 8)) {
                rewardAmount = input.amount * Long.parseLong(env.getProperty("reward.normal.ratio")) * extra;
                isWinning = true;
            } else if (input.typeBet == TypeBet.LUCKY && result == 7) {
                rewardAmount = input.amount * Long.parseLong(env.getProperty("reward.lucky.ratio")) * extra;
                isWinning = true;
            }
            userProfile.increaseBalance(rewardAmount);
            userProfile.updateRoundPlayed();
            PlayHistory playHistory = PlayHistory.create(input.accountId, roundNumber, input.typeBet, input.amount, result);
            this.userProfileRepository.save(userProfile);
            this.playHistoryRepository.save(playHistory);
            return Result.success(new Output(dices, rewardAmount, userProfile.getBalancePoint(), isWinning));
        } catch (Exception e) {
            return Result.failed(e);
        }
    }

    private int[] getResult() {
        Random random = new Random();
        int firstDice = random.nextInt(1, 7);
        int secondDice = random.nextInt(1, 7);
        return new int[]{firstDice, secondDice};
    }
}
