package filereader;

public class Table {

	private String tableName;
	private String brutFieldList;
	private String[] separatedFieldList;
	private int filesCounts;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getBrutFieldList() {
		return brutFieldList;
	}
	public void setBrutFieldList(String brutFieldList) {
		this.brutFieldList = brutFieldList;
	}
	public String[] getSeparatedFieldList() {
		return separatedFieldList;
	}
	public void setSeparatedFieldList(String[] separatedFieldList) {
		this.separatedFieldList = separatedFieldList;
	}
	public int getFielsCounts() {
		return filesCounts;
	}
	public void setFilesCounts(int filesCounts) {
		this.filesCounts = filesCounts;
	}
	
	public void UpdateValues(String tableName, String brutFieldList, String[] separatedFieldList, int filesCounts) {
		
		this.tableName = tableName;
		this.brutFieldList = brutFieldList;
		this.separatedFieldList = separatedFieldList;
		this.filesCounts = filesCounts;
	}	
	
}
