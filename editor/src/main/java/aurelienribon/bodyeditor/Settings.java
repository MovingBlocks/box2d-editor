package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.maths.Clipper.Polygonizer;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Settings {
	public static boolean isImageDrawn = true;
	public static boolean isShapeDrawn = true;
	public static boolean isPolygonDrawn = true;
	public static boolean isPhysicsDebugEnabled = false;
	public static boolean isSnapToGridEnabled = false;
	public static boolean isAxisShown = true;
	public static boolean isGridShown = false;
	public static float gridGap = 0.03f;
	public static Polygonizer polygonizer = Polygonizer.BAYAZIT;
	public static float autoTraceHullTolerance = 2.5f;
	public static int autoTraceAlphaTolerance = 128;
	public static boolean autoTraceMultiPartDetection = false;
	public static boolean autoTraceHoleDetection = false;
}
