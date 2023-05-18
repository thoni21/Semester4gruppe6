package dk.sdu.group6.wavesystem;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.WavePart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.List;

public class WaveSystemPlugin implements IGamePluginService {
    private Entity waveSystem;

    @Override
    public void start(GameData gameData, World world) {
        waveSystem = createWave(gameData, world);
        world.addEntity(waveSystem);
    }

    private Entity createWave(GameData gameData, World world) {
        List<Entity> enemies = new ArrayList<>();

        // Get all enemy entities in world - if there is any
        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityTypes.Enemy) {
                enemies.add(entity);
            }
        }

        Entity waveSystem = new WaveSystem();
        waveSystem.add(new WavePart(5));

        return waveSystem;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(waveSystem);
    }
}
