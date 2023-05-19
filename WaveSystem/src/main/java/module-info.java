import dk.sdu.grp6.wavesystem.WaveSystemProcess;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module WaveSystem {
    requires Common;
    provides IPostEntityProcessingService with WaveSystemProcess;
}