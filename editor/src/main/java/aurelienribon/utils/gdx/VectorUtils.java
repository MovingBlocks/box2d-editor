package aurelienribon.utils.gdx;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class VectorUtils {
	public static Vector2 mul(Vector2 v, float cx, float cy) {
		Vector2 ret = v.cpy();
		ret.x *= cx;
		ret.y *= cy;
		return ret;
	}

	public static Vector2[] mul(Vector2[] vs, float cx, float cy) {
		Vector2[] ret = new Vector2[vs.length];
		for (int i=0; i<ret.length; i++) ret[i] = mul(vs[i], cx, cy);
		return ret;
	}

	public static Vector2[][] mul(Vector2[][] vss, float cx, float cy) {
		Vector2[][] ret = new Vector2[vss.length][];
		for (int i=0; i<ret.length; i++) ret[i] = mul(vss[i], cx, cy);
		return ret;
	}

	// -------------------------------------------------------------------------

	public static Vector2 getCopy(Vector2 v) {
		if (v == null) return null;
		return v.cpy();
	}

	public static Vector2[] getCopy(Vector2[] vs) {
		if (vs == null) return null;
		Vector2[] ret = new Vector2[vs.length];
		for (int i=0; i<ret.length; i++) ret[i] = getCopy(vs[i]);
		return ret;
	}

	public static Vector2[][] getCopy(Vector2[][] vss) {
		if (vss == null) return null;
		Vector2[][] ret = new Vector2[vss.length][];
		for (int i=0; i<ret.length; i++) ret[i] = getCopy(vss[i]);
		return ret;
	}
}
