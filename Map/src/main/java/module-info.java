import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Map {
    requires Common;

    provides IGamePluginService with map.MapPlugin;
    provides IEntityProcessingService with map.MapProcessing;
}