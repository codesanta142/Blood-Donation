package history;

public class donorBean {
	public String mobile;
	public String name;
	public String gender;
	public String address;
	public String city;
	public String bgroup;
	public int age;
	public String disease;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public donorBean()
	{
		
	}
	public donorBean(String mobile, String name, String gender, String address, String city, String bgroup, int age,
			String disease) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.bgroup = bgroup;
		this.age = age;
		this.disease = disease;
	}
}
