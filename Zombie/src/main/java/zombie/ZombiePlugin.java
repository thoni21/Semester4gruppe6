package zombie;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class ZombiePlugin implements IGamePluginService {
    private Entity zombie;
    @Override
    public void start(GameData gameData, World world) {
        zombie = createEnemy(gameData);
        world.addEntity(zombie);
    }
    private Entity createEnemy(GameData gameData){
        String spritePath = "assets/zombie.png";
        float deacceleration = 100;
        float acceleration = 140;
        float maxSpeed = 140;
        float x = (float) Math.random() * gameData.getDisplayWidth();
        float y = (float) Math.random() * gameData.getDisplayHeight();

        Entity zombie = new Zombie();
        zombie.add(new MovingPart(deacceleration, acceleration, maxSpeed));
        zombie.add(new PositionPart(x, y));
        zombie.add(new SpritePart(spritePath,44,44, gameData.getDisplayHeight()/10.0f, gameData.getDisplayWidth()/10.0f,1));

        return zombie;
    }
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(zombie);
    }
}
