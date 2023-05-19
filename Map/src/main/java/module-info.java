import dk.sdu.grp6.map.MapPlugin;
import dk.sdu.grp6.map.MapProcessing;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Map {
    requires Common;

    provides IGamePluginService with MapPlugin;
    provides IEntityProcessingService with MapProcessing;
}