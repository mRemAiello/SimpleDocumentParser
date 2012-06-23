package Composite;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TXTFile extends GenericFile 
{
	public TXTFile(String ext) {
		extension = ext;
	}
	
	public String clean(File file) throws IOException {
		content = readWholeFile(file);
		return content;
	}

	public String clean(String content) throws IOException {
		this.content = content.toString();
		return this.content;
	}

	public String clean(URL url) throws IOException {
		content = readWholeFile(url);
		return content;
	}
}