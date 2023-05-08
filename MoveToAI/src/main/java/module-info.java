import dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;

module MoveToAI {
    requires Common;

    provides IArtificialIntelligenceService with movetoai.MoveToAIProcess;
}