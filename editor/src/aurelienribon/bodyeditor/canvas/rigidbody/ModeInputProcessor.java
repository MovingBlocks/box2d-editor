package aurelienribon.bodyeditor.canvas.rigidbody;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class ModeInputProcessor extends InputAdapter {
	private final Canvas canvas;

	public ModeInputProcessor(Canvas canvas) {
		this.canvas = canvas;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Keys.TAB: canvas.setNextMode(); break;
		}
		
		return false;
	}
}
