import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Weapon {
    exports weapon;
    requires Common;

    provides IGamePluginService with weaponzombie.ZombiePlugin;
    provides IEntityProcessingService with zombie.ZombieControlSystem;
}