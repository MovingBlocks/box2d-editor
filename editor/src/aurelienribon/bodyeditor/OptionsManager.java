package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.EarClippingManager.Polygonizers;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class OptionsManager {
	// -------------------------------------------------------------------------
	// Singleton
	// -------------------------------------------------------------------------

    private static OptionsManager instance = new OptionsManager();
	public static OptionsManager instance() { return instance; }

	// -------------------------------------------------------------------------
	// Options
	// -------------------------------------------------------------------------

	public boolean isAssetDrawn = true;
	public boolean isAssetDrawnWithOpacity50 = false;
	public boolean isShapeDrawn = true;
	public boolean arePolyDrawn = true;
	public boolean isBackgroundLight = false;
	public boolean isSnapToGridEnabled = false;
	public boolean isGridShown = false;
	public int gridGap = 10;
	public Polygonizers polygonizer = Polygonizers.BAYAZIT;
}
