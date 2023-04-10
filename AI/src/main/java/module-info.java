import dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;

module AI {
    exports ai;
    requires Common;

    provides IArtificialIntelligenceService with ai.AiPlugin;
}