package dk.sdu.group6.wavesystem;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class WaveSystemProcess implements IPostEntityProcessingService {
    private int waveCount = 1;
    private float waveLength = 20;
    @Override
    public void process(GameData gameData, World world) {
        int enemyCount = 0;

        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityTypes.Enemy) {
                enemyCount ++;
            }
        }

        if (enemyCount > 0) {
            // TODO: create spawn event
        }
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
