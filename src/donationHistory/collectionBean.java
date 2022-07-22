package donationHistory;

public class collectionBean {
	public String mobile;
	public String bgroup;
	public String dateofdonation;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public String getDateofdonation() {
		return dateofdonation;
	}
	public void setDateofdonation(String dateofdonation) {
		this.dateofdonation = dateofdonation;
	}
	public collectionBean()
	{
		
	}
	
	public collectionBean(String mobile, String bgroup, String dateofdonation) {
		super();
		this.mobile = mobile;
		this.bgroup = bgroup;
		this.dateofdonation = dateofdonation;
	}
	
	
}
