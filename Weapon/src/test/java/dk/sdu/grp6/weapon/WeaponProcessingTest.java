package dk.sdu.grp6.weapon;

import dk.sdu.mmmi.cbse.common.Types.EntityTypes;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.commonbullet.BulletSPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeaponProcessingTest {
    private WeaponProcessing weaponProcessing;
    private GameData gameData;
    private World world;
    private Entity playerEntity;
    private LifePart playerLifePart;
    private PositionPart playerPositionPart;
    private SpritePart playerSpritePart;
    private Weapon weaponEntity;
    private PositionPart weaponPositionPart;
    private SpritePart weaponSpritePart;
    private Entity auraEntity;

    @BeforeEach
    public void setUp() {
        weaponProcessing = new WeaponProcessing();

        gameData = mock(GameData.class);
        world = mock(World.class);
        playerEntity = mock(Entity.class);
        playerLifePart = mock(LifePart.class);
        playerPositionPart = mock(PositionPart.class);
        playerSpritePart = mock(SpritePart.class);
        weaponEntity = mock(Weapon.class);
        weaponPositionPart = mock(PositionPart.class);
        weaponSpritePart = mock(SpritePart.class);
        auraEntity = mock(Entity.class);

        // Setup player entity
        when(playerEntity.getPart(LifePart.class)).thenReturn(playerLifePart);
        when(playerEntity.getPart(PositionPart.class)).thenReturn(playerPositionPart);
        when(playerEntity.getPart(SpritePart.class)).thenReturn(playerSpritePart);
        when(playerEntity.getType()).thenReturn(EntityTypes.Player);

        // Setup weapon entity
        when(weaponEntity.getPart(PositionPart.class)).thenReturn(weaponPositionPart);
        when(weaponEntity.getPart(SpritePart.class)).thenReturn(weaponSpritePart);

        // Setup aura entity
        when(auraEntity.getType()).thenReturn(EntityTypes.Aura);
    }

    @Test
    void testProcess_PlayerEntityNull() {
        weaponProcessing = spy(new WeaponProcessing());
        when(weaponProcessing.getPlayerEntity(world)).thenReturn(null);

        // Run process
        weaponProcessing.process(gameData, world);

        // Check
        verify(weaponProcessing, times(1)).removeWeapon(world);
        verify(weaponProcessing, never()).getWeaponEntity(world);
        verify(weaponProcessing, never()).updateWeaponPosition(any(Entity.class));
        verify(weaponProcessing, never()).getAuraEntity(world);
        verify(weaponProcessing, never()).createAuraEntities(any(GameData.class), any(World.class), any(Entity.class));
    }

    @Test
    void testProcess_PlayerEntityNotNull_PlayerDead() {
        weaponProcessing = spy(new WeaponProcessing());
        when(weaponProcessing.getPlayerEntity(world)).thenReturn(playerEntity);
        when(playerEntity.getPart(LifePart.class)).thenReturn(playerLifePart);
        when(playerLifePart.isDead()).thenReturn(true);

        // Run process
        weaponProcessing.process(gameData, world);

        // Check
        verify(weaponProcessing).removeWeapon(world);
        verify(weaponProcessing, never()).getWeaponEntity(world);
        verify(weaponProcessing, never()).updateWeaponPosition(any(Entity.class));
        verify(weaponProcessing, never()).getAuraEntity(world);
        verify(weaponProcessing, never()).createAuraEntities(any(GameData.class), any(World.class), any(Entity.class));
    }

    @Test
    void testProcess_PlayerEntityNotNull_PlayerAlive_WeaponEntityNull() {
        weaponProcessing = spy(new WeaponProcessing());
        when(weaponProcessing.getPlayerEntity(world)).thenReturn(playerEntity);
        when(playerEntity.getPart(LifePart.class)).thenReturn(playerLifePart);
        when(playerLifePart.isDead()).thenReturn(false);
        when(weaponProcessing.getWeaponEntity(world)).thenReturn(null);

        // Run process
        weaponProcessing.process(gameData, world);

        // Check
        verify(weaponProcessing, never()).removeWeapon(world);
        verify(weaponProcessing).getWeaponEntity(world);
        verify(weaponProcessing, never()).updateWeaponPosition(any(Entity.class));
        verify(weaponProcessing).getAuraEntity(world);
        verify(weaponProcessing, never()).createAuraEntities(any(GameData.class), any(World.class), any(Entity.class));
    }

    @Test
    void testProcess_PlayerEntityNotNull_PlayerAlive_WeaponEntityNotNull_AuraEntityNotNull() {
        weaponProcessing = spy(new WeaponProcessing());
        when(weaponProcessing.getPlayerEntity(world)).thenReturn(playerEntity);
        when(playerEntity.getPart(LifePart.class)).thenReturn(playerLifePart);
        when(playerLifePart.isDead()).thenReturn(false);
        when(weaponProcessing.getWeaponEntity(world)).thenReturn(weaponEntity);
        when(weaponProcessing.getAuraEntity(world)).thenReturn(auraEntity);

        // Run process
        weaponProcessing.process(gameData, world);

        // Check
        verify(weaponProcessing, never()).removeWeapon(world);
        verify(weaponProcessing).getWeaponEntity(world);
        verify(weaponProcessing).updateWeaponPosition(weaponEntity);
        verify(weaponProcessing).getAuraEntity(world);
        verify(weaponProcessing, never()).createAuraEntities(any(GameData.class), any(World.class), any(Entity.class));
    }

    @Test
    void testProcess_PlayerEntityNotNull_PlayerAlive_WeaponEntityNotNull_AuraEntityNull() {
        weaponProcessing = spy(new WeaponProcessing());
        when(weaponProcessing.getPlayerEntity(world)).thenReturn(playerEntity);
        when(playerEntity.getPart(LifePart.class)).thenReturn(playerLifePart);
        when(playerLifePart.isDead()).thenReturn(false);
        when(weaponProcessing.getWeaponEntity(world)).thenReturn(weaponEntity);
        when(weaponProcessing.getAuraEntity(world)).thenReturn(null);

        // Run process
        weaponProcessing.process(gameData, world);

        // Check
        verify(weaponProcessing, never()).removeWeapon(world);
        verify(weaponProcessing).getWeaponEntity(world);
        verify(weaponProcessing).updateWeaponPosition(weaponEntity);
        verify(weaponProcessing).getAuraEntity(world);
        verify(weaponProcessing).createAuraEntities(gameData, world, weaponEntity);
    }

    @Test
    void testGetWeaponEntity_WeaponExists() {
        // Mock the behavior of the world's getEntities method
        when(world.getEntities()).thenReturn(createEntityCollection(weaponEntity, mock(Entity.class)));

        // Run process
        Entity result = weaponProcessing.getWeaponEntity(world);

        // Check
        assertEquals(weaponEntity, result);
    }

    @Test
    void testGetWeaponEntity_WeaponDoesNotExist() {
        // Mock the behavior of the world's getEntities method
        when(world.getEntities()).thenReturn(createEntityCollection(mock(Entity.class)));

        // Run process
        Entity result = weaponProcessing.getWeaponEntity(world);

        // Check
        assertNull(result);
    }

    @Test
    void testGetPlayerEntity_PlayerExists() {
        // Mock the behavior of the world's getEntities method
        when(world.getEntities()).thenReturn(createEntityCollection(playerEntity, mock(Entity.class)));

        // Run process
        Entity result = weaponProcessing.getPlayerEntity(world);

        // Check
        assertEquals(playerEntity, result);
    }

    @Test
    void testGetPlayerEntity_PlayerDoesNotExist() {
        // Mock the behavior of the world's getEntities method
        when(world.getEntities()).thenReturn(createEntityCollection(mock(Entity.class)));

        // Run process
        Entity result = weaponProcessing.getPlayerEntity(world);

        // Check
        assertNull(result);
    }

    @Test
    void testUpdateWeaponPosition() {
        when(weaponEntity.getPart(PositionPart.class)).thenReturn(weaponPositionPart);
        when(weaponEntity.getPart(SpritePart.class)).thenReturn(weaponSpritePart);
        when(playerPositionPart.getX()).thenReturn(100.0f);
        when(playerSpritePart.getSrcWidth()).thenReturn(50);
        when(playerPositionPart.getY()).thenReturn(200.0f);
        when(playerSpritePart.getSrcHeight()).thenReturn(25);

        // Run process
        weaponProcessing.playerPositionPart = playerPositionPart;
        weaponProcessing.playerSpritePart = playerSpritePart;
        weaponProcessing.updateWeaponPosition(weaponEntity);

        // Check
        verify(weaponPositionPart).setX(75.0f);
        verify(weaponPositionPart).setY(212.5f);
    }

    @Test
    void testGetAuraEntity_AuraExists() {
        // Mock the behavior of the world's getEntities method
        when(world.getEntities()).thenReturn(createEntityCollection(auraEntity, mock(Entity.class)));

        // Run process
        Entity result = weaponProcessing.getAuraEntity(world);

        // Check
        assertEquals(auraEntity, result);
    }

    @Test
    void testGetAuraEntity_AuraDoesNotExist() {
        // Mock the behavior of the world's getEntities method
        when(world.getEntities()).thenReturn(createEntityCollection(mock(Entity.class)));

        // Run process
        Entity result = weaponProcessing.getAuraEntity(world);

        // Check
        assertNull(result);
    }

    @Test
    void testCreateAuraEntities_BulletSPIIsNull() {
        // Mock AI Service
        BulletSPI mockedBulletSPI = mock(BulletSPI.class);

        when(mockedBulletSPI.createBullet(weaponEntity, gameData)).thenReturn(mock(Entity.class));

        // Mock the zombieControlSystem object
        weaponProcessing = new WeaponProcessing() {
            @Override
            Collection<BulletSPI> loadBulletSPIs() {
                return Collections.singletonList(mockedBulletSPI);
            }
        };

        // run process
        weaponProcessing.createAuraEntities(gameData, world, weaponEntity);

        // Check
        verify(world, times(1)).addEntity(any(Entity.class));
    }

    // Helper method to convert varargs to Collection<Entity>
    @SafeVarargs
    private Collection<Entity> createEntityCollection(Entity... entities) {
        return List.of(entities);
    }
}
