package zombie;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class ZombieControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity zombie : world.getEntities(Zombie.class)) {
            PositionPart positionPart = zombie.getPart(PositionPart.class);
            MovingPart movingPart = zombie.getPart(MovingPart.class);

            if (Math.random() < 0.2) {
                movingPart.setLeft(Math.random() < 0.5);
            }
            if (Math.random() < 0.2) {
                movingPart.setRight(Math.random() < 0.5);
            }
            if (Math.random() < 0.2) {
                movingPart.setUp(Math.random() < 0.5);
            }
            if (Math.random() < 0.2) {
                movingPart.setDown(Math.random() < 0.5);
            }

            movingPart.process(gameData, zombie);
            positionPart.process(gameData, zombie);
        }
    }
}
