package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class SpritePart implements EntityPart {
    private final int srcWidth;
    private final int srcHeight;
    private int srcStartPosX;
    private int srcStartPosY;
    private int layer;
    private float sizeWidth;
    private float sizeHeight;
    private float opacity;


    public SpritePart (int srcStartPosX, int srcStartPosY, int srcWidth, int srcHeight, float sizeHeight, float sizeWidth, int layer, float opacity) {
        this.srcStartPosX = srcStartPosX;
        this.srcStartPosY = srcStartPosY;
        this.srcHeight = srcHeight;
        this.srcWidth = srcWidth;
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
        this.layer = layer;
        this.opacity = opacity;
    }

    public int getSrcHeight() {
        return srcHeight;
    }

    public int getSrcWidth() {
        return srcWidth;
    }

    public int getSrcStartPosX() {
        return srcStartPosX;
    }

    public int getSrcStartPosY() {
        return srcStartPosY;
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

    public float getOpacity() { return opacity; }

    public void setOpacity(float newOpacity) {
        this.opacity = newOpacity;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }
}
