package MVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import Composite.GenericFile;
import Composite.Resource;
import Parser.Parser;

public class CalcController {
	//... The Controller needs to interact with both the Model and View.
	private CalcModel m_model;
	private GUI       m_view;

	public CalcController(CalcModel model, GUI view) {
		m_model = model;
		m_view  = view;

		//... Add listeners to the view.
		view.addClearListener(new ClearListener());
		view.addExitListener(new ExitListener());
		view.addAboutListener(new AboutListener());
		view.addSelectDirectoryListener(new DirChooserListener());
		view.addSelectFileListener(new FileChooserListener());
		view.addSelectRemoteFileListener(new RemoteFileChooserListener());
		view.addParseRemoteFileListener(new parseRemoteFileListener());
		view.addParseFileListener(new parseFileListener());
		view.addParseDirectoryListener(new parseDirectoryListener());
		view.addItalicButtonListener(new ItalicButtonListener());
		view.addBoldButtonListener(new BoldButtonListener());
		view.addUnderlineButtonListener(new UnderlineButtonListener());
		view.addAdvFileListener(new AdvFileChooserListener());
		view.addAdvFileParserListener(new AdvFileListener());
	}

	//////////////////////////////////////////// ClearListener
	/**  1. Reset model.
	 *   2. Reset View.
	 */    
	private class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_model.reset();
			m_view.reset();
		}
	}
	
	//////////////////////////////////////////// ExitListener
	/**
	 * Exit program
	 */
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.close();
		}
	}
	
	class AboutListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.openHelp();
		}
	}
	
	class DirChooserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.openDirChooser();
		}
	}
	
	class FileChooserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.openFileChooser();
		}
	}
	
	class RemoteFileChooserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.openRemoteChooser();
		}
	}
	
	class AdvFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				File file = m_model.getFile();
				String word = m_view.getWord();
				GenericFile toParse = Resource.getAppropriateFile(file.getCanonicalPath());
				String ext = toParse.getExtension();
				boolean isUnderline = m_view.isUnderline(), isBold = m_view.isBold(),
						isItalic = m_view.isItalic();
				Parser.getInstance().parse(toParse.clean(file),word,
						  					isBold, isUnderline, isItalic);
				String outputContent = Parser.getInstance().toString();
				String outputFilePath = file.getAbsolutePath().replaceAll(ext,"");
				BufferedWriter outputFile = new BufferedWriter(new FileWriter(new File
						(outputFilePath+"_"+word+"_occurrency.html")));
				outputFile.write(outputContent);
				outputFile.flush();
				outputFile.close();
				m_view.showError("File correttamente processato\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class parseRemoteFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				URL url = m_model.getURL();
				String word = m_view.getWord();
				String ext = url.toString().substring(url.toString().lastIndexOf('.'), url.toString().length());
				String nomeFile = url.toString().substring(url.toString().lastIndexOf('/'), url.toString().length());
				//TODO
		    	switch(ext) {
		    	/*case ".txt":
		    		Parser.getInstance().parse(new TXTFile().clean(url).toString(),word);
		    		break;
		    	case ".xml":
		    		Parser.getInstance().parse(new XMLFile().clean(url).toString(),word);
		    		break;
		    	case ".html":
		    	case ".php":
		    		Parser.getInstance().parse(new HTMLFile().clean(url).toString(),word);
		    		break;*/
		    	}
		    	String outputContent = Parser.getInstance().toString();
				BufferedWriter outputFile = new BufferedWriter(new FileWriter(new File
						(nomeFile+"_"+word+"_occurrency.html")));
				outputFile.write(outputContent);
				outputFile.flush();
				outputFile.close();
				m_view.showError("File correttamente processato\n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class parseFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				File file = m_model.getFile();
				String word = m_view.getWord();
				GenericFile toParse = Resource.getAppropriateFile(file.getCanonicalPath());
				String ext = toParse.getExtension();
				toParse.clean(file);
				Parser.getInstance().parse(toParse.toString(),word);
		    	String outputContent = Parser.getInstance().toString();
		    	String outputFilePath = file.getAbsolutePath().replaceAll(ext,"");
				BufferedWriter outputFile = new BufferedWriter(new FileWriter(new File
						(outputFilePath+"_"+word+"_occurrency.html")));
				outputFile.write(outputContent);
				outputFile.flush();
				outputFile.close();
				m_view.showError("File correttamente processato\n");
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	class parseDirectoryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
		    	File[] files = m_model.getFile().listFiles();
		    	String word = m_view.getWord();
				BufferedWriter outputFile = new BufferedWriter(new FileWriter(new File
						(m_model.getFile().getName()+"_"+word+"_stats.txt")));
				StringBuffer outputContent = new StringBuffer();
				int numOfOccurrency = 0;
				for(File file : files) {
		    		if(!file.isDirectory()) {
		    			GenericFile toParse = Resource.getAppropriateFile(file.getCanonicalPath());
						Parser.getInstance().parse(toParse.clean(file),word);
						outputContent.append(file.getName()+" "+Parser.getInstance().getNumOccurrency()+"\n");
			    		numOfOccurrency += Parser.getInstance().getNumOccurrency();
		    		}
		    	}
				outputContent.insert(0, numOfOccurrency+"\n");
		    	outputFile.write(outputContent.toString());
				outputFile.flush();
				outputFile.close();
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	class AdvFileChooserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.openAdvFileChooser();
		}
	}

	class BoldButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.setBoldSelected();
		}
	}
	
	class ItalicButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.setItalicSelected();
		}
	}

	class UnderlineButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_view.setUnderlineSelected();
		}
	}
}
