package activity.hhzmy.com.myaa.bean;

public class Data {

	private String content;
	private String updatetime;
	public Data(String content, String updatetime) {
		super();
		this.content = content;
		this.updatetime = updatetime;
	}
	public Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
}
