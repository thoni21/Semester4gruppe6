package dk.sdu.grp6.weapon;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class WeaponProcessTest {
    @Mock
    private GameData mockedGameData;
    @Mock
    private World mockedWorld;
    private WeaponProcessing weaponProcess;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        weaponProcess = new WeaponProcessing();
    }

    private Entity mockPlayer () {
        // Mock player entity
        Entity player = mock(Entity.class);
        when(player.getType()).thenReturn(EntityTypes.Player);

        return player;
    }

    private Entity mockWeapon() {
        // Mock weapon entity
        Entity weapon = mock(Entity.class);

        return weapon;
    }

    private PositionPart mockPositionPart (Entity entity) {
        // Mock entity position part
        PositionPart positionPart = mock(PositionPart.class);
        when(entity.getPart(PositionPart.class)).thenReturn(positionPart);

        return positionPart;
    }

    private LifePart mockLifePart (Entity entity) {
        // Mock entity life part
        LifePart lifePart = mock(LifePart.class);
        when(entity.getPart(LifePart.class)).thenReturn(lifePart);

        return lifePart;
    }

    private SpritePart mockSpritePart (Entity entity) {
        // Mock entity sprite part
        SpritePart spritePart = mock(SpritePart.class);
        when(entity.getPart(SpritePart.class)).thenReturn(spritePart);

        return spritePart;
    }

    @Test
    void testInfernoAuraProcess() {
        // Player Mock
        Entity mockedPlayer = mockPlayer();
        mockedPlayer.setType(EntityTypes.Player);
        when(mockedWorld.getEntities()).thenReturn(List.of(mockedPlayer));

        LifePart lifePartPlayer = mockLifePart(mockedPlayer);
        when(lifePartPlayer.isDead()).thenReturn(false);

        PositionPart positionPartPlayer = mockPositionPart(mockedPlayer);
        when(mockedPlayer.getPart(PositionPart.class)).thenReturn(positionPartPlayer);

        mockSpritePart(mockedPlayer);

        // Weapon Mock
        Entity mockedWeapon = mockWeapon();
        when(mockedWorld.getEntities(Weapon.class)).thenReturn(List.of(mockedWeapon));

        SpritePart spritePartWeapon = mockSpritePart(mockedWeapon);
        when(mockedWeapon.getPart(SpritePart.class)).thenReturn(spritePartWeapon);

        PositionPart positionPartWeapon = mockPositionPart(mockedWeapon);
        when(mockedWeapon.getPart(PositionPart.class)).thenReturn(positionPartWeapon);

        // Run process
        weaponProcess.process(mockedGameData, mockedWorld);

        // Checks
        // Check that the player is found
        assertEquals(mockedPlayer.getType(), EntityTypes.Player);

        // Check that the aura position is updated with a float
        verify(positionPartWeapon).setX(anyFloat());
        verify(positionPartWeapon).setY(anyFloat());
    }

    // TODO added test for spawning bullet/aura
}
