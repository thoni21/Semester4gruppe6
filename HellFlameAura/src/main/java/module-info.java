import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module HellFlameAura {
    exports hellFlameAura;
    requires Common;
    requires CommonBullet;

    provides IGamePluginService with hellFlameAura.HellFlamePlugin;
    provides IEntityProcessingService with hellFlameAura.HellFlameAuraProcessing;
}