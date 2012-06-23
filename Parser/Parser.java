package Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser
{ 
	private final static Parser INSTANCE = new Parser();
	private static StringBuffer fileParsed = new StringBuffer();
	private int occurrency = 0;

	private Parser() {}
	
	public void parse(String content, String word) {
		Matcher match = Pattern.compile(word).matcher(content);
		occurrency = match.groupCount();
		fileParsed = new StringBuffer();
		fileParsed.insert(0,"<html>\n<head>\n</head>\n<body>\n");
		content = content.replaceAll(word, "<b><i><u>"+word+"</u></b><i>" );
		content = content.replaceAll("\n","<br />");
		fileParsed.append(content);
		fileParsed.append("\n</body>\n</html>");
	}
	
	public void parse(String content, String word, boolean isBold, boolean isUnderline, boolean isItalic) {
		String tagFix = "";
		if(isBold) tagFix += "<b>";
		if(isItalic) tagFix += "<i>";
		if(isUnderline) tagFix += "<u>";
		String closeTag = tagFix.replaceAll("<", "</");
		Matcher match = Pattern.compile(word).matcher(content);
		occurrency = match.groupCount();
		fileParsed = new StringBuffer();
		fileParsed.insert(0,"<html>\n<head>\n</head>\n<body>\n");
		content = content.replaceAll(word, tagFix+word+closeTag);
		content = content.replaceAll("\n","<br />");
		fileParsed.append(content);
		fileParsed.append("\n</body>\n</html>");
	}
	
	public int getNumOccurrency() {
		return occurrency;
	}
	
	public String toString() {
		return fileParsed.toString();
	}

	public static Parser getInstance() {
		return INSTANCE;
	}
}
