import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Enemy {
    uses dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;
    exports zombie;
    requires Common;
    requires AI;
    requires com.badlogic.gdx;

    provides IGamePluginService with zombie.ZombiePlugin;
    provides IEntityProcessingService with zombie.ZombieControlSystem;
}