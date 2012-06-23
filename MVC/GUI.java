package MVC;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


@SuppressWarnings("serial")
public class GUI extends JFrame
{
	//... Constants
	private static final String str_toMatch       = "Parola da ricercare :";
	private static final String dir_toMatch       = "Cartella da considerare:";
	private static final String str_toMatchRemote = "Link file remoto:";
	private static final String str_search        = "Ricerca!";
	private static final String str_treepoints    = "...";
	private static final int FileRemoteArea = 2, DirectoryArea = 1, FileArea = 0, FileAdvArea = 3;
	
	//... Components
	private JPanel       tab1              = new JPanel();
	private JPanel       tab2              = new JPanel();
	private JPanel       tab3              = new JPanel();
	private JPanel       tab4              = new JPanel();
	private JFileChooser dirChooser        = new JFileChooser();
	private JFileChooser fileChooser       = new JFileChooser();
	private JFileChooser advFileChooser    = new JFileChooser();
	private JLabel       strToMatchLabel   = new JLabel(str_toMatch);
	private JLabel       strToMatchLabel2  = new JLabel(str_toMatch);
	private JLabel       strToMatchLabel3  = new JLabel(str_toMatch);
	private JLabel       strToMatchLabel4  = new JLabel(str_toMatch);
	private JLabel       dirToMatchLabel   = new JLabel(dir_toMatch);
	private JLabel       strToMatchRemote  = new JLabel(str_toMatchRemote);
	private JTextField   strToMatch        = new JTextField(15);
	private JTextField   strToMatch2       = new JTextField(15);
	private JTextField   strToMatch3       = new JTextField(15);
	private JTextField   dirToMatch        = new JTextField(15);
	private JTextField   advStrToMatch     = new JTextField(15);
	private JTextArea    outputMatch       = new JTextArea(13,70);
	private JTextArea    outputDirMatch    = new JTextArea(13,70);
	private JTextArea    outputRFileMatch  = new JTextArea(13,70);
	private JTextArea    advOutputString   = new JTextArea(13,70);
	private JButton      clToMatch         = new JButton(str_search);
	private JButton      clToMatch2        = new JButton(str_search);
	private JButton      clToMatch3        = new JButton(str_search);
	private JButton      clToMatch4        = new JButton(str_search);
	private JButton      selFile           = new JButton(str_treepoints);
	private JButton      selDir            = new JButton(str_treepoints);
	private JButton      selRemFile        = new JButton(str_treepoints);
	private JButton      advSelFile        = new JButton(str_treepoints);
	private JButton      forBold           = new JButton("<html><b>B</b></html>");
	private JButton      forItalic         = new JButton("<html><i>I</i></html>");
	private JButton      forUnderline      = new JButton("<html><u>U</u></html>");
	private JMenuBar     menuBar           = new JMenuBar();
	private JMenu        fileMenu          = new JMenu("File");
	private JMenu        helpMenu          = new JMenu("Aiuto");
	private JMenuItem    cancella          = new JMenuItem("Pulisci");
	private JMenuItem    esci              = new JMenuItem("Esci");
	private JMenuItem    help              = new JMenuItem("Informazioni");
	private JTabbedPane  tabbedPane        = new JTabbedPane();

	private CalcModel m_model;

