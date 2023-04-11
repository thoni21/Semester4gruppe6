package dk.sdu.mmmi.cbse;

import com.badlogic.gdx.Gdx;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import player.Player;
import zombie.Zombie;

import java.awt.*;

public class CollisionDetection implements IEntityProcessingService{
    private PositionPart ent;
    private PositionPart colEnt;
    private SpritePart entSprite;
    private SpritePart colEntSprite;

    @Override
    public void process(GameData gameData, World world){
        // TODO find a way to get the entities classes without the for-loop
        // TODO find out if i fucked with other dependencies
        // This brings my pis i kog
        /*
        for (Entity entity : world.getEntities()){
            PositionPart ent1 = entity.getPart(PositionPart.class);
            for (Entity colEntity : world.getEntities()){
                if (entity.equals(world.getEntities(Player.class)) && colEntity.equals(world.getEntities(Zombie.class))){
                    if (collideWith(entity, colEntity)) {
                        ent1.setY(200);
                        ent1.setX(200);
                    }

                }
            }
        }
        */

        // This brings my pis i kog
        /*
        for (Entity entity : world.getEntities()){
            PositionPart ent1 = entity.getPart(PositionPart.class);
            for (Entity colEntity : world.getEntities()){
                if (collideWith(entity, colEntity) && colEntity == world.getEntities(Zombie.class)) {
                    ent1.setY(200);
                    ent1.setX(200);
                }
            }
        }
        */

        /*
           This works, no clue why the others don't, but this solution would be extremely memory heavy
           Finds all players and zombies in entities check if they overlap
           and if true sets player position to 200,200
        */
        for (Entity entity : world.getEntities(Player.class)){
            PositionPart ent1 = entity.getPart(PositionPart.class);
            for (Entity colEntity : world.getEntities(Zombie.class)){
                if (collideWith(entity, colEntity)) {
                    ent1.setY(200);
                    ent1.setX(200);
                }
            }
        }

    }

    public Boolean collideWith(Entity entity, Entity collisionEntity){
        ent = entity.getPart(PositionPart.class);
        colEnt = collisionEntity.getPart(PositionPart.class);
        entSprite = entity.getPart(SpritePart.class);
        colEntSprite = collisionEntity.getPart(SpritePart.class);

        // Creates rectangles behind the sprites to control hitboxes
        // using the current pos of the PositionPart and the dimensions
        // of the SpritePart
        Rectangle hitBoxEnt = new Rectangle((int) ent.getX(),(int) ent.getY(), (int) entSprite.getSizeWidth(), (int) entSprite.getSizeHeight());
        Rectangle hitBoxColEnt = new Rectangle((int) colEnt.getX(),(int) colEnt.getY(), (int) colEntSprite.getSizeWidth(), (int) colEntSprite.getSizeHeight());

        // Checks if the two rectangles overlaps
        boolean overlapping = hitBoxEnt.intersects(hitBoxColEnt);

        // return based on if they overlap or not
        if (!overlapping){
            return false;
        } else {
            return true;
        }

        /*
        float horizontal;
        if (ent.getX() > colEnt.getX()){
            horizontal = ent.getX() - colEnt.getX();
        } else {
            horizontal = colEnt.getX() - ent.getX();
        }

        float vertical;
        if (ent.getY() > colEnt.getY()){
            vertical = ent.getY() - colEnt.getY();
        } else {
            vertical = colEnt.getY() - ent.getY();
        }

        //float horizontal = (float) Math.sqrt(Math.pow(x21, 2) + Math.pow(y21, 2));

        if (horizontal < (entSprite.getSizeWidth() + colEntSprite.getSizeWidth())){
            System.out.println("Horizontal collision between " + ent.getClass() + " and " + colEnt.getClass());
        }
        if (vertical < (entSprite.getSizeHeight() + colEntSprite.getSizeHeight())){
            System.out.println("Vertical collision between " + ent.getClass() + " and " + colEnt.getClass());
        }
        */
    }
}
