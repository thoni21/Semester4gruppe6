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
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * @author Group 6
 */
public class WeaponProcessing implements IEntityProcessingService {
    Entity playerEntity;
    LifePart playerLifePart;
    PositionPart playerPositionPart;
    SpritePart playerSpritePart;
    Collection<BulletSPI> bulletSPIs;

    @Override
    public void process(GameData gameData, World world) {
        if (playerEntity == null) {
            playerEntity = getPlayerEntity(world);
            if (playerEntity != null) {
                playerLifePart = playerEntity.getPart(LifePart.class);
                playerPositionPart = playerEntity.getPart(PositionPart.class);
                playerSpritePart = playerEntity.getPart(SpritePart.class);
            }
        }

        if (playerEntity == null || playerLifePart.isDead()) {
            removeWeapon(world);
            return;
        }

        Entity weaponEntity = getWeaponEntity(world);
        if (weaponEntity != null) {
            updateWeaponPosition(weaponEntity);
        }

        Entity auraEntity = getAuraEntity(world);
        if (auraEntity == null && weaponEntity != null) {
            createAuraEntities(gameData, world, weaponEntity);
        }
    }

    Entity getPlayerEntity(World world) {
        return world.getEntities().stream()
                .filter(entity -> entity.getType() == EntityTypes.Player)
                .findFirst()
                .orElse(null);
    }

    Entity getWeaponEntity(World world) {
        return world.getEntities().stream()
                .filter(entity -> entity instanceof Weapon)
                .findFirst()
                .orElse(null);
    }

    void updateWeaponPosition(Entity weaponEntity) {
        PositionPart weaponPositionPart = weaponEntity.getPart(PositionPart.class);
        SpritePart weaponSpritePart = weaponEntity.getPart(SpritePart.class);
        weaponPositionPart.setX(playerPositionPart.getX() - playerSpritePart.getSrcWidth() / 2f);
        weaponPositionPart.setY(playerPositionPart.getY() + playerSpritePart.getSrcHeight() / 2f + weaponSpritePart.getSrcHeight() / 2f);
    }

    Entity getAuraEntity(World world) {
        return world.getEntities().stream()
                .filter(entity -> entity.getType() == EntityTypes.Aura)
                .findFirst()
                .orElse(null);
    }

    void removeWeapon(World world) {
        Optional<Entity> weaponEntity = world.getEntities(Weapon.class).stream().findFirst();
        weaponEntity.ifPresent(world::removeEntity);
    }

    void createAuraEntities(GameData gameData, World world, Entity weaponEntity) {
        if (bulletSPIs == null) {
            bulletSPIs = loadBulletSPIs();
        }

        for (BulletSPI bullet : bulletSPIs) {
            world.addEntity(bullet.createBullet(weaponEntity, gameData));
        }
    }

    Collection<BulletSPI> loadBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
