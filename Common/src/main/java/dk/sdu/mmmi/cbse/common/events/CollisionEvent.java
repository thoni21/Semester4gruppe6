package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;

public class CollisionEvent extends Event {
    private Entity colEntity;
    public CollisionEvent(Entity sourceEntity, Entity colEntity) {
        super(sourceEntity);
        this.colEntity = colEntity;
    }

    @Override
    public void executeEvent() {
        // Gdx.app.log("Take damage", super.getSource().getID());
        if (super.getSource().getType() == EntityTypes.Player && this.colEntity.getType() == EntityTypes.Enemy) {
            // The player has collided with an enemy.
            // TODO: Kill/Take damage from the player
            LifePart sourceLifePart = super.getSource().getPart(LifePart.class);
            sourceLifePart.setIsHit(true);
        }
    }
}
