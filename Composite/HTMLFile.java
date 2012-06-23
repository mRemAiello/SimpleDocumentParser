package Composite;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLFile extends GenericFile 
{
	private static final String regex = "";
	
	public HTMLFile(String ext) {
		extension = ext;
	}
	
	private String findMatch(String input) {
		Matcher matcher = Pattern.compile(regex).matcher(input);
		StringBuffer output = new StringBuffer();

		while (matcher.find()) {
			String fixed = matcher.group();
			fixed = fixed.replaceAll("<body>","");
			fixed = fixed.replaceAll("</body>","");
			output.append(fixed);
		}
		
		return output.toString();
	}
	
	public String clean(File file) throws IOException {
		content = readWholeFile(file);
		content = findMatch(content);
		return content;
	}

	public String clean(String input) throws IOException {
		content = findMatch(input);
		return content;
	}

	public String clean(URL url) throws IOException {
		content = readWholeFile(url);
		content = findMatch(content);
		return content;
	}
}
