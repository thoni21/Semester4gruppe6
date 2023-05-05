package weapon;

import com.badlogic.gdx.Gdx;
import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
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
        // Find player entity.

        Entity weaponEntity = null;
        for (Entity weapon : world.getEntities(Weapon.class)) {
            weaponEntity = weapon;

            for (Entity entity : world.getEntities()) {
                if (entity.getType() == EntityTypes.Player) {
                    Entity player = entity;

                    // Position.
                    PositionPart playerPositionPart = player.getPart(PositionPart.class);
                    PositionPart weaponPositionPart = weapon.getPart(PositionPart.class);

                    // Sprite.
                    SpritePart playerSpritePart = player.getPart(SpritePart.class);
                    SpritePart weaponSpritePart = weapon.getPart(SpritePart.class);

                    weaponPositionPart.setX(playerPositionPart.getX() -
                            playerSpritePart.getSrcWidth() / 2f);
                    weaponPositionPart.setY(playerPositionPart.getY() +
                            playerSpritePart.getSrcHeight() / 2f + weaponSpritePart.getSrcHeight() / 2f);

                    break;
                }
            }

            break;
        }

        Entity aura = null;
        for (Entity entity : world.getEntities()) {
            if (entity.getType() == EntityTypes.Aura) {
                aura = entity;
                break;
            }
        }

        if (aura == null && weaponEntity != null) {
            Gdx.app.log("Test", "" + getBulletSPIs().size());
            for (BulletSPI bullet : getBulletSPIs()) {
                world.addEntity(bullet.createBullet(weaponEntity, gameData));
                Gdx.app.log("Test", "Bullet fired");
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {

        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private double calculateDistance(Entity player, Entity enemy) {
        PositionPart playerPosition = player.getPart(PositionPart.class);
        PositionPart enemyPosition = enemy.getPart(PositionPart.class);

        return Math.sqrt(Math.pow(playerPosition.getX() - enemyPosition.getX(), 2)
        + Math.pow(playerPosition.getY() - enemyPosition.getY(), 2));
    }
}
