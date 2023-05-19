package dk.sdu.grp6.weapon;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WeaponPluginTest {
    private static GameData mockedGameData;
    private static World mockedWorld;
    private static WeaponPlugin weaponPlugin;

    @BeforeAll
    static void setUp() {
        mockedGameData = mock(GameData.class);
        mockedWorld = mock(World.class);
        weaponPlugin = new WeaponPlugin();
    }

    @Test
    @Order(1)
    void start(){
        weaponPlugin.start(mockedGameData,mockedWorld);

        verify(mockedWorld).addEntity(any(Weapon.class));
    }

    @Test
    @Order(2)
    void stop(){
        weaponPlugin.stop(mockedGameData,mockedWorld);

        verify(mockedWorld).removeEntity(any(Weapon.class));
    }
}
