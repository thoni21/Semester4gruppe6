package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;



public class SpritePart implements EntityPart {
    private String spritePath;
    private final int srcWidth;
    private final int srcHeight;
    private int layer;
    private float sizeWidth;
    private float sizeHeight;





    public SpritePart (String spritePath, int srcWidth, int srcHeight,float sizeHeight,float sizeWidth, int layer) {
        this.spritePath = spritePath;
        this.srcHeight = srcHeight;
        this.srcWidth = srcWidth;
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
        this.layer = layer;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public int getSrcHeight() {
        return srcHeight;
    }

    public int getSrcWidth() {
        return srcWidth;
    }

    public float getSizeHeight() {
        return sizeHeight;
    }

    public float getSizeWidth() {
        return sizeWidth;
    }

    public int getLayer() {
        return layer;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }
}
