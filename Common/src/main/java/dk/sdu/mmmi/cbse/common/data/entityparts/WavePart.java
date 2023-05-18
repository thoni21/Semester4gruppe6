package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import org.w3c.dom.ranges.Range;

import java.util.ArrayList;
import java.util.List;

public class WavePart implements EntityPart{
    private int enemyCount;
    private List<Entity> enemies;
    public WavePart (int enemyCount) {
        this.enemyCount = enemyCount;
        this.enemies = new ArrayList<>();
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        // Spawn init wave
        for (int i = 0; i < enemyCount; i++) {

        }
    }
}
