package filereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {


	public static void main(String[] args) throws IOException {
        
		Path source = Paths.get("test.txt", args);
        try {
        	BufferedReader reader = Files.newBufferedReader(source, StandardCharsets.UTF_8);
            String line = null;
            Pattern p;
            Matcher m;
            boolean ifmatch;
            while ((line = reader.readLine()) != null) {
            	/* reg exp result
            	 * first group is table name
            	 * second group fields name
            	 */
            	String currentTablename;
            	String currentFiledsList;
            	p = Pattern.compile("#(\\w+)\\((.*)\\)");
            	m = p.matcher(line);
            	ifmatch = m.matches();
            	System.out.println(line);
            	if(ifmatch) {
            		currentTablename = m.group(1);
            		currentFiledsList = m.group(2);
            		System.out.println("table name: "+currentTablename);
            		System.out.println("table fields list: "+currentFiledsList);

            	}
            	else{
            		System.out.println("Values List: "+line);
            		p = Pattern.compile(",");
            		String[] items = p.split(line);
            	    for(int i=0; i <= (items.length-1); i++) {
            	        System.out.println("Value " + i + " : " + items[i]);
            	    }
            	}
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
	}

}
