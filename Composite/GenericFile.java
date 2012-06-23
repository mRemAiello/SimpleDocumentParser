package Composite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/** 
 * 
 * @author Mirko Raimondo Aiello
 * 
 */
public abstract class GenericFile
{
	protected String content;
	protected String extension;
	
	public abstract String clean(File file) throws IOException;
	public abstract String clean(String content) throws IOException;
	public abstract String clean(URL url) throws IOException;
    public void add(GenericFile file) {}
    public void remove(GenericFile file) {}
    public void clear() {}

    public String getExtension() { return extension; }
	public String toString() { return content; }
	
	protected String readWholeFile (File file) throws IOException {
		InputStreamReader isr = null;

		StringBuffer sb = new StringBuffer ();
		char[] buffer = new char[4096];
		int len;

		try
		{
			FileInputStream fis = new FileInputStream (file);
			isr = new InputStreamReader (fis);

			while ((len = isr.read (buffer)) > 0)
				sb.append (buffer, 0, len);

			return sb.toString();
		}
		finally
		{
			if (isr != null)
				isr.close ();
		}
	}
	
	@SuppressWarnings("unused")
	protected String readWholeFile (URL url) throws IOException {
		URLConnection urlConn = url.openConnection();
		InputStream is = null;

		StringBuffer sb = new StringBuffer();
		byte[] buffer = new byte[4096];

		try
		{
			is = urlConn.getInputStream();

			int len;
			while ((len = is.read(buffer)) > 0)
				sb.append(new String( buffer, "UTF-8" ));

			return sb.toString();
		}
		finally
		{
			if (is != null)
				is.close();
		}
	}

}