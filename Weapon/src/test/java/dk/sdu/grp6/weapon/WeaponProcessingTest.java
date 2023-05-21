package dk.sdu.grp6.weapon;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.commonbullet.BulletSPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeaponProcessingTest {
    private World world;
    private GameData gameData;
    private Entity playerEntity;
    private LifePart playerLifePart;
    private PositionPart playerPositionPart;
    private SpritePart playerSpritePart;
    private Entity weaponEntity;
    private PositionPart weaponPositionPart;
    private SpritePart weaponSpritePart;
    private Entity auraEntity;
    private BulletSPI bulletSPI;

    private WeaponProcessing weaponProcessing;

    @BeforeEach
    public void setup() {
        world = mock(World.class);
        gameData = mock(GameData.class);
        playerEntity = mock(Entity.class);
        playerLifePart = mock(LifePart.class);
        playerPositionPart = mock(PositionPart.class);
        playerSpritePart = mock(SpritePart.class);
        weaponEntity = mock(Weapon.class);
        weaponPositionPart = mock(PositionPart.class);
        weaponSpritePart = mock(SpritePart.class);
        auraEntity = mock(Entity.class);
        bulletSPI = mock(BulletSPI.class);

        // Set up the player entity and its components
        when(playerEntity.getType()).thenReturn(EntityTypes.Player);
        when(playerEntity.getPart(LifePart.class)).thenReturn(playerLifePart);
        when(playerEntity.getPart(PositionPart.class)).thenReturn(playerPositionPart);
        when(playerEntity.getPart(SpritePart.class)).thenReturn(playerSpritePart);

        // Set up the weapon entity and its components
        when(weaponEntity.getPart(PositionPart.class)).thenReturn(weaponPositionPart);
        when(weaponEntity.getPart(SpritePart.class)).thenReturn(weaponSpritePart);

        // Set up the world to return the player and weapon entities
        when(world.getEntities()).thenReturn(Arrays.asList(playerEntity, weaponEntity, auraEntity));

        // Create and set up the mock BulletSPI
        bulletSPI = mock(BulletSPI.class);
        when(bulletSPI.createBullet(weaponEntity, gameData)).thenReturn(mock(Entity.class));

        // Create the WeaponProcessing instance
        weaponProcessing = new WeaponProcessing();
        weaponProcessing.bulletSPIs = Collections.singletonList(bulletSPI);
    }

    @Test
    public void testProcess_PlayerEntityIsNull_RemovesWeapon() {
        // Set the player entity to null
        when(playerLifePart.isDead()).thenReturn(true);

        // Process the weapon
        weaponProcessing.process(gameData, world);

        // Verify that the weapon entity is removed
        verify(world).removeEntity(any(Weapon.class));
    }

    @Test
    public void testProcess_PlayerEntityIsDead_RemovesWeapon() {
        // Arrange
        when(playerLifePart.isDead()).thenReturn(true);

        // Act
        weaponProcessing.process(gameData, world);

        // Assert
        verify(world).removeEntity(eq(weaponEntity));
    }

    @Test
    public void testProcess_WeaponEntityExists_UpdatesWeaponPosition() {
        // Arrange
        when(playerPositionPart.getX()).thenReturn(100f);
        when(playerSpritePart.getSrcWidth()).thenReturn(50);
        when(playerPositionPart.getY()).thenReturn(200f);
        when(playerSpritePart.getSrcHeight()).thenReturn(60);
        when(weaponSpritePart.getSrcHeight()).thenReturn(30);

        // Act
        weaponProcessing.process(gameData, world);

        // Assert
        verify(weaponPositionPart).setX(eq(75f));
        verify(weaponPositionPart).setY(eq(260f));
    }

    @Test
    public void testProcess_AuraEntityIsNull_CreatesAuraEntities() {
        // Arrange
        when(world.getEntities()).thenReturn(Arrays.asList(playerEntity, weaponEntity));
        when(world.getEntities(eq(Weapon.class))).thenReturn(Collections.emptyList());

        // Act
        weaponProcessing.process(gameData, world);

        // Assert
        verify(world).addEntity(any(Entity.class));
        verify(bulletSPI).createBullet(eq(weaponEntity), eq(gameData));
    }

    @Test
    public void testProcess_AuraEntityExists_DoesNotCreateAuraEntities() {
        // Arrange
        when(world.getEntities()).thenReturn(Arrays.asList(playerEntity, weaponEntity));
        when(world.getEntities(eq(Weapon.class))).thenReturn(List.of(mock(Entity.class)));

        // Act
        weaponProcessing.process(gameData, world);

        // Assert
        verify(world, never()).addEntity(any(Entity.class));
        verify(bulletSPI, never()).createBullet(any(Entity.class), any(GameData.class));
    }
}
