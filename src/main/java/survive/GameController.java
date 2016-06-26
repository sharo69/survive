package survive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by nikolakaloyanov on 6/26/16.
 */
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Game createGame(@RequestParam int maxPlayers, @RequestParam int width, @RequestParam int height) {
        Game g = new Game(maxPlayers, width, height);
        gameRepository.saveAndFlush(g);
        return g;
    }

    @RequestMapping(value = "/{gameId}", method = RequestMethod.GET)
    public Game getGame(@PathVariable long gameId) {
        return gameRepository.findOne(gameId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @RequestMapping(value = "/{gameId}/player", method = RequestMethod.POST)
    public Game addPlayer(@PathVariable long gameId) {
        Random r = new Random();
        Game g = gameRepository.findOne(gameId);
        int x = r.nextInt(g.getWidth());
        int y = r.nextInt(g.getHeight());
        g.addPlayer(new Player(g.findZone(x, y)));
        gameRepository.save(g);
        return g;
    }

    @RequestMapping(value = "/{gameId}/player/{playerId}", method = RequestMethod.GET)
    public Map<String, Object> getPlayer(@PathVariable long gameId, @PathVariable long playerId) {
        Map<String, Object> result = new HashMap<>();

        Game g = gameRepository.findOne(gameId);
        result.put("w", g.getWidth());
        result.put("h", g.getHeight());
        result.put("player", playerRepository.findOne(playerId));

        return result;
    }

    @RequestMapping(value = "/{gameId}/player/{playerId}/move", method = RequestMethod.POST)
    public Player move(@PathVariable long gameId, @PathVariable long playerId,@RequestParam int x, @RequestParam int y) {
        Player p = playerRepository.findOne(playerId);
        Game g = gameRepository.findOne(gameId);
        Zone z = g.findZone(x,y);
        p.addVisitedZone(p.getCurrentZone());
        p.setCurrentZone(z);
        gameRepository.save(g);

        return p;
    }
}
