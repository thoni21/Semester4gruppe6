package dk.sdu.grp6.movetoai;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;

import java.util.List;

import static org.mockito.Mockito.*;

public class MoveToAIProcessTest {

    private MoveToAIProcess moveToAIProcess;
    private World world;
    private GameData gameData;
    private Entity entityWithAI;
    private PositionPart entityWithAIPosPart;
    private MovingPart entityWithAIMovePart;
    private Entity player;
    private PositionPart playerPos;

    @BeforeEach
    public void setUp() {
        moveToAIProcess = new MoveToAIProcess();
        world = mock(World.class);
        gameData = mock(GameData.class);
        entityWithAI = mock(Entity.class);
        entityWithAIPosPart = mock(PositionPart.class);
        entityWithAIMovePart = mock(MovingPart.class);
        player = mock(Entity.class);
        playerPos = mock(PositionPart.class);

        when(entityWithAI.getPart(PositionPart.class)).thenReturn(entityWithAIPosPart);
        when(entityWithAI.getPart(MovingPart.class)).thenReturn(entityWithAIMovePart);
        when(player.getPart(PositionPart.class)).thenReturn(playerPos);
    }

    @Test
    public void testProcess_Left() {
        when(world.getEntities()).thenReturn(List.of(player));
        when(player.getType()).thenReturn(EntityTypes.Player);

        // Set up player position to be on the left of the AI entity
        when(entityWithAIPosPart.getX()).thenReturn(10.0f);
        when(playerPos.getX()).thenReturn(5.0f);

        // Call the process method
        moveToAIProcess.process(world, gameData, entityWithAI);

        // Verify that the MovingPart is updated correctly
        verify(entityWithAIMovePart).setLeft(true);
        verify(entityWithAIMovePart).setRight(false);

        // Verify that the process method is called
        verify(entityWithAIMovePart).process(gameData, entityWithAI);
    }

    @Test
    public void testProcess_Right() {
        when(world.getEntities()).thenReturn(List.of(player));
        when(player.getType()).thenReturn(EntityTypes.Player);

        // Set up player position to be on the left of the AI entity
        when(entityWithAIPosPart.getX()).thenReturn(5.0f);
        when(playerPos.getX()).thenReturn(10.0f);

        // Call the process method
        moveToAIProcess.process(world, gameData, entityWithAI);

        // Verify that the MovingPart is updated correctly
        verify(entityWithAIMovePart).setLeft(false);
        verify(entityWithAIMovePart).setRight(true);

        // Verify that the process method is called
        verify(entityWithAIMovePart).process(gameData, entityWithAI);
    }

    @Test
    public void testProcess_Up() {
        when(world.getEntities()).thenReturn(List.of(player));
        when(player.getType()).thenReturn(EntityTypes.Player);

        // Set up player position to be on the left of the AI entity
        when(entityWithAIPosPart.getY()).thenReturn(5.0f);
        when(playerPos.getY()).thenReturn(10.0f);

        // Call the process method
        moveToAIProcess.process(world, gameData, entityWithAI);

        // Verify that the MovingPart is updated correctly
        verify(entityWithAIMovePart).setUp(true);
        verify(entityWithAIMovePart).setDown(false);

        // Verify that the process method is called
        verify(entityWithAIMovePart).process(gameData, entityWithAI);
    }

    @Test
    public void testProcess_Down() {
        when(world.getEntities()).thenReturn(List.of(player));
        when(player.getType()).thenReturn(EntityTypes.Player);

        // Set up player position to be on the left of the AI entity
        when(entityWithAIPosPart.getY()).thenReturn(10.0f);
        when(playerPos.getY()).thenReturn(5.0f);

        // Call the process method
        moveToAIProcess.process(world, gameData, entityWithAI);

        // Verify that the MovingPart is updated correctly
        verify(entityWithAIMovePart).setUp(false);
        verify(entityWithAIMovePart).setDown(true);

        // Verify that the process method is called
        verify(entityWithAIMovePart).process(gameData, entityWithAI);
    }

    @Test
    public void testProcess_PlayerTypeNotPlayer() {
        when(world.getEntities()).thenReturn(List.of(player));

        // Set up player type to be EntityTypes.Player
        when(player.getType()).thenReturn(EntityTypes.Aura);

        // Call the process method
        moveToAIProcess.process(world, gameData, entityWithAI);

        // Verify that the MovingPart is not updated
        verify(entityWithAIMovePart, never()).setLeft(anyBoolean());
        verify(entityWithAIMovePart, never()).setRight(anyBoolean());
        verify(entityWithAIMovePart, never()).setUp(anyBoolean());
        verify(entityWithAIMovePart, never()).setDown(anyBoolean());

        // Verify that the process method is called
        verify(entityWithAIMovePart).process(gameData, entityWithAI);
    }
}