	public GUI(CalcModel model)
	{
		//... Set up the logic
		m_model = model;

		//... Initialize components
		setLayout(new BorderLayout());
		tabbedPane.setBorder(BorderFactory.createEmptyBorder(
                20, //top
                30, //left
                10, //bottom
                30));
		outputMatch.setEditable(false);
		outputDirMatch.setEditable(false);
		dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		dirChooser.setApproveButtonText("Apri Cartella");  
		dirChooser.addChoosableFileFilter(new FileNameExtensionFilter("Cartella di file", "*"));	
		fileChooser.setApproveButtonText("Apri File");
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("TXT - TeXTfile", "txt"));	
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("HTML - HyperText Markup Language", "html"));	
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML - eXtensible Markup Language", "xml"));	
		advFileChooser.setApproveButtonText("Apri File");
		advFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("TXT - TeXTfile", "txt"));	
		advFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("HTML - HyperText Markup Language", "html"));	
		advFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML - eXtensible Markup Language", "xml"));
		outputMatch.setEditable(false);
		outputDirMatch.setEditable(false);
		advOutputString.setEditable(false);
		outputRFileMatch.setEditable(false);
		
		//... Layout the components.  
		tab1.add(strToMatchLabel,BorderLayout.CENTER);
		tab1.add(strToMatch,BorderLayout.CENTER);
		tab1.add(selFile,BorderLayout.CENTER);
		tab1.add(clToMatch,BorderLayout.CENTER);
		tab1.add(outputMatch,BorderLayout.SOUTH);
		
		tab2.add(dirToMatchLabel,BorderLayout.CENTER);
		tab2.add(dirToMatch,BorderLayout.CENTER);
		tab2.add(selDir,BorderLayout.CENTER);
		tab2.add(strToMatchLabel2,BorderLayout.CENTER);
		tab2.add(strToMatch2,BorderLayout.CENTER);
		tab2.add(strToMatch3,BorderLayout.CENTER);
		tab2.add(clToMatch2,BorderLayout.CENTER);
		tab2.add(outputDirMatch,BorderLayout.SOUTH);
		
		tab3.add(strToMatchRemote,BorderLayout.CENTER);
		tab3.add(selRemFile,BorderLayout.CENTER);
		tab3.add(strToMatchLabel4,BorderLayout.CENTER);
		tab3.add(strToMatch2,BorderLayout.CENTER);
		tab3.add(clToMatch3,BorderLayout.CENTER);
		tab3.add(outputRFileMatch,BorderLayout.SOUTH);
		
		tab4.add(forBold,BorderLayout.CENTER);
		tab4.add(forItalic,BorderLayout.CENTER);
		tab4.add(forUnderline,BorderLayout.CENTER);
		tab4.add(strToMatchLabel3,BorderLayout.CENTER);
		tab4.add(advStrToMatch,BorderLayout.CENTER);
		tab4.add(advSelFile,BorderLayout.CENTER);
		tab4.add(clToMatch4,BorderLayout.CENTER);
		tab4.add(advOutputString,BorderLayout.SOUTH);
		tabbedPane.addTab("Parser", tab1);
		tabbedPane.addTab("Parser Cartella", tab2);
		tabbedPane.addTab("Parser File Remoti", tab3);
		tabbedPane.addTab("Parser Avanzato", tab4);

		add(tabbedPane);

		/** menu **/
		JMenuBar menuBar = makeMenu();
		setJMenuBar(menuBar);
		/** fine menu **/

		//... finalize layout
		setTitle("Nome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(750,400);
		setLocation(150, 150);
	}

	private JMenuBar makeMenu()
	{
		fileMenu.add(cancella);
		fileMenu.addSeparator();
		fileMenu.add(esci);
		helpMenu.add(help);
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		return menuBar;
	}
	
	public void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}
	
	public void updateLogTextArea(String string)
	{
		int whatTextArea = tabbedPane.getSelectedIndex();
		switch(whatTextArea)
		{
		case FileRemoteArea:
			outputRFileMatch.setText(
					new StringBuffer(outputRFileMatch.getText()).
					append(string).toString());
			break;
		case DirectoryArea:
			outputDirMatch.setText(
					new StringBuffer(outputDirMatch.getText()).
					append(string).toString());
			break;
		case FileArea:
			outputMatch.setText(
					new StringBuffer(outputMatch.getText()).
					append(string).toString());
			break;
		case FileAdvArea:
			advOutputString.setText(
					new StringBuffer(advOutputString.getText()).
					append(string).toString());
			break;
		}
	}

	public String getWord() {
		int whatTextArea = tabbedPane.getSelectedIndex();
		switch(whatTextArea)
		{
		case FileRemoteArea:
			return strToMatch2.getText();
		case DirectoryArea:
			return strToMatch3.getText();
		case FileArea:
			return strToMatch.getText();
		case FileAdvArea:
			return advStrToMatch.getText();
		}
		return "";
	}
	
	protected void openHelp() {
		new About().setVisible(true);
	}
	
	protected void openFileChooser() {
		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			m_model.setResource(fileChooser.getSelectedFile());
			updateLogTextArea("File : "+fileChooser.getSelectedFile().getName()+" caricato.\n");
			showError("File : "+fileChooser.getSelectedFile().getName()+" caricato.");
		}
	}
	
	protected void openAdvFileChooser() {
		int returnVal = advFileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			m_model.setResource(advFileChooser.getSelectedFile());
			updateLogTextArea("File : "+advFileChooser.getSelectedFile().getName()+" caricato.\n");
			showError("File : "+advFileChooser.getSelectedFile().getName()+" caricato.");
		}
	}
	
	protected void openDirChooser() {
		int returnVal = dirChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			m_model.setResource(dirChooser.getSelectedFile());
			dirToMatch.setText(dirChooser.getSelectedFile().getAbsolutePath());
			updateLogTextArea("Cartella : "+dirChooser.getSelectedFile().getName()+" caricata.\n");
			showError("Cartella : "+dirChooser.getSelectedFile().getName()+" caricata.");
		}
	}
	
	protected void openRemoteChooser() {
		String inputValue = JOptionPane.showInputDialog("Please input a value");
		String error = m_model.setResource(inputValue);
		updateLogTextArea(error+"\n");
		showError(error);
	}

	public void reset() {
		strToMatch.setText("");
		strToMatch2.setText("");
		dirToMatch.setText("");
	}
	
	public void close() {
		System.exit(0);
	}

	public void setBoldSelected() {
		if(forBold.isSelected())
			forBold.setSelected(false);
		else forBold.setSelected(true);
	}

	public void setItalicSelected() {
		if(forItalic.isSelected())
			forItalic.setSelected(false);
		else forItalic.setSelected(true);
	}

	public void setUnderlineSelected() {
		if(forUnderline.isSelected())
			forUnderline.setSelected(false);
		else forUnderline.setSelected(true);
	}

	protected void addClearListener(ActionListener cal) {
		cancella.addActionListener(cal);
    }

	protected void addExitListener(ActionListener cal) {
		esci.addActionListener(cal);
	}
	
	protected void addAboutListener(ActionListener cal) {
		help.addActionListener(cal);
	}
	
	protected void addSelectDirectoryListener(ActionListener cal) {
		selDir.addActionListener(cal);
	}
	
	protected void addSelectFileListener(ActionListener cal) {
		selFile.addActionListener(cal);
	}
	
	protected void addAdvFileListener(ActionListener cal) {
		advSelFile.addActionListener(cal);
	}
	
	protected void addSelectRemoteFileListener(ActionListener cal) {
		selRemFile.addActionListener(cal);
	}

	protected void addParseRemoteFileListener(ActionListener cal) {
		clToMatch3.addActionListener(cal);
	}
	
	protected void addParseFileListener(ActionListener cal) {
		clToMatch.addActionListener(cal);
	}
	
	protected void addParseDirectoryListener(ActionListener cal) {
		clToMatch2.addActionListener(cal);
	}

	protected void addBoldButtonListener(ActionListener cal) {
		forBold.addActionListener(cal);
	}
	
	protected void addItalicButtonListener(ActionListener cal) {
		forItalic.addActionListener(cal);
	}
	
	protected void addUnderlineButtonListener(ActionListener cal) {
		forUnderline.addActionListener(cal);
	}
	
	public boolean isUnderline() { return forUnderline.isSelected(); }
	public boolean isItalic() { return forItalic.isSelected(); }
	public boolean isBold() { return forBold.isSelected(); }

	protected void addAdvFileParserListener(ActionListener cal) {
		clToMatch4.addActionListener(cal);
	}
	
}
