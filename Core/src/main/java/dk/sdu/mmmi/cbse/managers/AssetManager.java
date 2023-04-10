package dk.sdu.mmmi.cbse.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AssetManager {
    // Player
    public static final Sprite PLAYER_SPRITE = new Sprite(loadTexture("assets/player.png"));

    private static Texture loadTexture(String texturePath) {
        return new Texture(texturePath);
    }
}
