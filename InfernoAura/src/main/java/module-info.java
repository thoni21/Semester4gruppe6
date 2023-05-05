import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.commonbullet.BulletSPI;

module InfernoAura {
    requires Common;
    requires CommonBullet;

    provides IGamePluginService with dk.sdu.mmmi.infernoaura.InfernoAuraPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.infernoaura.InfernoAuraProcess;
    provides BulletSPI with dk.sdu.mmmi.infernoaura.InfernoAuraProcess;
}