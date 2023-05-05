package ai;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AiPlugin implements IArtificialIntelligenceService {

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        MovingPart movingPart = entity.getPart(MovingPart.class);

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

        movingPart.process(gameData, entity);
        positionPart.process(gameData, entity);

    }
}
