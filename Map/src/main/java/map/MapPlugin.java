package map;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class MapPlugin implements IGamePluginService{

    private Entity map;
    @Override
    public void start(GameData gameData, World world) {
        map = CreateMap(gameData);
        world.addEntity(map);
    }

    private Entity CreateMap(GameData gameData){
        String spritePath = "assets/map.png";

        Entity map = new Map();
        map.add(new PositionPart(0,0));
        map.add(new SpritePart(spritePath,1000,600,gameData.getDisplayHeight(), gameData.getDisplayWidth(),0));

        return map;
    }

    @Override
    public void stop(GameData gameData, World world) {

    }
}
