package forgotPass;

public class User {
	
	private String username;
	private String useremail;
	private String usercode;

	public User(String username, String useremail, String usercode) {
		super();
		this.username = username;
		this.useremail = useremail;
		this.usercode = usercode;
	}
	
	
	public String getUsername() {
		return username;
	}

	

	public String getUseremail() {
		return useremail;
	}

	
	public String getUsercode() {
		return usercode;
	}

	

}
