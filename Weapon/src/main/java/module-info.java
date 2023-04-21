import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Weapon {
    exports weapon;
    requires Common;

    provides IGamePluginService with weapon.WeaponPlugin;
    provides IEntityProcessingService with weapon.WeaponProcessing;
}