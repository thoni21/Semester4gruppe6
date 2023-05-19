package dk.sdu.grp6.infernoaura;

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
import java.util.Timer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfernoAuraProcessTest {
    @Mock
    private GameData mockedGameData;
    @Mock
    private World mockedWorld;
    private InfernoAuraProcess infernoAuraProcess;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        infernoAuraProcess = new InfernoAuraProcess();
    }

    private Entity mockPlayer () {
        // Mock player entity
        Entity player = mock(Entity.class);
        when(player.getType()).thenReturn(EntityTypes.Player);

        return player;
    }

    private Entity mockAura () {
        // Mock aura entity
        Entity infernoAura = mock(Entity.class);

        return infernoAura;
    }

    private Entity mockWeapon() {
        // Mock weapon entity
        Entity weapon = mock(Entity.class);

        return weapon;
    }

    private PositionPart mockPositionPart (Entity entity) {
        // Mock player position part
        PositionPart positionPart = mock(PositionPart.class);
        when(entity.getPart(PositionPart.class)).thenReturn(positionPart);

        return positionPart;
    }

    private LifePart mockLifePart (Entity entity) {
        // Mock player life part
        LifePart lifePart = mock(LifePart.class);
        when(entity.getPart(LifePart.class)).thenReturn(lifePart);

        return lifePart;
    }

    private TimerPart mockTimerPart (Entity entity) {
        // Mock player timer part
        TimerPart timerPart = mock(TimerPart.class);
        when(entity.getPart(TimerPart.class)).thenReturn(timerPart);

        return timerPart;
    }

    private SpritePart mockSpritePart (Entity entity) {
        // Mock player sprite part
        SpritePart spritePart = mock(SpritePart.class);
        when(entity.getPart(SpritePart.class)).thenReturn(spritePart);

        return spritePart;
    }

    private void mockDamagePart (Entity entity) {
        // Mock player damage part
        DamagePart damagePart = mock(DamagePart.class);
        when(entity.getPart(DamagePart.class)).thenReturn(damagePart);
    }

    @Test
    void testCreateBulletProcess() {
        Entity mockedWeapon = mockWeapon();

        mockPositionPart(mockedWeapon);

        Entity createdAura = infernoAuraProcess.createBullet(mockedWeapon, mockedGameData);

        // Check if the method returns and instance of the InfernoAura class
        assertInstanceOf(InfernoAura.class, createdAura);
    }

    @Test
    void testInfernoAuraProcessIsDeadAndRemoved() {
        // Aura Mock
        Entity mockedInfernoAura = mockAura();
        when(mockedWorld.getEntities(InfernoAura.class)).thenReturn(List.of(mockedInfernoAura));

        LifePart lifePart = mockLifePart(mockedInfernoAura);
        when(lifePart.isDead()).thenReturn(true);

        mockTimerPart(mockedInfernoAura);

        mockSpritePart(mockedInfernoAura);

        mockDamagePart(mockedInfernoAura);

        mockPositionPart(mockedInfernoAura);

        infernoAuraProcess.process(mockedGameData, mockedWorld);

        // Checks
        // Check if the inferno aura is found in the world
        verify(mockedWorld, times(1)).getEntities(InfernoAura.class);

        // Check if the inferno aura gets removed
        verify(mockedWorld).removeEntity(mockedInfernoAura);
    }

    @Test
    void testInfernoAuraProcess() {
        // Player Mock
        Entity mockedPlayer = mockPlayer();
        mockedPlayer.setType(EntityTypes.Player);
        when(mockedWorld.getEntities()).thenReturn(List.of(mockedPlayer));

        PositionPart positionPartPlayer = mockPositionPart(mockedPlayer);
        when(mockedPlayer.getPart(PositionPart.class)).thenReturn(positionPartPlayer);

        mockSpritePart(mockedPlayer);

        // Aura Mock
        Entity mockedInfernoAura = mockAura();
        when(mockedWorld.getEntities(InfernoAura.class)).thenReturn(List.of(mockedInfernoAura));

        LifePart lifePart = mockLifePart(mockedInfernoAura);
        when(lifePart.isDead()).thenReturn(false);

        TimerPart timerPartAura = mockTimerPart(mockedInfernoAura);
        when(mockedInfernoAura.getPart(TimerPart.class)).thenReturn(timerPartAura);

        SpritePart spritePartAura = mockSpritePart(mockedInfernoAura);
        when(mockedInfernoAura.getPart(SpritePart.class)).thenReturn(spritePartAura);

        PositionPart positionPartAura = mockPositionPart(mockedInfernoAura);
        when(mockedInfernoAura.getPart(PositionPart.class)).thenReturn(positionPartAura);

        // Run process
        infernoAuraProcess.process(mockedGameData, mockedWorld);

        // Checks
        // Check that the player is found
        assertEquals(mockedPlayer.getType(), EntityTypes.Player);

        // Check that the aura position is updated with a float
        verify(positionPartAura).setX(anyFloat());
        verify(positionPartAura).setY(anyFloat());

        // Check timer part process runs
        verify(timerPartAura).process(mockedGameData, mockedInfernoAura);

        // Check that the sprite opacity is getting updated
        verify(spritePartAura).setOpacity(anyFloat());

        // Check life part process runs
        verify(lifePart).process(mockedGameData, mockedInfernoAura);
    }
}
