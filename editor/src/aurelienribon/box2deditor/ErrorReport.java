package aurelienribon.box2deditor;

import java.awt.Component;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JOptionPane;

public class ErrorReport {
    public static void reportOnStdErr(String msg) {
		System.err.println(formatMessage(msg));
	}

	public static void reportOnStdErr(String msg, Exception ex) {
		System.err.println(formatMessage(msg, ex));
	}

	public static void reportOnUi(Component parent, String msg) {
		JOptionPane.showMessageDialog(parent,
			formatMessage(msg),
			"All your error are belong to us!",
			JOptionPane.ERROR_MESSAGE);
	}

	public static void reportOnUi(Component parent, String msg, Exception ex) {
		JOptionPane.showMessageDialog(parent,
			formatMessage(msg, ex),
			"All your error are belong to us!",
			JOptionPane.ERROR_MESSAGE);
	}

	// -------------------------------------------------------------------------

	private static String formatMessage(String msg) {
		if (msg != null && !msg.equals("")) {
			msg = "[error] Something wrong happened!\n" + msg;
			msg = msg.replaceAll("\n", "\n    ");
		} else {
			msg = "[error] Something wrong happened!";
		}
		return msg;
	}

	private static String formatMessage(String msg, Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));

		if (msg != null && !msg.equals("")) {
			msg = msg + "\n\n"
				+ ex.getMessage() + "\n"
				+ sw.toString();
		} else {
			msg = ex.getMessage() + "\n"
				+ sw.toString();
		}
		return formatMessage(msg);
	}
}
