package dk.sdu.grp6.map;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class MapProcessingTest {
    private MapProcessing mapProcessing;
    private GameData mockedGameData;
    private World mockedWorld;

    private Entity mapEntity;
    private PositionPart mapPosPart;
    private SpritePart mapSpritePart;

    private Entity entity;
    private PositionPart entityPosPart;
    private SpritePart entitySpritePart;

    @BeforeEach
    void setUp() {
        mapProcessing = new MapProcessing();
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);

        // Mock map entity
        mapEntity = mock(Entity.class);
        mapPosPart = mock(PositionPart.class);
        mapSpritePart = mock(SpritePart.class);
        when(mapEntity.getPart(PositionPart.class)).thenReturn(mapPosPart);
        when(mapEntity.getPart(SpritePart.class)).thenReturn(mapSpritePart);

        // Mock entity
        entity = mock(Entity.class);
        entityPosPart = mock(PositionPart.class);
        entitySpritePart = mock(SpritePart.class);
        when(entity.getPart(PositionPart.class)).thenReturn(entityPosPart);
        when(entity.getPart(SpritePart.class)).thenReturn(entitySpritePart);

        // Add mock entities to the world
        when(mockedWorld.getEntities(Map.class)).thenReturn(List.of(mapEntity));
        when(mockedWorld.getEntities()).thenReturn(List.of(entity));
    }

    @Test
    void testEntityAtLeftBorder() {
        // Mock map entity
        when(mapPosPart.getX()).thenReturn(0f);
        when(mapSpritePart.getSrcWidth()).thenReturn(100);

        // Mock entity outside left border
        when(entityPosPart.getX()).thenReturn(-10f);
        when(entity.getType()).thenReturn(EntityTypes.Player);

        // Perform the processing
        mapProcessing.process(mockedGameData, mockedWorld);

        // Verify that the entity's X position is set to the map's X position
        verify(entityPosPart).setX(0f);
    }

    @Test
    void testEntityAtRightBorder() {
        // Mock map entity
        when(mapPosPart.getX()).thenReturn(0f);
        when(mapSpritePart.getSrcWidth()).thenReturn(100);

        // Mock entity outside right border
        when(entityPosPart.getX()).thenReturn(110f);
        when(entitySpritePart.getSrcWidth()).thenReturn(20);
        when(entity.getType()).thenReturn(EntityTypes.Player);

        // Perform the processing
        mapProcessing.process(mockedGameData, mockedWorld);

        // Verify that the entity's X position is adjusted to fit within the map's boundaries
        verify(entityPosPart).setX(80f);
    }

    @Test
    void testEntityAtTopBorder() {
        // Mock map entity
        when(mapPosPart.getY()).thenReturn(0f);
        when(mapSpritePart.getSrcHeight()).thenReturn(100);

        // Mock entity outside top border
        when(entityPosPart.getY()).thenReturn(-10f);
        when(entity.getType()).thenReturn(EntityTypes.Player);

        // Perform the processing
        mapProcessing.process(mockedGameData, mockedWorld);

        // Verify that the entity's Y position is set to the map's Y position
        verify(entityPosPart).setY(0f);
    }

    @Test
    void testEntityAtBottomBorder() {
        // Mock map entity
        when(mapPosPart.getY()).thenReturn(0f);
        when(mapSpritePart.getSrcHeight()).thenReturn(100);

        // Mock entity outside bottom border
        when(entityPosPart.getY()).thenReturn(110f);
        when(entitySpritePart.getSrcHeight()).thenReturn(20);
        when(entity.getType()).thenReturn(EntityTypes.Player);

        // Perform the processing
        mapProcessing.process(mockedGameData, mockedWorld);

        // Verify that the entity's Y position is adjusted to fit within the map's boundaries
        verify(entityPosPart).setY(80f);
    }

    @Test
    void testEntityAtLeftBorderAuraEntity() {
        // Mock map entity
        when(mapPosPart.getX()).thenReturn(0f);
        when(mapSpritePart.getSrcWidth()).thenReturn(100);

        // Mock entity outside left border
        when(entityPosPart.getX()).thenReturn(-10f);
        when(entity.getType()).thenReturn(EntityTypes.Aura);

        // Perform the processing
        mapProcessing.process(mockedGameData, mockedWorld);

        // Verify that the entity's X position is set to the map's X position
        verify(entityPosPart, never()).setX(0f);
    }

    @Test
    void testEntityAtRightBorderAuraEntity() {
        // Mock map entity
        when(mapPosPart.getX()).thenReturn(0f);
        when(mapSpritePart.getSrcWidth()).thenReturn(100);

        // Mock entity outside right border
        when(entityPosPart.getX()).thenReturn(110f);
        when(entitySpritePart.getSrcWidth()).thenReturn(20);
        when(entity.getType()).thenReturn(EntityTypes.Aura);

        // Perform the processing
        mapProcessing.process(mockedGameData, mockedWorld);

        // Verify that the entity's X position is adjusted to fit within the map's boundaries
        verify(entityPosPart, never()).setX(80f);
    }

    @Test
    void testEntityAtTopBorderAuraEntity() {
        // Mock map entity
        when(mapPosPart.getY()).thenReturn(0f);
        when(mapSpritePart.getSrcHeight()).thenReturn(100);

        // Mock entity outside top border
        when(entityPosPart.getY()).thenReturn(-10f);
        when(entity.getType()).thenReturn(EntityTypes.Aura);

        // Perform the processing
        mapProcessing.process(mockedGameData, mockedWorld);

        // Verify that the entity's Y position is set to the map's Y position
        verify(entityPosPart, never()).setY(0f);
    }

    @Test
    void testEntityAtBottomBorderAuraEntity() {
        // Mock map entity
        when(mapPosPart.getY()).thenReturn(0f);
        when(mapSpritePart.getSrcHeight()).thenReturn(100);

        // Mock entity outside bottom border
        when(entityPosPart.getY()).thenReturn(110f);
        when(entitySpritePart.getSrcHeight()).thenReturn(20);
        when(entity.getType()).thenReturn(EntityTypes.Aura);

        // Perform the processing
        mapProcessing.process(mockedGameData, mockedWorld);

        // Verify that the entity's Y position is adjusted to fit within the map's boundaries
        verify(entityPosPart, never()).setY(80f);
    }
}
