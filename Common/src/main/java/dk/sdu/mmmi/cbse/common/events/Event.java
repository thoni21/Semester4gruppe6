package dk.sdu.mmmi.cbse.common.events;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.services.IEventExecuteService;

import java.io.Serializable;

/**
 *
 * @author Mads
 */
public class Event implements Serializable, IEventExecuteService {

    private Entity source;

    public Entity getSource() {
        return this.source;
    }

    public void setSource(Entity source) {
        this.source = source;
    }

    @Override
    public void executeEvent() {

    }
}
