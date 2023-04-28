package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;

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
            // TODO: Kill/Take damage from the player an enemy - Make sure they don't overlap.
            LifePart sourceLifePart = super.getSource().getPart(LifePart.class);
            sourceLifePart.setIsHit(true);

            PositionPart playerPos = super.getSource().getPart(PositionPart.class);
            PositionPart enemyPos = colEntity.getPart(PositionPart.class);

            if(playerPos.getX() < enemyPos.getX()){ // right side player
                enemyPos.setX(enemyPos.getX()+5);
            }
            if (playerPos.getX() > enemyPos.getX()){ // left side player
                enemyPos.setX(enemyPos.getX()-5);
            }
            if (playerPos.getY() > enemyPos.getY()){ // bottom side player
                enemyPos.setY(enemyPos.getY()-5);
            }
            if (playerPos.getY() < enemyPos.getY()) { // top side player
                enemyPos.setY(enemyPos.getY()+5);
            }
        }
    }
}
