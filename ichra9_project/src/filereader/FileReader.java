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

				Table currentTable = new Table();
				/* reg exp result
				 * first group is table name
				 * second group fields name
				 */
				p = Pattern.compile("#(\\w+)\\((.*)\\)");
				m = p.matcher(line);
				ifmatch = m.matches();
				System.out.println(line);
				p = Pattern.compile(",");
				if(ifmatch) {
					currentTable.UpdateValues(m.group(1), m.group(2), p.split(m.group(2)), (p.split(m.group(2)).length - 1));
					System.out.println("table name: "+currentTable.getTableName());
					System.out.println("table fields list: "+currentTable.getBrutFieldList());
				}
				else{
					System.out.println("Values List: "+line);
					String[] items = p.split(line);
					if((items.length - 1) != currentTable.getFielsCounts()) {
						System.out.println("Error fiels number not respected");
					}
					else{
						for(int i=0; i <= (items.length-1); i++) {
							System.out.println("Value " + i + " : " + items[i]);
						}	
					}
				}
			}
		} catch (IOException x) {
			x.printStackTrace();
		}
	}

}
