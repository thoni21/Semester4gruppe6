module Common {
    requires java.datatransfer;
    requires java.desktop;
    requires com.badlogic.gdx;

    exports dk.sdu.mmmi.cbse.common.services;
    exports dk.sdu.mmmi.cbse.common.data.entityparts;
    exports dk.sdu.mmmi.cbse.common.data;
    exports dk.sdu.mmmi.cbse.common.Types;
    exports dk.sdu.mmmi.cbse.common.events;
}