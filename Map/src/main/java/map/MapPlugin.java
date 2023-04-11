package map;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class MapPlugin implements IGamePluginService {

    private Entity map;
    @Override
    public void start(GameData gameData, World world) {
        map = CreateMap(gameData);
        world.addEntity(map);
    }

    private Entity CreateMap(GameData gameData){
        int spriteStartPosX = 0; // Sprite start position X in sprite sheet
        int spriteStartPosY = 0; // Sprite start position Y in sprite sheet
        int spriteWidth = 1000;     // Sprite width in sprite sheet
        int spriteHeight = 600;    // Sprite height in sprite sheet
        int spriteLayer = 0;     // TODO: maybe convert layers to an enum

        Entity map = new Map();
        map.add(new PositionPart(0,0));
        map.add(new SpritePart(spriteStartPosX, spriteStartPosY, spriteWidth,spriteHeight,gameData.getDisplayHeight()/10.0f, gameData.getDisplayWidth()/10.0f, spriteLayer));

        return map;
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
