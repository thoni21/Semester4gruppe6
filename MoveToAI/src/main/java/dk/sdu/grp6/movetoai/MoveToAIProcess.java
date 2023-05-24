package dk.sdu.grp6.movetoai;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;

/**
 * @author Group 6
 */
public class MoveToAIProcess implements IArtificialIntelligenceService {
    @Override
    public void process(World world, GameData gameData, Entity entityWithAI) {
        PositionPart entityWithAIPosPart = entityWithAI.getPart(PositionPart.class);
        MovingPart entityWithAIMovePart = entityWithAI.getPart(MovingPart.class);

        // Loop world entities to find player
        for (Entity player : world.getEntities()) {
            if (player.getType() == EntityTypes.Player) {
                PositionPart playerPos = player.getPart(PositionPart.class);

                if (playerPos.getX() < entityWithAIPosPart.getX()){ // zombie left side
                    entityWithAIMovePart.setLeft(true);
                    entityWithAIMovePart.setRight(false);
                }
                if (playerPos.getX() > entityWithAIPosPart.getX()){ // zombie right side
                    entityWithAIMovePart.setRight(true);
                    entityWithAIMovePart.setLeft(false);
                }
                if (playerPos.getY() > entityWithAIPosPart.getY()){ // zombie top side
                    entityWithAIMovePart.setUp(true);
                    entityWithAIMovePart.setDown(false);
                }
                if (playerPos.getY() < entityWithAIPosPart.getY()) { // zombie bottom side
                    entityWithAIMovePart.setDown(true);
                    entityWithAIMovePart.setUp(false);
                }
            }
        }

        // update AI entity moving part
        entityWithAIMovePart.process(gameData, entityWithAI);
    }
}
