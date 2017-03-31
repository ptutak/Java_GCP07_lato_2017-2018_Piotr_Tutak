package gui2;

import java.util.UUID;

class LogPass {
	private String login;
	private String password;
	private String sex;
	private int age;
	private String location;
	
	
	String getLogin() {
		return login;
	}
	String getPassword() {
		return password;
	}
	String getSex() {
		return sex;
	}
	int getAge() {
		return age;
	}
	String getLocation() {
		return location;
	}
	void setLogin(String login) {
		this.login = login;
	}
	void setPassword(String password) {
		this.password = password;
	}
	void setSex(String sex) {
		this.sex = sex;
	}
	void setAge(int age) {
		this.age = age;
	}
	void setLocation(String location) {
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogPass other = (LogPass) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}
