package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     *
     * @author Jan Corfixen
     * <a href="https://github.com/sweat-tek/AsteroidsJPMS/tree/lwjgl3">Repo</a>
     * @param gameData
     * @param world
     */
    void process(GameData gameData, World world);
}
