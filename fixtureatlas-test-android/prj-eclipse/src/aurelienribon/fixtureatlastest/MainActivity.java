package aurelienribon.fixtureatlastest;

import com.badlogic.gdx.backends.android.AndroidApplication;

import android.os.Bundle;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new App(), false);
    }
}