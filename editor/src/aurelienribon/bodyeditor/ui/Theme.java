package aurelienribon.bodyeditor.ui;

import aurelienribon.utils.ui.GroupBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class Theme {
    public static final Color MAIN_BACKGROUND = new Color(0x444444);
    public static final Color MAIN_FOREGROUND = new Color(0xF0F0F0);
    public static final Color MAIN_ALT_BACKGROUND = new Color(0x707070);
    public static final Color MAIN_ALT_FOREGROUND = new Color(0xF0F0F0);

    public static final Color HEADER_BACKGROUND = new Color(0x707070);
    public static final Color HEADER_FOREGROUND = new Color(0xF0F0F0);

    public static final Color TEXTAREA_BACKGROUND = new Color(0x333333);
    public static final Color TEXTAREA_FOREGROUND = new Color(0xF0F0F0);
    public static final Color TEXTAREA_SELECTED_BACKGROUND = new Color(0x808080);
    public static final Color TEXTAREA_SELECTED_FOREGROUND = new Color(0xF0F0F0);

    public static final Color CONSOLE_BACKGROUND = new Color(0xA5A5A5);
    public static final Color CONSOLE_FOREGROUND = new Color(0x000000);

    public static final Color SEPARATOR = new Color(0xB5B5B5);

	public static void apply(Component cmp) {
		if (cmp instanceof JComponent) {
			JComponent jcmp = (JComponent) cmp;
			Border border = jcmp.getBorder();
			if (border != null && border instanceof GroupBorder) {
				Font font = jcmp.getFont();
				jcmp.setFont(new Font(font.getFamily(), Font.BOLD, font.getSize()));
				jcmp.setBackground(Theme.MAIN_ALT_BACKGROUND);
				jcmp.setForeground(Theme.MAIN_ALT_FOREGROUND);
				jcmp.setOpaque(false);
			}
		}

		if (cmp instanceof Container) {
			Container ctn = (Container) cmp;
			for (Component child : ctn.getComponents())
				apply(child);
		}
	}
}
