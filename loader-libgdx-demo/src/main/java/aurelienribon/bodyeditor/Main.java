package aurelienribon.bodyeditor;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main {
    public static void main(final String[] args) {
        // When this app was written against an older LibGDX the boolean previously here was setting GL10 vs GL20. Now always 20.
        new LwjglApplication(new App(), "Physics Body Editor - Loader Demo", 500, 600);
    }
}
