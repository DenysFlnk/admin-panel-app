package com.game.util;

import com.game.entity.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PlayerValidationUtil {

    private static final Date BIRTHDAY_LOWER_BORDER;

    private static final Date BIRTHDAY_UPPER_BORDER;

    static {
        Calendar lowerBorder = Calendar.getInstance();
        lowerBorder.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        BIRTHDAY_LOWER_BORDER = lowerBorder.getTime();

        Calendar upperBorder = Calendar.getInstance();
        upperBorder.set(3000, Calendar.DECEMBER, 31, 23, 59, 59);
        BIRTHDAY_UPPER_BORDER = upperBorder.getTime();
    }

    private PlayerValidationUtil() {
    }

    public static long validateAndGetId(String id) {
        long validatedId;

        try {
            validatedId = Long.parseLong(id);

            if (validatedId <= 0) {
                throw new NumberFormatException("Id is less or equals 0");
            }
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        return validatedId;
    }

    public static <T> T checkNotFound(T object) {
        if (object == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity is not found");
        }
        return object;
    }

    public static Player validateAndCreate(Player request) {
        Map<String, String> checkMap = convertPlayerToMap(request);

        if (checkMap.containsValue(null))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player properties contain null values");

        Player player = new Player();

        try {
            player.setName(checkPlayerName(request));
            player.setTitle(checkPlayerTitle(request));
            player.setRace(request.getRace());
            player.setProfession(request.getProfession());
            player.setBirthday(checkPlayerBirthday(request));
            player.setBanned(request.isBanned());
            player.setExperience(checkPlayerExperience(request));
            player.setLevel(player.calculateCurrentLevel());
            player.setUntilNextLevel(player.calculateExperienceToNextLevel());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        return player;
    }

    public static Player validateAndUpdate(Player request, Player oldPlayer) {
        Player updatePlayer = new Player(request);

        try {
            if (updatePlayer.getName() == null) {
                updatePlayer.setName(oldPlayer.getName());
            } else {
                checkPlayerName(updatePlayer);
            }

            if (updatePlayer.getTitle() == null) {
                updatePlayer.setTitle(oldPlayer.getTitle());
            } else {
                checkPlayerTitle(updatePlayer);
            }

            if (updatePlayer.getRace() == null) {
                updatePlayer.setRace(oldPlayer.getRace());
            }

            if (updatePlayer.getProfession() == null) {
                updatePlayer.setProfession(oldPlayer.getProfession());
            }

            if (updatePlayer.isBanned() == null) {
                updatePlayer.setBanned(oldPlayer.isBanned());
            }

            if (updatePlayer.getBirthday() == null) {
                updatePlayer.setBirthday(oldPlayer.getBirthday());
            } else {
                checkPlayerBirthday(updatePlayer);
            }

            if (updatePlayer.getExperience() == null) {
                updatePlayer.setExperience(oldPlayer.getExperience());
                updatePlayer.setLevel(oldPlayer.getLevel());
                updatePlayer.setUntilNextLevel(oldPlayer.getUntilNextLevel());
            } else {
                checkPlayerExperience(updatePlayer);
                updatePlayer.setLevel(updatePlayer.calculateCurrentLevel());
                updatePlayer.setUntilNextLevel(updatePlayer.calculateExperienceToNextLevel());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        return updatePlayer;
    }

    private static Integer checkPlayerExperience(Player player) {
        if (player.getExperience() < 0 || player.getExperience() > 10_000_000) {
            throw new IllegalArgumentException("Player experience should be in bounds 0 - 10000");
        }
        return player.getExperience();
    }

    private static Date checkPlayerBirthday(Player player) {
        if (player.getBirthday().before(BIRTHDAY_LOWER_BORDER) || player.getBirthday().after(BIRTHDAY_UPPER_BORDER)) {
            throw new IllegalArgumentException("Player registration date should be in bounds " + BIRTHDAY_LOWER_BORDER + " - " + BIRTHDAY_UPPER_BORDER);
        }
        return player.getBirthday();
    }

    private static String checkPlayerTitle(Player player) {
        if (player.getTitle().length() > 30) {
            throw new IllegalArgumentException("Player title length should be less then 30");
        }
        return player.getTitle();
    }

    private static String checkPlayerName(Player player) {
        if (player.getName().equals("") || player.getName().length() > 12) {
            throw new IllegalArgumentException("Player name should not be blank or length bigger then 12");
        }
        return player.getName();
    }

    private static Map<String, String> convertPlayerToMap(Player player) {
        Map<String, String> playerMap = new HashMap<>();
        playerMap.put("name", player.getName());
        playerMap.put("title", player.getTitle());
        playerMap.put("race", player.getRace() == null ? null : player.getRace().name());
        playerMap.put("profession", player.getProfession() == null ? null : player.getProfession().name());
        playerMap.put("birthday", player.getBirthday() == null ? null : String.valueOf(player.getBirthday().getTime()));
        playerMap.put("banned", player.isBanned() == null ? "false" : player.isBanned().toString());
        playerMap.put("experience", String.valueOf(player.getExperience()));

        return playerMap;
    }
}
