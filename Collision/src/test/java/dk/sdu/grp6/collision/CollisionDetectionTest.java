package dk.sdu.grp6.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.events.CollisionEvent;
import dk.sdu.mmmi.cbse.common.events.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CollisionDetectionTest {
    private CollisionDetection collisionDetection;
    @Mock
    private World mockedWorld;
    @Mock
    private GameData mockedGameData;
    @BeforeEach
    void setUp() {
        collisionDetection = new CollisionDetection();

        MockitoAnnotations.initMocks(this);
    }

    private List<Entity> setupMocking(
            String entity1ID,
            float entity1X,
            float entity1Y,
            float entity1SpriteWidth,
            float entity1SpriteHeight,
            String entity2ID,
            float entity2X,
            float entity2Y,
            float entity2SpriteWidth,
            float entity2SpriteHeight
    ) {
        // Create mock entities with position and sprite parts
        Entity entity1 = mock(Entity.class);
        when(entity1.getID()).thenReturn(entity1ID);

        PositionPart positionPart1 = mock(PositionPart.class);
        when(entity1.getPart(PositionPart.class)).thenReturn(positionPart1);
        when(positionPart1.getX()).thenReturn(entity1X);
        when(positionPart1.getY()).thenReturn(entity1Y);

        SpritePart spritePart1 = mock(SpritePart.class);
        when(entity1.getPart(SpritePart.class)).thenReturn(spritePart1);
        when(spritePart1.getSizeWidth()).thenReturn(entity1SpriteWidth);
        when(spritePart1.getSizeHeight()).thenReturn(entity1SpriteHeight);

        Entity entity2 = mock(Entity.class);
        when(entity2.getID()).thenReturn(entity2ID);

        PositionPart positionPart2 = mock(PositionPart.class);
        when(entity2.getPart(PositionPart.class)).thenReturn(positionPart2);
        when(positionPart2.getX()).thenReturn(entity2X);
        when(positionPart2.getY()).thenReturn(entity2Y);

        SpritePart spritePart2 = mock(SpritePart.class);
        when(entity2.getPart(SpritePart.class)).thenReturn(spritePart2);
        when(spritePart2.getSizeWidth()).thenReturn(entity2SpriteWidth);
        when(spritePart2.getSizeHeight()).thenReturn(entity2SpriteHeight);

        List<Entity> entityList = new LinkedList<>();
        entityList.add(entity1);
        entityList.add(entity2);

        return entityList;
    }

    @Test
    void testCollisionDetection() {
        List<Entity> entityList = setupMocking(
                "1",
                0,
                0,
                100,
                100,
                "2",
                50,
                50,
                100,
                100
        );

        assertTrue(collisionDetection.collideWith(entityList.get(0), entityList.get(1)));
    }

    @Test
    void testNonCollisionDetection() {
        List<Entity> entityList = setupMocking(
                "1",
                0,
                0,
                100,
                100,
                "2",
                500,
                500,
                100,
                100
        );

        assertFalse(collisionDetection.collideWith(entityList.get(0), entityList.get(1)));
    }

    @Test
    void testCollisionDetectionWithSelf() {
        List<Entity> entityList = setupMocking(
                "1",
                0,
                0,
                100,
                100,
                "1",
                500,
                500,
                100,
                100
        );

        when(mockedWorld.getEntities()).thenReturn(entityList);

        collisionDetection.process(mockedGameData, mockedWorld);

        assertEquals(entityList.get(0).getID(), entityList.get(1).getID());
        verify(mockedGameData, times(0)).addEvent(any(Event.class));
    }

    @Test
    void testCollisionEventIsAdded() {
        List<Entity> entityList = setupMocking(
                "1",
                0,
                0,
                100,
                100,
                "2",
                50,
                50,
                100,
                100
        );

        when(mockedWorld.getEntities()).thenReturn(entityList);

        collisionDetection.process(mockedGameData, mockedWorld);

        // 2 times because both entities calls it.
        verify(mockedGameData, times(2)).addEvent(any(CollisionEvent.class));
    }
}