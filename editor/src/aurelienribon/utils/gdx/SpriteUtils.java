package aurelienribon.utils.gdx;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class SpriteUtils {
	public static boolean isOver(Sprite sp, float x, float y) {
		return sp.getX() <= x && x <= sp.getX() + sp.getWidth()
			&& sp.getY() <= y && y <= sp.getY() + sp.getHeight();
	}
}
