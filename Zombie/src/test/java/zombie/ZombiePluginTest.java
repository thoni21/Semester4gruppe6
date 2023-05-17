package zombie;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.*;
import zombie.Zombie;
import zombie.ZombiePlugin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ZombiePluginTest {
    private static GameData mockedGameData;
    private static World mockedWorld;
    private static ZombiePlugin zombiePlugin;

    @BeforeAll
    static void setUp() {
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);
        zombiePlugin = new ZombiePlugin();
    }

    @Test
    @Order(1)
    void start(){
        zombiePlugin.start(mockedGameData,mockedWorld);

        verify(mockedWorld).addEntity(any(Zombie.class));
    }

    @Test
    @Order(2)
    void stop(){
        zombiePlugin.stop(mockedGameData,mockedWorld);

        verify(mockedWorld).removeEntity(any(Zombie.class));
    }
}
