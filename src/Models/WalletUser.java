package Models;

public class WalletUser 
{
		
	int  userId;
	String userName;
	String password;
	String phoneNumber;
	String loginName;
	
	
	// For Create New Account
	
	public WalletUser(int userId, String userName, String password, String phoneNumber, String loginName)
	{
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.loginName = loginName;
	}
	

	// For Login Purpose
	public WalletUser(String password, String loginName)
	{
		super();
		this.password = password;
		this.loginName = loginName;
	}


	public int getUserId()
	{
		return userId;
	}
	
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPhoneNumber() 
	{
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	public String getLoginName()
	{
		return loginName;
	}
	
	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}
	
}
