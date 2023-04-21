package weapon;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.ArrayList;

import static dk.sdu.mmmi.cbse.common.data.GameKeys.*;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.DOWN;

public class WeaponProcessing implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        // Find player entity.
        Entity player = null;
        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityTypes.Player) {
                player = entity;
                break;
            }
        }

        // Find closest enemy.
        /*
        Entity closestEnemy = null;
        for (Entity entity : world.getEntities()) {
            if ((entity.getType() == EntityTypes.Enemy && closestEnemy != null)
            && calculateDistance(player, closestEnemy) > calculateDistance(player, entity)) {
                closestEnemy = entity;
            } else {
                closestEnemy = entity;
            }
        }
        */

        // Find enemies within range (aura weapon).
        ArrayList<Entity> enemiesInRange = new ArrayList<>();
        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityTypes.Enemy
                    && calculateDistance(player, entity) < 500) {
                enemiesInRange.add(entity);
            }
        }

        for (Entity entity : enemiesInRange) {
            // gameData.addEvent();
        }
    }

    private double calculateDistance(Entity player, Entity enemy) {
        PositionPart playerPosition = player.getPart(PositionPart.class);
        PositionPart enemyPosition = enemy.getPart(PositionPart.class);

        return Math.sqrt(Math.pow(playerPosition.getX() - enemyPosition.getX(), 2)
        + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2));
    }
}
