package player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class PlayerMovementTest {
    @Mock
    private GameData mockedGameData;
    @Mock
    private World mockedWorld;
    private PlayerMovement playerMovement;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        playerMovement = new PlayerMovement();
    }

    private Entity mockPlayer () {
        // Mock player entity
        Entity player = mock(Entity.class);
        when(mockedWorld.getEntities(Player.class)).thenReturn(List.of(player));

        return player;
    }

    private MovingPart mockMovingPart (Entity player) {
        // Mock player moving part
        MovingPart movingPart = mock(MovingPart.class);
        when(player.getPart(MovingPart.class)).thenReturn(movingPart);

        return movingPart;
    }

    private void mockPositionPart (Entity player) {
        // Mock player position parts
        PositionPart positionPart = mock(PositionPart.class);
        when(player.getPart(PositionPart.class)).thenReturn(positionPart);
    }

    private LifePart mockLifePart (Entity player, boolean isDead) {
        // Mock player position parts
        LifePart lifePart = mock(LifePart.class);
        when(player.getPart(LifePart.class)).thenReturn(lifePart);
        when(lifePart.isDead()).thenReturn(isDead);

        return lifePart;
    }

    private void mockGameKeys (
            boolean isLeft,
            boolean isRight,
            boolean isUp,
            boolean isDown
    ) {

        // Mock game keys
        GameKeys gameKeys = mock(GameKeys.class);
        when(mockedGameData.getKeys()).thenReturn(gameKeys);
        if (isLeft) {
            when(gameKeys.isDown(GameKeys.LEFT)).thenReturn(true);
        }
        if (isRight) {
            when(gameKeys.isDown(GameKeys.RIGHT)).thenReturn(true);
        }
        if (isUp) {
            when(gameKeys.isDown(GameKeys.UP)).thenReturn(true);
        }
        if (isDown) {
            when(gameKeys.isDown(GameKeys.DOWN)).thenReturn(true);
        }
    }

    @Test
    public void testProcessPlayerAliveMovingPartMoveUp() {
        Entity mockedPlayer = mockPlayer();

        MovingPart mockedMovingPart = mockMovingPart(mockedPlayer);

        mockPositionPart(mockedPlayer);

        mockLifePart(mockedPlayer, false);

        mockGameKeys (
                false,
                false,
                true,
                false
        );

        // Call process method
        playerMovement.process(mockedGameData, mockedWorld);

        // Verify that the MovingPart is updated correctly
        verify(mockedMovingPart).setLeft(false);
        verify(mockedMovingPart).setRight(false);
        verify(mockedMovingPart).setUp(true);
        verify(mockedMovingPart).setDown(false);
    }

    @Test
    public void testProcessPlayerAliveMovingPartMoveDown() {
        Entity mockedPlayer = mockPlayer();

        MovingPart mockedMovingPart = mockMovingPart(mockedPlayer);

        mockPositionPart(mockedPlayer);

        mockLifePart(mockedPlayer, false);

        mockGameKeys (
                false,
                false,
                false,
                true
        );

        // Call process method
        playerMovement.process(mockedGameData, mockedWorld);

        // Verify that the MovingPart is updated correctly
        verify(mockedMovingPart).setLeft(false);
        verify(mockedMovingPart).setRight(false);
        verify(mockedMovingPart).setUp(false);
        verify(mockedMovingPart).setDown(true);
    }

    @Test
    public void testProcessPlayerAliveMovingPartMoveLeft() {
        Entity mockedPlayer = mockPlayer();

        MovingPart mockedMovingPart = mockMovingPart(mockedPlayer);

        mockPositionPart(mockedPlayer);

        mockLifePart(mockedPlayer, false);

        mockGameKeys (
                true,
                false,
                false,
                false
        );

        // Call process method
        playerMovement.process(mockedGameData, mockedWorld);

        // Verify that the MovingPart is updated correctly
        verify(mockedMovingPart).setLeft(true);
        verify(mockedMovingPart).setRight(false);
        verify(mockedMovingPart).setUp(false);
        verify(mockedMovingPart).setDown(false);
    }

    @Test
    public void testProcessPlayerAliveMovingPartMoveRight() {
        Entity mockedPlayer = mockPlayer();

        MovingPart mockedMovingPart = mockMovingPart(mockedPlayer);

        mockPositionPart(mockedPlayer);

        mockLifePart(mockedPlayer, false);

        mockGameKeys (
                false,
                true,
                false,
                false
        );

        // Call process method
        playerMovement.process(mockedGameData, mockedWorld);

        // Verify that the MovingPart is updated correctly
        verify(mockedMovingPart).setLeft(false);
        verify(mockedMovingPart).setRight(true);
        verify(mockedMovingPart).setUp(false);
        verify(mockedMovingPart).setDown(false);
    }

    @Test
    public void testProcessPlayerDeadPlayerRemoved() {
        Entity mockedPlayer = mockPlayer();

        mockMovingPart(mockedPlayer);

        mockPositionPart(mockedPlayer);

        mockLifePart(mockedPlayer, true);

        mockGameKeys(
                false,
                false,
                false,
                false
        );

        // Call process method
        playerMovement.process(mockedGameData, mockedWorld);

        // Verify that the player entity is removed from the world
        verify(mockedWorld).removeEntity(mockedPlayer);
    }
}
