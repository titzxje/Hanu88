package com.web.hanu88.game.model;

import com.web.hanu88.share.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "play_history")
@Getter
@NoArgsConstructor
public class PlayHistory extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long accountId;
    private long roundNumber;
    private TypeBet typeBet;
    private long amount;
    private int result;

    public static PlayHistory create(long accountId, long roundNumber, TypeBet typeBet, long amount, int result) {
        PlayHistory playHistory = new PlayHistory();
        playHistory.accountId = accountId;
        playHistory.roundNumber = roundNumber;
        playHistory.typeBet = typeBet;
        playHistory.amount = amount;
        playHistory.result = result;
        return playHistory;
    }

}
