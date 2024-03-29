/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 * @author Alexander
 * <a href="https://github.com/sweat-tek/AsteroidsJPMS/tree/lwjgl3">Repo</a>
 */
public class TimerPart
        implements EntityPart {

    // The current expiration time left.
    private float expiration;

    // The initial expiration time.
    private float expirationTotal;

    public TimerPart(float expiration) {
        this.expiration = expiration;
        this.expirationTotal = expiration;
    }

    public float getExpiration() {
        return expiration;
    }

    public float getExpirationTotal() { return expirationTotal; }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }

    public void reduceExpiration(float delta) {
        this.expiration -= delta;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (expiration > 0) {
            reduceExpiration(gameData.getDelta());
        }

        if (expiration <= 0) {
            LifePart lifePart = entity.getPart(LifePart.class);
            lifePart.setLife(0);
        }
    }

}
