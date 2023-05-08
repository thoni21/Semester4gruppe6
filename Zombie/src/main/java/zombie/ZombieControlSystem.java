package zombie;

import com.badlogic.gdx.Gdx;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class ZombieControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {


        for (Entity zombie : world.getEntities(Zombie.class)) {
            LifePart lifePart = zombie.getPart(LifePart.class);
            if (lifePart.isDead()) {
                world.removeEntity(zombie);
            }

            // Gdx.app.log("Zombie Life" ,lifePart.getLife() + "");

            // Find the AI
            // TODO: Can be modified to take a specific AI if we create more - (If AI type = some type enum)
            for (IArtificialIntelligenceService ai : getArtificialIntelligenceServices()) {
                ai.process(world, gameData, zombie);
            }

            lifePart.process(gameData, zombie);
        }
    }

    private Collection<? extends IArtificialIntelligenceService> getArtificialIntelligenceServices() {
        return ServiceLoader.load(IArtificialIntelligenceService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}