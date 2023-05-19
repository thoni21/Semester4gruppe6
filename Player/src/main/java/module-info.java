import dk.sdu.grp6.player.PlayerMovement;
import dk.sdu.grp6.player.PlayerPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    exports dk.sdu.grp6.player;
    requires Common;
    requires com.badlogic.gdx;

    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with PlayerMovement;
}