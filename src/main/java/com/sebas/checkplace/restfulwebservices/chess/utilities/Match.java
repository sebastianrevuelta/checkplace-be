package com.sebas.checkplace.restfulwebservices.chess.utilities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {

    @Id
    private Long match_id;

    private String white_player;
    private String black_player;
    private String result;
    private String match;
    private int year;

    protected Match(Long id, String player1, String player2, String res, String match, int year) {
        this.match_id = id;
        this.white_player = player1;
        this.black_player = player2;
        this.result = res;
        this.year = year;
        this.match = match;
    }
}
