package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class SpritePart implements EntityPart {
    private String spritePath;
    private int srcWidth;
    private int srcHeight;

    public SpritePart (String spritePath, int srcWidth, int srcHeight) {
        this.spritePath = spritePath;
        this.srcHeight = srcHeight;
        this.srcWidth = srcWidth;
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

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }
}
