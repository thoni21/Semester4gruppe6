package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.services.IEventExecuteService;

import java.io.Serializable;

/**
 *
 * @author Mads
 */
public class Event implements Serializable, IEventExecuteService {

    private final Entity source;

    public Event(Entity source) {
        this.source = source;
    }

    public Entity getSource() {
        return source;
    }

    @Override
    public void executeEvent() {

    }
}
