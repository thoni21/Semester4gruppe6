package zombie;

import ai.AiPlugin;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class ZombieControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity zombie : world.getEntities(Zombie.class)) {
            AiPlugin aiPlugin = new AiPlugin();
            aiPlugin.process(gameData, zombie);
        }
    }
}