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
		DataBaseWrapper dbwrapper = null;
		try {
			BufferedReader reader = Files.newBufferedReader(source, StandardCharsets.UTF_8);
			String line = null;
			Pattern p;
			Matcher m;
			boolean ifmatch;
			dbwrapper = new DataBaseWrapper();
			int currentLineNumber = 0;
			Table currentTable = new Table();
			while ((line = reader.readLine()) != null) {
				currentLineNumber++;
				if(line.isEmpty()){ //separation between tables
					continue;
				}
				/* reg exp result
				 * first group is table name
				 * second group fields name
				 */
				p = Pattern.compile("#(\\w+)\\((.*)\\)");
				m = p.matcher(line);
				ifmatch = m.matches();
				p = Pattern.compile(",");
				if(ifmatch) {
					currentTable.UpdateValues(m.group(1), m.group(2), p.split(m.group(2)), p.split(m.group(2)).length);
					if(dbwrapper != null){
						dbwrapper.createTable(currentTable.getTableName(), currentTable.getSeparatedFieldList());
					}
				}
				else{
					String valuesListQuery = "";
					String[] items = p.split(line);
					if(items.length != currentTable.getFielsCounts()) {
						System.out.println("Syntax error in line "+currentLineNumber);
					}
					else{
						for(int i=0; i < items.length; i++) {
							if(i==0){
								valuesListQuery = valuesListQuery+"'"+items[i]+"'";
							}
							else{
								valuesListQuery = valuesListQuery+",'"+items[i]+"'";
							}
						}
						if(dbwrapper != null){
							dbwrapper.insertValues(currentTable.getTableName(), currentTable.getBrutFieldList(), valuesListQuery);
						}
					}
				}
			}
		} catch (IOException x) {
			x.printStackTrace();
		} finally {
			if(dbwrapper != null){
				dbwrapper.confCleanUp();
			}
		}
	}

}
