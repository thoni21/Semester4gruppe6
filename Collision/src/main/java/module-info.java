import dk.sdu.mmmi.cbse.CollisionDetection;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


module Collision {
    requires Common;
    requires com.badlogic.gdx;
    requires java.datatransfer;
    requires java.desktop;
    requires Player;
    requires Enemy;

    provides  IEntityProcessingService with CollisionDetection;
}