package dk.sdu.mmmi.infernoaura;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonbullet.BulletSPI;

public class InfernoAuraProcess implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        // Find current HellFlameAura instance (only one aura exist at a time, no double damage).
        for (Entity infernoAura : world.getEntities(InfernoAura.class)) {

            // Check if bullet is dead.
            LifePart lifePart = infernoAura.getPart(LifePart.class);
            if (lifePart.isDead()) {
                world.removeEntity(infernoAura);
            }

            // Update position-part based on player position-part.
            for (Entity entityPosition : world.getEntities()) {
                if (entityPosition.getType() == EntityTypes.Player) {
                    // Position.
                    PositionPart playerPositionPart = entityPosition.getPart(PositionPart.class);
                    PositionPart auraPositionPart = infernoAura.getPart(PositionPart.class);

                    // Sprite.
                    SpritePart playerSpritePart = entityPosition.getPart(SpritePart.class);
                    SpritePart auraSpritePart = infernoAura.getPart(SpritePart.class);

                    auraPositionPart.setX(playerPositionPart.getX() +
                            playerSpritePart.getSrcWidth() / 2f - auraSpritePart.getSrcWidth() / 2f);
                    auraPositionPart.setY(playerPositionPart.getY() +
                            playerSpritePart.getSrcHeight() / 2f - auraSpritePart.getSrcHeight() / 2f);
                    break;
                }
            }

            TimerPart timerPart = infernoAura.getPart(TimerPart.class);
            timerPart.process(gameData, infernoAura);

            // Calculate the time left in percentage
            float timeLeftPer = timerPart.getExpiration() / timerPart.getExpirationTotal();
            SpritePart spritePart = infernoAura.getPart(SpritePart.class);
            // Change the alpha (opacity) of the aura
            spritePart.setOpacity(1 * timeLeftPer);

            lifePart.process(gameData, infernoAura);

            break;
        }
    }

    @Override
    public Entity createBullet(Entity weapon, GameData gameData) {
        PositionPart weaponPosition = weapon.getPart(PositionPart.class);

        // Sprite attributes.
        int spriteStartPosX = 64;  // Sprite start position X in sprite sheet
        int spriteStartPosY = 600; // Sprite start position Y in sprite sheet
        int spriteWidth = 176;     // Sprite width in sprite sheet
        int spriteHeight = 176;    // Sprite height in sprite sheet
        int spriteLayer = 1;       // TODO: maybe convert layers to an enum
        float opacity = 1;         // Sprite opacity

        Entity infernoAura = new InfernoAura();
        infernoAura.add(new SpritePart(
            spriteStartPosX, spriteStartPosY, spriteWidth, spriteHeight, spriteWidth, spriteHeight, spriteLayer,
                opacity));
        infernoAura.add(new PositionPart(weaponPosition.getX(), weaponPosition.getY()));
        infernoAura.add(new TimerPart(2));
        infernoAura.add(new LifePart(1));
        infernoAura.add(new DamagePart(2));

        infernoAura.setType(EntityTypes.Aura);

        return infernoAura;
    }
}
