import dk.sdu.grp6.weapon.WeaponPlugin;
import dk.sdu.grp6.weapon.WeaponProcessing;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Weapon {
    exports dk.sdu.grp6.weapon;
    requires Common;
    requires CommonBullet;

    provides IGamePluginService with WeaponPlugin;
    provides IEntityProcessingService with WeaponProcessing;
    uses dk.sdu.mmmi.commonbullet.BulletSPI;
}