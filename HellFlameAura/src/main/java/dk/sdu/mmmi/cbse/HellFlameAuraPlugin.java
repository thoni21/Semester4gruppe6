package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class HellFlameAuraPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
    }



    @Override
    public void stop(GameData gameData, World world) {
        for (Entity entity : world.getEntities(HellFlameAura.class)) {
            world.removeEntity(entity);
        }
    }
}
