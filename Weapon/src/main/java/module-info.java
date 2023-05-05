import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Weapon {
    exports weapon;
    requires Common;
    requires CommonBullet;
    requires com.badlogic.gdx;

    provides IGamePluginService with weapon.WeaponPlugin;
    provides IEntityProcessingService with weapon.WeaponProcessing;
    uses dk.sdu.mmmi.commonbullet.BulletSPI;
}