import dk.sdu.grp6.movetoai.MoveToAIProcess;
import dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;

module MoveToAI {
    requires Common;

    provides IArtificialIntelligenceService with MoveToAIProcess;
}