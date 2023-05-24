package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jan Corfixen
 * <a href="https://github.com/sweat-tek/AsteroidsJPMS/tree/lwjgl3">Repo</a>
 */
public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    private EntityTypes type;
    private Map<Class, EntityPart> parts;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public String getID() {
        return ID.toString();
    }

    public void setType(EntityTypes type) {
        this.type = type;
    }

    public EntityTypes getType() {
        return type;
    }
}
