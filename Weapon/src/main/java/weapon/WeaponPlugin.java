package weapon;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class WeaponPlugin implements IGamePluginService {

    private Entity weapon;
    @Override
    public void start(GameData gameData, World world) {
        weapon = createEnemy(gameData);
        world.addEntity(zombie);
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
