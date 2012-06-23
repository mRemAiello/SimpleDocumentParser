import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import MVC.*;

public class Main
{
	static PrintStream output = null;
	public static void main(String [] args) throws IOException, ClassNotFoundException, InstantiationException,
													IllegalAccessException, UnsupportedLookAndFeelException
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			CalcModel      model      = new CalcModel();
			GUI      	   view       = new GUI(model);
			CalcController controller = new CalcController(model, view);
			view.setVisible(true);
		} catch (RuntimeException e) {
			output = new PrintStream(new File("crashDump.txt"));
			e.printStackTrace();
			e.printStackTrace(output);
			output.close();
		}
	}
}