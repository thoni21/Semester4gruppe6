package zombie;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class ZombiePlugin implements IGamePluginService {
    private Entity zombie;
    @Override
    public void start(GameData gameData, World world) {
        zombie = createEnemy(gameData);
        world.addEntity(zombie);
    }
    private Entity createEnemy(GameData gameData){
        // Sprite attributes.
        int spriteStartPosX = 32;  // Sprite start position X in sprite sheet
        int spriteStartPosY = 600; // Sprite start position Y in sprite sheet
        int spriteWidth = 32;      // Sprite width in sprite sheet
        int spriteHeight = 44;     // Sprite height in sprite sheet
        int spriteLayer = 2;       // TODO: maybe convert layers to an enum
        float opacity = 1;         // Sprite opacity

        // Movement attributes.
        float deacceleration = 100;
        float acceleration = 140;
        float maxSpeed = 140;
        float x = (float) Math.random() * gameData.getDisplayWidth();
        float y = (float) Math.random() * gameData.getDisplayHeight();

        // Health attributes.
        int health = 1000;

        // Damage attributes.
        int damage = 1;

        Entity zombie = new Zombie();
        zombie.setType(EntityTypes.Enemy);
        zombie.add(new MovingPart(deacceleration, acceleration, maxSpeed));
        zombie.add(new PositionPart(x, y));
        zombie.add(new SpritePart(spriteStartPosX, spriteStartPosY, spriteWidth,spriteHeight,spriteHeight, spriteWidth, spriteLayer, opacity));
        zombie.add(new LifePart(health));
        zombie.add(new DamagePart(damage));
        return zombie;
    }
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(zombie);
    }
}
