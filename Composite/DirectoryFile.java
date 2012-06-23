package Composite;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DirectoryFile extends GenericFile
{
    private List<GenericFile> mChildFile = new ArrayList<GenericFile>();
 
	public DirectoryFile(String ext) {
		extension = ext;
	}
    
    //Aggiunge il file alla composizione.
    public void add(GenericFile file) {
        mChildFile.add(file);
    }
 
    //Rimuove il file dalla composizione.
    public void remove(GenericFile file) {
        mChildFile.remove(file);
    }
    
    public void clear() {
    	mChildFile.clear();
    }

	public String clean(File file) throws IOException {
		return null;
	}

	public String clean(String content) throws IOException {
		return null;
	}

	public String clean(URL url) throws IOException {
		return null;
	}
}