package player;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
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
        // Sprite attributes
        int spriteStartPosX = 0; // Sprite start position X in sprite sheet
        int spriteStartPosY = 600; // Sprite start position Y in sprite sheet
        int spriteWidth = 32;     // Sprite width in sprite sheet
        int spriteHeight = 44;    // Sprite height in sprite sheet
        int spriteLayer = 2;      // TODO: maybe convert layers to an enum

        // Movement attributes
        float deacceleration = 100;
        float acceleration = 140;
        float maxSpeed = 140;
        float x = (float) gameData.getDisplayWidth() / 2;
        float y = (float) gameData.getDisplayHeight() / 2;

        // Health attributes
        int health = 20;

        Entity player = new Player();
        player.setType(EntityTypes.Player);
        player.add(new MovingPart(deacceleration, acceleration, maxSpeed));
        player.add(new PositionPart(x, y));
        player.add(new SpritePart(spriteStartPosX, spriteStartPosY, spriteWidth,spriteHeight,spriteHeight, spriteWidth, spriteLayer));
        player.add(new LifePart(health));
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }
}
