package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author Someone
 */
public class LifePart implements EntityPart {

    private boolean dead = false;
    private int life;
    private boolean isHit = false;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        //TODO: If entity is hit minus damage.
        if (this.isHit) {
            this.life = - 1;
            this.isHit = false;
        }
        if (this.life <= 0) {
            this.dead = true;
        }
    }
}
