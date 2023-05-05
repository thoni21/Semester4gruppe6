import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.collision.CollisionDetection;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


module Collision {
    requires Common;
    requires com.badlogic.gdx;
    requires java.datatransfer;
    requires java.desktop;


    provides IPostEntityProcessingService with CollisionDetection;
}