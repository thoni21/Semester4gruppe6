import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    exports player;
    requires Common;
    uses IGamePluginService;
    provides IGamePluginService with player.PlayerPlugin;
}