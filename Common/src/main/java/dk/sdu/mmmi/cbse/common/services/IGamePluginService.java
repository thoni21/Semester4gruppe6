package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {

    /**
     * @author Jan Corfixen
     * @param gameData
     * @param world
     */
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
