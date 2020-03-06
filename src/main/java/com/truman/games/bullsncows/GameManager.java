package com.truman.games.bullsncows;

import com.truman.games.bullsncows.exceptions.GamesCountExceeded;
import com.truman.games.bullsncows.models.StatusDetails;

import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameManager {
    private static final int MAX_GAMES = 10;
    private static final long LIFECYCLE_IDLE = 30_000;
    private static GameManager gameManager = new GameManager();
    private final ConcurrentHashMap<String, Game> db;

    private GameManager() {
        db = new ConcurrentHashMap<>();
    }

    public static GameManager instance() {
        return gameManager;
    }

    public ConcurrentHashMap<String, Game> getDb() {
        return db;
    }

    public String createGame() throws GamesCountExceeded {
        return createGame(null);
    }

    public String createGame(File answerFile) throws GamesCountExceeded {
        if (gameManager.db.size() >= MAX_GAMES) {
            gc();
            if (gameManager.db.size() >= MAX_GAMES) {
                throw new GamesCountExceeded(String.format("Games Count Exceeded, please wait at least %dms for idle games to be removed.", findMinWaitingMs()));
            }
        }
        String id;
        do {
            id = UUID.randomUUID().toString().substring(0, 4);
        } while (db.containsKey(id));

        Game game;
        if (answerFile != null) {
            game = new Game(answerFile);
        } else {
            game = new Game();
        }
        db.put(id, game);
        return id;
    }


    public Game getNewGame() throws GamesCountExceeded {
        return getNewGame(null);
    }

    public Game getNewGame(File answerFile) throws GamesCountExceeded {
        return gameManager.getGame(createGame(answerFile));
    }

    public Game getGame(String id) {
        return this.db.get(id);
    }

    private void gc() {
        db.forEach((key, game) -> {
            if (calcRemainingLife(game) == 0) {
                db.remove(key);
            }
        });
    }

    private long findMinWaitingMs() {
        return db.values().stream().map(this::calcRemainingLife).min(Long::compareTo).orElse(-1L);
    }

    private long calcLifeCycle(Game game) {
        StatusDetails statusDetails = game.getStatusDetails();
        long lifeCycle = LIFECYCLE_IDLE;
        switch (statusDetails.getStatus()) {
            case Preparing:
            case Ready:
                break;
            case Ongoing:
                lifeCycle = LIFECYCLE_IDLE * 5;
                break;
            case Ended:
                if (!game.isResultRetrieved()) {
                    lifeCycle = LIFECYCLE_IDLE * 5;
                }
        }
        return lifeCycle;
    }

    private long calcRemainingLife(Game game) {
        long lifeSpan = new Date().getTime() - game.getStatusDetails().getLastActiveTime();
        long lifeCycle = calcLifeCycle(game);
        return lifeSpan > lifeCycle ? 0 : lifeCycle - lifeSpan;
    }


}
