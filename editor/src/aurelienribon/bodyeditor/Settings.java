package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.EarClippingManager.Polygonizers;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Settings {
	public static boolean isImageDrawn = true;
	public static boolean isShapeDrawn = true;
	public static boolean isPolygonDrawn = true;
	public static boolean isBackgroundLight = true;
	public static boolean isSnapToGridEnabled = false;
	public static boolean isGridShown = false;
	public static float gridGap = 0.03f;
	public static Polygonizers polygonizer = Polygonizers.BAYAZIT;
}
