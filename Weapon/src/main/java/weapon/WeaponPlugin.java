package weapon;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class WeaponPlugin implements IGamePluginService {

    private Entity weapon;
    @Override
    public void start(GameData gameData, World world) {
        weapon = createEnemy(gameData);
        world.addEntity(weapon);
    }

    private Entity createEnemy(GameData gameData){

        // Sprite attributes
        // int spriteStartPosX = 32; -- Sprite start position X in sprite sheet
        // int spriteStartPosY = 600; -- // Sprite start position Y in sprite sheet
        // int spriteWidth = 32;  --   // Sprite width in sprite sheet
        // int spriteHeight = 44;  --  // Sprite height in sprite sheet
        // int spriteLayer = 2;  --   // TODO: maybe convert layers to an enum

        // Movement attributes
        float x = Float.MAX_VALUE; // Any other way to spawn weapon, perhaps on player? How does one get access to player and player attributes?
        float y = Float.MAX_VALUE; // Any other way to spawn weapon, perhaps on player? How does one get access to player and player attributes?

        Entity weapon = new Weapon();
        // weapon.setType(EntityTypes.defaultWeapon); // Add weapon types
        weapon.add(new PositionPart(x, y));
        // weapon.add(new SpritePart(spriteStartPosX, spriteStartPosY, spriteWidth,spriteHeight,spriteHeight, spriteWidth, spriteLayer));

        return weapon;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(weapon);
    }
}
