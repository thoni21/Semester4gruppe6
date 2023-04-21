package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.Types.EventTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;

public class CollisionEvent extends Event{
    public CollisionEvent(Entity source, EventTypes type) {
        super(source, type);
    }

    public void execute() {

    }
}
