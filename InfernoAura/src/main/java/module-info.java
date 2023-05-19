import dk.sdu.grp6.infernoaura.InfernoAuraPlugin;
import dk.sdu.grp6.infernoaura.InfernoAuraProcess;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.commonbullet.BulletSPI;

module InfernoAura {
    requires Common;
    requires CommonBullet;

    provides IGamePluginService with InfernoAuraPlugin;
    provides IEntityProcessingService with InfernoAuraProcess;
    provides BulletSPI with InfernoAuraProcess;
}