package dk.sdu.grp6.weapon;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonbullet.BulletSPI;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class WeaponProcessing implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        // Loop the world entities to find the weapon
        Entity weaponEntity = null;
        for (Entity weapon : world.getEntities(Weapon.class)) {
            weaponEntity = weapon;

            // Loop the world entities to find the player
            for (Entity entity : world.getEntities()) {
                if (entity.getType() == EntityTypes.Player) {
                    Entity player = entity;

                    // Check if player is alive. If not remove weapon from world
                    LifePart playerLifePart = player.getPart(LifePart.class);
                    if (playerLifePart.isDead()) {
                        world.removeEntity(weaponEntity);
                    }

                    // Get Position parts
                    PositionPart playerPositionPart = player.getPart(PositionPart.class);
                    PositionPart weaponPositionPart = weapon.getPart(PositionPart.class);

                    // Get Sprite parts.
                    SpritePart playerSpritePart = player.getPart(SpritePart.class);
                    SpritePart weaponSpritePart = weapon.getPart(SpritePart.class);

                    // Calculate the position of the aura according to the player.
                    weaponPositionPart.setX(playerPositionPart.getX() -
                            playerSpritePart.getSrcWidth() / 2f);
                    weaponPositionPart.setY(playerPositionPart.getY() +
                            playerSpritePart.getSrcHeight() / 2f + weaponSpritePart.getSrcHeight() / 2f);

                    break;
                }
            }
            break;
        }

        // Get the aura(bullet) if it exists
        Entity aura = null;
        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityTypes.Aura) {
                aura = entity;
                break;
            }
        }

        // If the doesn't exist aura(bullet) and weapon exists - Spawn the aura
        if (aura == null && weaponEntity != null) {
            for (BulletSPI bullet : getBulletSPIs()) {
                world.addEntity(bullet.createBullet(weaponEntity, gameData));
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
