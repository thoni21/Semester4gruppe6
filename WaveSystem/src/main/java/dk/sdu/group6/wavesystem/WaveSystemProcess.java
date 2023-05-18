package dk.sdu.group6.wavesystem;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.events.CollisionEvent;
import dk.sdu.mmmi.cbse.common.events.SpawnEvent;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class WaveSystemProcess implements IPostEntityProcessingService {
    private List<Entity> enemies = new ArrayList<>();
    private Entity player;
    @Override
    public void process(GameData gameData, World world) {
        // Get enemies
        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityTypes.Enemy) {
                enemies.add(entity);
            }
            if (entity.getType() == EntityTypes.Player) {
                this.player = entity;
            }
        }

        if (enemies.size() <= 0 && player != null) {
            // TODO: create spawn event
            gameData.addEvent(new SpawnEvent(this.player, 5));
        }
    }

    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
