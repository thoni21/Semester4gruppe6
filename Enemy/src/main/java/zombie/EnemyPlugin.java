package zombie;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    private Entity enemy;
    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }
    private Entity createEnemy(GameData gameData){

        Entity zombie = new Enemy();

        return zombie;
    }
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
