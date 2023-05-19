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

    private void mockTimerPart (Entity entity) {
        // Mock player timer part
        TimerPart timerPart = mock(TimerPart.class);
        when(entity.getPart(TimerPart.class)).thenReturn(timerPart);
    }

    private void mockSpritePart (Entity entity) {
        // Mock player sprite part
        SpritePart spritePart = mock(SpritePart.class);
        when(entity.getPart(SpritePart.class)).thenReturn(spritePart);
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

        // Checks
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
        verify(mockedWorld, times(1)).getEntities(InfernoAura.class);
        verify(mockedWorld).removeEntity(mockedInfernoAura);
    }

    @Test
    void testInfernoAuraProcessMovedToPlayer() {
        // Player Mock
        Entity mockedPlayer = mockPlayer();
        mockedPlayer.setType(EntityTypes.Player);
        when(mockedWorld.getEntities()).thenReturn(List.of(mockedPlayer));

        PositionPart positionPartPlayer = mockPositionPart(mockedPlayer);
        positionPartPlayer.setX(200f);
        positionPartPlayer.setY(200f);
        when(mockedPlayer.getPart(PositionPart.class)).thenReturn(positionPartPlayer);

        mockSpritePart(mockedPlayer);

        // Aura Mock
        Entity mockedInfernoAura = mockAura();
        when(mockedWorld.getEntities(InfernoAura.class)).thenReturn(List.of(mockedInfernoAura));

        LifePart lifePart = mockLifePart(mockedInfernoAura);
        when(lifePart.isDead()).thenReturn(false);

        mockTimerPart(mockedInfernoAura);

        mockSpritePart(mockedInfernoAura);

        mockDamagePart(mockedInfernoAura);

        PositionPart positionPartAura = mockPositionPart(mockedInfernoAura);
        positionPartAura.setX(0f);
        positionPartAura.setY(0f);
        when(mockedInfernoAura.getPart(PositionPart.class)).thenReturn(positionPartAura);

        // Checks
        // TODO Check if player and aura position is not the same
        // assertNotEquals(positionPartAura.getX(), positionPartPlayer.getX());
        // assertNotEquals(positionPartAura.getY(), positionPartPlayer.getY());

        // Run process
        infernoAuraProcess.process(mockedGameData, mockedWorld);

        // Checks
        // TODO Check if player and aura position is the same
        // assertEquals(positionPartAura.getX(), positionPartPlayer.getX());
        // assertEquals(positionPartAura.getY(), positionPartPlayer.getY());
    }
}
