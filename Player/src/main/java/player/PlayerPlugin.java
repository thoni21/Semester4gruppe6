package player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData) {
        String spritePath = "assets/player.png";
        float deacceleration = 100;
        float acceleration = 140;
        float maxSpeed = 140;
        float x = (float) gameData.getDisplayWidth() / 2;
        float y = (float) gameData.getDisplayHeight() / 2;

        Entity player = new Player();
        player.add(new MovingPart(deacceleration, acceleration, maxSpeed));
        player.add(new PositionPart(x, y));
        player.add(new SpritePart(spritePath,44,44,gameData.getDisplayHeight()/10.0f, gameData.getDisplayWidth()/10.0f,2));

        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }
}
