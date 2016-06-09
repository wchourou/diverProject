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
            	p = Pattern.compile("#*\\(*\\)");
            	m = p.matcher(line);
            	ifmatch = m.matches();
            	if(ifmatch) {
            	    for(int i=0; i <= m.groupCount(); i++) {
            	        System.out.println("Groupe " + i + " : " + m.group(i));
            	    }
            	}
            	else{
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
