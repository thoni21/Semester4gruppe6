import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.grp6.collision.CollisionDetection;


module Collision {
    requires Common;
    requires com.badlogic.gdx;
    requires java.datatransfer;
    requires java.desktop;


    provides IPostEntityProcessingService with CollisionDetection;
}