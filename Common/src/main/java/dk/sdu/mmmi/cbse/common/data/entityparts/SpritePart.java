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


    public SpritePart (int srcStartPosX, int srcStartPosY, int srcWidth, int srcHeight,float sizeHeight,float sizeWidth, int layer) {
        this.srcStartPosX = srcStartPosX;
        this.srcStartPosY = srcStartPosY;
        this.srcHeight = srcHeight;
        this.srcWidth = srcWidth;
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;
        this.layer = layer;
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

    @Override
    public void process(GameData gameData, Entity entity) {
    }
}
