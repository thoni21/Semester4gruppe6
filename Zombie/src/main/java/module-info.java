import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Enemy {
    exports zombie;
    requires Common;

    provides IGamePluginService with zombie.ZombiePlugin;
    provides IEntityProcessingService with zombie.ZombieControlSystem;
}