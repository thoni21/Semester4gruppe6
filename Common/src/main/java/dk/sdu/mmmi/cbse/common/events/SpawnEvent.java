package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class SpawnEvent extends Event {
    private int enemyCount;
    public SpawnEvent(Entity sourceEntity, int enemyCount) {
        super.setSource(sourceEntity);
        this.enemyCount = enemyCount;
    }

    @Override
    public void executeEvent() {
        for (int i = 0; i < enemyCount; i++) {

        }
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
