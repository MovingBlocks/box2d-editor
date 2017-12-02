package aurelienribon.bodyeditor.ui;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.json.JSONException;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Canvas;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Main {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.useGL30 = false;
                config.useHDPI = true;
             //
                //
              //   ShaderProgram.prependVertexCode = "#version 140\n#define varying out\n#define attribute in\n";
              //  ShaderProgram.prependFragmentCode = "#version 140\n#define varying in\n#define texture2D texture\n#define gl_FragColor fragColor\nout vec4 fragColor;\n";

                LwjglAWTCanvas glCanvas = new LwjglAWTCanvas(new Canvas());
               // LwjglCanvas glCanvas = new LwjglCanvas(new Canvas(), config);
                MainWindow mw = Ctx.window;

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                mw.setSize(
                        Math.min(1150, screenSize.width - 100),
                        Math.min(800, screenSize.height - 100)
                );
                mw.setCanvas(glCanvas.getCanvas());
                mw.setLocationRelativeTo(null);
                mw.setVisible(true);

                parseArgs(args);
            }
        });
    }

    private static void parseArgs(String[] args) {
        for (int i = 1; i < args.length; i++) {
            if (args[i - 1].equals("-f")) {
                try {
                    File file = new File(args[i]).getCanonicalFile();
                    Ctx.io.setProjectFile(file);
                    Ctx.io.importFromFile();
                } catch (IOException ex) {
                } catch (JSONException ex) {
                }
            }
        }
    }
}
