import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import map.Map;
import map.MapPlugin;
import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapPluginTest {
    private static GameData mockedGameData;
    private static World mockedWorld;
    private static MapPlugin mapPlugin;

    @BeforeAll
    static void setUp() {
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);
        mapPlugin = new MapPlugin();
    }
    /*
    @Test
    @Order(1)
    void start(){
        mapPlugin.start(mockedGameData,mockedWorld);

        verify(mockedWorld).addEntity(any(Map.class));
    }

    @Test
    @Order(2)
    void stop(){}
    */
}
