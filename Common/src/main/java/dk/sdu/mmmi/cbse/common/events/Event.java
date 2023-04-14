package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.Types.EventTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import java.io.Serializable;

/**
 *
 * @author Mads
 */
public class Event implements Serializable {

    private final Entity source;
    private EventTypes type;

    public Event(Entity source, EventTypes type) {
        this.source = source;
        this.type = type;
    }

    public Entity getSource() {
        return source;
    }

    public void setType(EventTypes type) { this.type = type; }

    public EventTypes getType() { return this.type; }
}
