package dk.sdu.mmmi.cbse;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.ArrayList;

public class HellFlameAuraProcessing implements IEntityProcessingService, BulletSPI {
    @Override
    public void process(GameData gameData, World world) {
        // Find current HellFlameAura instance (only one aura exist at a time, no double damage).
        Entity hellFlameAura = null;
        for (Entity entity : world.getEntities(HellFlameAura.class)) {
            hellFlameAura = entity;
            break;
        }

        // Update position-part based on player position-part.
        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityTypes.Player) {
                // Position.
                PositionPart playerPositionPart = entity.getPart(PositionPart.class);
                PositionPart auraPositionPart = hellFlameAura.getPart(PositionPart.class);

                // Sprite.
                SpritePart playerSpritePart = entity.getPart(SpritePart.class);
                SpritePart auraSpritePart = hellFlameAura.getPart(SpritePart.class);

                auraPositionPart.setX(playerPositionPart.getX() +
                        playerSpritePart.getSrcWidth() / 2f - auraSpritePart.getSrcWidth() / 2f);
                auraPositionPart.setY(playerPositionPart.getY() -
                        playerSpritePart.getSrcHeight() / 2f + auraSpritePart.getSrcHeight() / 2f);
                break;
            }
        }
    }

    private double calculateDistance(Entity player, Entity enemy) {
        PositionPart playerPosition = player.getPart(PositionPart.class);
        PositionPart enemyPosition = enemy.getPart(PositionPart.class);

        return Math.sqrt(Math.pow(playerPosition.getX() - enemyPosition.getX(), 2)
                + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2));
    }
}
