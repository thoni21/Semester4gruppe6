package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.SpritePart;
import dk.sdu.mmmi.cbse.common.events.Event;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

public class Game
        implements ApplicationListener {

    private static OrthographicCamera cam;
    private SpriteBatch batch;
    private Texture spriteSheetTexture;
    private final GameData gameData = new GameData();
    private World world = new World();

    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        batch = new SpriteBatch();

        // Load Sprite Sheet
        this.spriteSheetTexture = new Texture("assets/spritesheet.png");

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );


        // Lookup all Game Plugins using ServiceLoader
        System.out.println(getPluginServices().size());
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData, world);
        }

    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }

        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }

        for (Event event : gameData.getEvents()) {
            // Gdx.app.log("Events", gameData.getEvents().size() + "");
            event.executeEvent();
            gameData.removeEvent(event);
        }
    }

    private void draw() {
        ArrayList<Entity> layer0 = new ArrayList<>();
        ArrayList<Entity> layer1 = new ArrayList<>();
        ArrayList<Entity> layer2 = new ArrayList<>();

        // Get all entities in the world and add them to drawing layers
        for (Entity entity : world.getEntities()) {
            SpritePart spritePart = entity.getPart(SpritePart.class);
            if (spritePart.getLayer() == 0) {
                layer0.add(entity);
            } else if (spritePart.getLayer() == 1) {
                layer1.add(entity);
            } else { layer2.add(entity); }
        }

        // Begin drawing batch.
        // For each layer draw the entity from the Sprite Sheet.
        batch.begin();
        for (Entity entity : layer0) {
            drawEntity(entity);
        }
        for (Entity entity : layer1) {
            drawEntity(entity);
        }
        for (Entity entity : layer2) {
            drawEntity(entity);
        }
        batch.end();
    }

    private void drawEntity(Entity entity) {
        SpritePart spritePart = entity.getPart(SpritePart.class);
        PositionPart positionPart = entity.getPart(PositionPart.class);

        TextureRegion region = new TextureRegion(spriteSheetTexture, spritePart.getSrcStartPosX(), spritePart.getSrcStartPosY(), spritePart.getSrcWidth(), spritePart.getSrcHeight());

        // Set the opacity off current sprite part
        batch.setColor(255,255,255, spritePart.getOpacity());

        batch.draw(region, positionPart.getX(), positionPart.getY());
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteSheetTexture.dispose();
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
    
    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
