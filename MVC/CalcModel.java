package MVC;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Composite.DirectoryFile;

public class CalcModel {
    //... Constants
    private static final String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private static final int file = 1, remoteFile = 2;
    
	//... Support var
	private DirectoryFile dirList;
	private File fileChoose;
	private String fileRemoteLink;
    
    //============================================================== constructor
    /** Constructor */
    public CalcModel() {
        reset();
    }
    
    //==================================================================== getFile/getUrl
    /** restituisce un URL o un file precaricato 
     * @throws IOException */
	public File getFile() throws IOException {
		if(isResourceLoaded(file))
			return fileChoose;
		else throw new IOException();
	}
	
	public URL getURL() throws MalformedURLException {
		return new URL(fileRemoteLink);
	}
	
	//==================================================================== isResourceLoaded
	/** controlla che la risorsa sia caricata in memoria. Per ottenere l'int, 
	 *  basta richiamare i metodi needFile/needRemoteFile
	 */
    public boolean isResourceLoaded(int resource) {
    	switch(resource) {
		case remoteFile:
    		return fileRemoteLink.equals("");
    	case file:
    		return (fileChoose != null);
    	default:
    		return false;
    	}
    }
    
    public int needFile() { return file; }
    public int needRemoteFile() { return remoteFile; }
    
    //==================================================================== setResource
    /** Setta i riferimenti al file o alla stringa che rappresenta l'url */
    public String setResource(File selectedFile) {
    	fileChoose = selectedFile;
    	return "";
    }
    
    public String setResource(String remoteUrl) {
    	if(isMatch(remoteUrl,regex)) {
			fileRemoteLink = remoteUrl;
			return "File Remoto : "+remoteUrl+" caricato.";
		} else return "Il link non è corretto.";
    }
    
    //==================================================================== isMatch
    /** Controlla che il link sia in un formato valido */
    private boolean isMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {}
        return false;
    }
    
    //==================================================================== reset
    /** Reset to initial value. */
    public void reset() {
    	fileChoose = null;
    	fileRemoteLink = null;
    	if(dirList != null)
    		dirList.clear();
    }
}
