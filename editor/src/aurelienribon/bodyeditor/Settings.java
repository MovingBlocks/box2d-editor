package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.earclipping.Clipper.Polygonizer;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Settings {
	public static boolean isImageDrawn = true;
	public static boolean isShapeDrawn = true;
	public static boolean isPolygonDrawn = true;
	public static boolean isPhysicsDebugEnabled = false;
	public static boolean isSnapToGridEnabled = false;
	public static boolean isGridShown = false;
	public static float gridGap = 0.03f;
	public static Polygonizer polygonizer = Polygonizer.BAYAZIT;
}
