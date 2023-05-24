package dk.sdu.grp6.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.events.CollisionEvent;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;


import java.awt.*;

/**
 * @author Group 6
 */
public class CollisionDetection implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world){
        // TODO find a way to get the entities classes without the for-loop

        /*
           This works, no clue why the others don't, but this solution would be extremely memory heavy
           Finds all players and zombies in entities check if they overlap
        */
        for (Entity entity : world.getEntities()){
            for (Entity colEntity : world.getEntities()){
                if (collideWith(entity, colEntity) && !entity.equals(colEntity)) {
                    gameData.addEvent(new CollisionEvent(entity, colEntity));
                    // Gdx.app.log("Collision", entity.getID() + " with " + colEntity.getID());
                }
            }
        }

    }

    Boolean collideWith(Entity entity, Entity collisionEntity) {
        PositionPart ent = entity.getPart(PositionPart.class);
        PositionPart colEnt = collisionEntity.getPart(PositionPart.class);


        // Check to see what the distance is between the two entities.
        // Return if the distance is to long to collide.
        // TODO: Define the distance length.
        float dx = ent.getX() - colEnt.getX();
        float dy = ent.getY() - colEnt.getY();
        float distance = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        // Gdx.app.log("Distance", distance + "");
        if (distance > 200) {
            return false;
        }

        // Creates rectangles behind the sprites to control hitboxes
        // using the current pos of the PositionPart and the dimensions
        // of the SpritePart
        SpritePart entSprite = entity.getPart(SpritePart.class);
        SpritePart colEntSprite = collisionEntity.getPart(SpritePart.class);
        Rectangle hitBoxEnt = new Rectangle((int) ent.getX(),(int) ent.getY(), (int) entSprite.getSizeWidth(), (int) entSprite.getSizeHeight());
        Rectangle hitBoxColEnt = new Rectangle((int) colEnt.getX(),(int) colEnt.getY(), (int) colEntSprite.getSizeWidth(), (int) colEntSprite.getSizeHeight());

        // return based on if they overlap or not, true if they do, false if not
        return hitBoxEnt.intersects(hitBoxColEnt);
    }
}
