package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface IArtificialIntelligenceService {

    /**
     *
     * @param gameData
     * @param entity
     */
     void process(GameData gameData, Entity entity);
}


