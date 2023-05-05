package dk.sdu.mmmi.commonbullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface BulletSPI {
    Entity createBullet(Entity entity, GameData gameData);
}
