import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    exports player;
    requires Common;
    requires com.badlogic.gdx;

    provides IGamePluginService with player.PlayerPlugin;
    provides IEntityProcessingService with player.PlayerMovement;
}