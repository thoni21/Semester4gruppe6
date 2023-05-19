import dk.sdu.grp6.zombie.ZombieControlSystem;
import dk.sdu.grp6.zombie.ZombiePlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Zombie {
    uses dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;
    exports dk.sdu.grp6.zombie;
    requires Common;
    requires com.badlogic.gdx;

    provides IGamePluginService with ZombiePlugin;
    provides IEntityProcessingService with ZombieControlSystem;
}