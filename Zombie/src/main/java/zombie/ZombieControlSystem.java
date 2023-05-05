package zombie;

import ai.AiPlugin;
import com.badlogic.gdx.Gdx;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class ZombieControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {


        for (Entity zombie : world.getEntities(Zombie.class)) {
            LifePart lifePart = zombie.getPart(LifePart.class);
            if (lifePart.isDead()) {
                world.removeEntity(zombie);
            }

            Gdx.app.log("Zombie Life" ,lifePart.getLife() + "");

            AiPlugin aiPlugin = new AiPlugin();
            aiPlugin.process(gameData, zombie);

            lifePart.process(gameData, zombie);
        }


    }
}