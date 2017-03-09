package tools;

public class Doc {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Doc(String name, String part, String code) {
		super();
		this.name = name;
		this.part = part;
		this.code = code;
	}
	public Doc()
	{
		
	}
	String name;
	String part;
	String code;
}
