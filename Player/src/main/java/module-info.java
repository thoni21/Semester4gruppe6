import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    requires Common;
    provides IGamePluginService with player.PlayerPlugin;
}