package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import com.game.util.PlayerValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Player> getPlayersList(Map<String, String> allParam) {
        PlayerOrder order = PlayerOrder.ID;
        int pageNumber = 0;
        int pageSize = 3;

        if (allParam.containsKey("order")) order = PlayerOrder.valueOf(allParam.get("order"));
        if (allParam.containsKey("pageNumber")) pageNumber = Integer.parseInt(allParam.get("pageNumber"));
        if (allParam.containsKey("pageSize")) pageSize = Integer.parseInt(allParam.get("pageSize"));

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, order.getFieldName());

        Specification<Player> specification = getFilterByParameters(allParam);

        return playerRepository.findAll(specification, page).getContent();
    }

    @Override
    public Integer getPlayersCount(Map<String, String> allParam) {
        if (allParam == null) {
            return (int) playerRepository.count();
        }
        return (int) playerRepository.count(getFilterByParameters(allParam));
    }

    @Override
    public Player createPlayer(Player player) {
        Player createPlayer = PlayerValidationUtil.validateAndCreate(player);
        return playerRepository.save(createPlayer);
    }

    @Override
    public Player getPlayer(String id) {
        long validatedId = PlayerValidationUtil.validateAndGetId(id);

        return PlayerValidationUtil.checkNotFound(playerRepository.findById(validatedId).orElse(null));
    }


    @Override
    public Player updatePlayer(Player player, String id) {
        long validatedId = PlayerValidationUtil.validateAndGetId(id);
        Player oldPlayer = PlayerValidationUtil.checkNotFound(playerRepository.findById(validatedId).orElse(null));

        player.setId(oldPlayer.getId());

        return playerRepository.save(PlayerValidationUtil.validateAndUpdate(player, oldPlayer));
    }

    @Override
    public void deletePlayer(String id) {
        long validatedId = PlayerValidationUtil.validateAndGetId(id);
        Player player = PlayerValidationUtil.checkNotFound(playerRepository.findById(validatedId).orElse(null));

        playerRepository.delete(player);
    }

    private Specification<Player> getFilterByParameters(Map<String, String> allParam) {
        String name = allParam.get("name");
        String title = allParam.get("title");
        Race race = allParam.get("race") == null ? null : Race.valueOf(allParam.get("race"));
        Profession profession = allParam.get("profession") == null ? null : Profession.valueOf(allParam.get("profession"));
        Date after = allParam.get("after") == null ? null : new Date(Long.parseLong(allParam.get("after")));
        Date before = allParam.get("before") == null ? null : new Date(Long.parseLong(allParam.get("before")));
        Boolean isBanned = allParam.get("banned") == null ? null : Boolean.valueOf(allParam.get("banned"));
        Integer minExperience = allParam.get("minExperience") == null ? null : Integer.parseInt(allParam.get("minExperience"));
        Integer maxExperience = allParam.get("maxExperience") == null ? null : Integer.parseInt(allParam.get("maxExperience"));
        Integer minLevel = allParam.get("minLevel") == null ? null : Integer.parseInt(allParam.get("minLevel"));
        Integer maxLevel = allParam.get("maxLevel") == null ? null : Integer.parseInt(allParam.get("maxLevel"));

        Specification<Player> specification = Specification
                .where(name == null ? null : PlayerQuerySpecification.findByName(name))
                .and(title == null ? null : PlayerQuerySpecification.findByTitle(title))
                .and(race == null ? null : PlayerQuerySpecification.findByRace(race))
                .and(profession == null ? null : PlayerQuerySpecification.findByProfession(profession))
                .and(after == null && before == null ? null : PlayerQuerySpecification.findByBirthday(after, before))
                .and(isBanned == null ? null : PlayerQuerySpecification.isBanned(isBanned))
                .and((minExperience == null && maxExperience == null) ? null : PlayerQuerySpecification.findByExperience(minExperience, maxExperience))
                .and(minLevel == null && maxLevel == null ? null : PlayerQuerySpecification.findByLevel(minLevel, maxLevel));

        return specification;
    }
}
