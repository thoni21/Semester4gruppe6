package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IArtificialIntelligenceService {

    /**
     * @param gameData
     * @param data
     * @param entity
     */
     void process(World gameData, GameData data, Entity entity);
}


