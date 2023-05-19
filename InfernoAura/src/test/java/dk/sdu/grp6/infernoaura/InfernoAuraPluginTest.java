package dk.sdu.grp6.infernoaura;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InfernoAuraPluginTest {
    private static GameData mockedGameData;
    private static World mockedWorld;
    private static InfernoAuraPlugin infernoAuraPlugin;

    @BeforeAll
    static void setUp() {
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);
        infernoAuraPlugin = new InfernoAuraPlugin();
    }

    /* Start method not implemented in class
    @Test
    @Order(1)
    void start(){
        infernoAuraPlugin.start(mockedGameData,mockedWorld);

        verify(mockedWorld).addEntity(any(InfernoAura.class));
    }

    @Test
    @Order(2)
    void stop(){
        infernoAuraPlugin.stop(mockedGameData,mockedWorld);

        verify(mockedWorld).removeEntity(any(InfernoAura.class));
    }
    */
}
