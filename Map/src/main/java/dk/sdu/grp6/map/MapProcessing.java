package dk.sdu.grp6.map;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class MapProcessing implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        // Make sure the entities can't leave the map
        for (Entity map : world.getEntities(Map.class)) {
            PositionPart mapPosPart = map.getPart(PositionPart.class);
            SpritePart mapSpritePart = map.getPart(SpritePart.class);

            for (Entity entity : world.getEntities()) {
                PositionPart entityPosPart = entity.getPart(PositionPart.class);
                SpritePart entitySpritePart = entity.getPart(SpritePart.class);

                // TODO: Aura is hardcoded to not be restricted by the border - May change
                if (mapPosPart.getX() > entityPosPart.getX() && entity.getType() != EntityTypes.Aura) {
                    // Entity at left border
                    entityPosPart.setX(mapPosPart.getX());
                }
                if (mapPosPart.getX() + mapSpritePart.getSrcWidth() < entityPosPart.getX() + entitySpritePart.getSizeWidth() && entity.getType() != EntityTypes.Aura) {
                    // Entity at right border
                    entityPosPart.setX(mapPosPart.getX() + mapSpritePart.getSrcWidth() - entitySpritePart.getSrcWidth());
                }
                if (mapPosPart.getY() > entityPosPart.getY() && entity.getType() != EntityTypes.Aura) {
                    // Entity at top border
                    entityPosPart.setY(mapPosPart.getY());
                }
                if (mapPosPart.getY() + mapSpritePart.getSrcHeight() < entityPosPart.getY() + entitySpritePart.getSizeHeight() && entity.getType() != EntityTypes.Aura) {
                    // Entity at bottom border
                    entityPosPart.setY(mapPosPart.getY() + mapSpritePart.getSrcHeight() - entitySpritePart.getSrcHeight());
                }
            }
        }
    }
}
