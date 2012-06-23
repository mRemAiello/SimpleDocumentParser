package Composite;

import java.io.File;

public class Resource
{
	public static GenericFile getAppropriateFile(String path) {
		File file = new File(path);
		String ext = "";
		if (file.isFile())
			ext = path.substring(path.lastIndexOf('.'), path.length());
		switch(ext) {
		case ".txt":
			return getTXTFile(ext);
		case ".xml":
			return getXMLFile(ext);
		case ".html":
			return getHTMLFile(ext);
		case "":
			return getDirectoryFile(ext);
		}
		return null;
	}
	
	private static GenericFile getHTMLFile(String ext) {
		return new HTMLFile(ext);	
	}
	private static GenericFile getXMLFile(String ext) {
		return new XMLFile(ext);	
	}
	private static GenericFile getTXTFile(String ext) {
		return new TXTFile(ext);	
	}
	private static GenericFile getDirectoryFile(String ext) {
		return new DirectoryFile(ext);	
	}
}
