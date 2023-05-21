package dk.sdu.grp6.zombie;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IArtificialIntelligenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class ZombieControlSystemTest {
    private static GameData mockedGameData;
    private static World mockedWorld;
    private static ZombieControlSystem zombieControlSystem;

    @BeforeEach
    void setUp() {
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);

        zombieControlSystem = new ZombieControlSystem();
    }

    @Test
    void testZombieIsDeadAndRemoved() {
        Entity mockedZombie = mock(Zombie.class);
        when(mockedWorld.getEntities(Zombie.class)).thenReturn(List.of(mockedZombie));

        LifePart lifePartZombie = mock(LifePart.class);
        when(mockedZombie.getPart(LifePart.class)).thenReturn(lifePartZombie);
        when(lifePartZombie.isDead()).thenReturn(true);

        zombieControlSystem.process(mockedGameData, mockedWorld);

        // Check Zombie is found
        verify(mockedWorld, times(1)).getEntities(Zombie.class);

        // Check that Zombie is removed from game world because its dead
        verify(mockedWorld).removeEntity(any(Zombie.class));
    }

    @Test
    void testZombieIsAliveAndCallAIProcess() {
        // Mock Zombie
        Entity mockedZombie = mock(Zombie.class);
        when(mockedWorld.getEntities(Zombie.class)).thenReturn(List.of(mockedZombie));

        LifePart lifePartZombie = mock(LifePart.class);
        when(mockedZombie.getPart(LifePart.class)).thenReturn(lifePartZombie);
        when(lifePartZombie.isDead()).thenReturn(false);

        // Mock AI Service
        IArtificialIntelligenceService mockedAiService = mock(IArtificialIntelligenceService.class);

        // Mock the zombieControlSystem object
        zombieControlSystem = new ZombieControlSystem() {
            @Override
            Collection<? extends IArtificialIntelligenceService> getArtificialIntelligenceServices() {
                return Collections.singletonList(mockedAiService);
            }
        };

        // Run the zombieControlSystem process
        zombieControlSystem.process(mockedGameData, mockedWorld);

        // Check Zombie is found
        verify(mockedWorld, times(1)).getEntities(Zombie.class);

        // Check that Zombie is not removed from game world because it's alive
        verify(mockedWorld, never()).removeEntity(any(Zombie.class));

        // TODO: Maybe test if the ai process is called
        // Check if the AI process method is called with the correct parameters
        verify(mockedAiService).process(mockedWorld, mockedGameData, mockedZombie);

        // Check that the zombie LifePart process is called
        verify(lifePartZombie).process(mockedGameData, mockedZombie);
    }
}
