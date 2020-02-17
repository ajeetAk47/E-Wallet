import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Models.WalletAccount;
import Models.WalletTransactions;
import Models.WalletUser;

public class HomeScreen 
{	
	static int choice;
	static List<WalletUser> userList=new ArrayList<WalletUser>();
	static List<WalletAccount> walletList=new ArrayList<WalletAccount>();
	static List<WalletTransactions> transactionsLog=new ArrayList<WalletTransactions>();
	
	static int  userId=1;
	static int accountId=1;
	static int transactionId=1;
	static Scanner input=new Scanner(System.in);
	
	
	public static void main(String[] args) 
	{			
		while(true) 
		{
			welcomeScreen();
		}
	}
	
	private static void welcomeScreen() 
	{	
	
		System.out.println("________________________________");
		System.out.println("	Welcome To Wallet");
		System.out.println("	-----------------");
		System.out.println("	1. Create New Account");
		System.out.println("	2. Login  ");
		System.out.println("	3. Quit");
		System.out.println("________________________________");
		System.out.print("	Select ->");
		choice=input.nextInt();
		
		
		switch (choice)
		{
		case 1: {
					createAccount();
					break;
				}
				
		case 2:	{
					login();
					break;
				}
		case 3:	{	
					System.out.println("\nThank You.....\nExit");
					System.exit(0);
				}
				
		default:
				System.out.println("\nUnexpected Selection -> " + choice);
		}
		
		
	}

	private static void login() 
	{	
		String user;
		String password;
		
		Scanner inputLogin=new Scanner(System.in);
		System.out.println("\n	Login Screen");
		System.out.println("-----------------------");
		System.out.print("User Name :- ");
		user=inputLogin.nextLine();
		System.out.print("Password :- ");
//		Console console = System.console();
//		char[] passwordEntered = console.readPassword("Enter Passwo ");
//		password=passwordEntered.toString();
		password=inputLogin.nextLine();
		
		if(user.equals("admin") &&password.equals("admin"))
		{
			adminView();
		}
		else if(userList.size()==0) 
		{
			System.out.println("\nPlease Create Account.....");
			System.err.println("****************************");
			System.out.println("Redirecting to  Home Screen");
			welcomeScreen();
		}
		else {
			for (WalletUser walletUser : userList) {
				if(user.equalsIgnoreCase(walletUser.getLoginName()))
				{	
					int count=2;
	
					do {
					
						if(password.equalsIgnoreCase(walletUser.getPassword())) 
						{
							System.out.println("Welcome To  Your Wallet");
							profile(walletUser);
							break;
						}
						count--;
					}while(count>0);

				}
				else 
				{
					System.out.println("\nPlease Create Account.....");
					System.err.println("****************************");
					System.out.println("Redirecting to  Home Screen");
					welcomeScreen();
				}
				
			}	
		}
		//inputLogin.close();
		
	}

	private static void adminView() 
	{
		double money=0.0;
		Scanner adminInput=new Scanner(System.in);
		System.out.println("	Admin Panel");
		System.out.println("---------------------------------");
		System.out.println("Total User Registered -"+userList.size());
		System.out.println("Total Transactions -"+transactionsLog.size());
		System.out.print("Total Money in Wallets -");
		
		for(WalletAccount walletAccount :	walletList) 
		{
			money=money+walletAccount.getAccountBalance();
		}
		System.out.println(money+"/-");
		System.out.println("---------------------------------");
		System.out.println("	Admin Menu");
		System.out.println("1. Transaction logs");
		System.out.println("2. Search User");
		//System.out.println("3. Approve Wallets");  //Pending
		System.out.println("Press Any Other Key for Home Screen");
		choice=adminInput.nextInt();
		switch (choice) {
		case 1: 
			{	
				transactionLogMeathod();
				break;
			}
		case 2:
			{	
				findUserDetails();
				break;
			
			}
		default:
			System.out.println("\nUnexpected Selection -> " + choice);
			System.err.println("****************************");
			System.out.println("Redirecting to  Home Screen");
			welcomeScreen();
		}
		
	}

	private static void transactionLogMeathod() {
		if(transactionsLog.size()==0) {
			System.out.println("No Transactions .....");
		}
		else {

			for (WalletTransactions walletTransactions : transactionsLog)
			{	
				
				System.out.println("\n\nTransaction Id - "+walletTransactions.getTransactionId());
				System.out.println("Date of Transaction - "+walletTransactions.getDateOfTransaction());
				System.out.println("Discription - "+walletTransactions.getDescription());
				System.out.println("Amount Transfered - "+walletTransactions.getAmount());
				
			}
		}
		
	}

	private static void findUserDetails() 
	{	Scanner searchedInput=new Scanner(System.in);
		String searchedUser;
		System.out.println("Enter  Name of  User to  be searched :-");
		searchedUser=searchedInput.nextLine();
		for (WalletUser walletUser : userList) 
		{
			if(searchedUser.equalsIgnoreCase(walletUser.getLoginName())) 
			{
				System.out.println("User Name is "+walletUser.getUserName());
				for (WalletAccount walletAccount : walletList) 
				{
					if(walletUser.getUserId()==walletAccount.getAccountId()) 
					{	
						System.out.println("User Account Balance is "+walletAccount.getAccountBalance());
						System.out.println("Total Number of Transaction "+walletAccount.getTransactionHistory().size());
						break;
					}
				}
				break;
			}
		}
		
	}

	private static void profile(WalletUser walletUser)
	{
		System.out.println("	Account Information");
		System.out.println("---------------------------------------------");
		System.out.println("Account Holder Name    - "+walletUser.getUserName());
		System.out.println("Account Number		- 0000"+walletUser.getUserId());
		
		for(WalletAccount walletAccount :	walletList) 
		{
			if(walletAccount.getAccountId()==walletUser.getUserId()) 
			{
				System.out.println("Account Balance		- "+walletAccount.getAccountBalance()+" /-");
			}
		}
		
		System.out.println("---------------------------------------------");
		
		
		
	}

	private static void createAccount()
	{
		
		String userName;
		String password;
		String rePassword;
		String phoneNumber;
		String loginName;
		boolean userCheck=true;
		boolean passwordCheck=true;
		
		Scanner inputDetails=new Scanner(System.in);
		System.out.println("Enter Details to Create Account ");
		System.out.print("Enter Your Full Name:- ");
		userName=inputDetails.nextLine();
		System.out.print("Enter your Phone Number :- ");
		phoneNumber=inputDetails.nextLine();
		System.out.print("Enter your User Id :- ");
		
		do {
			loginName=inputDetails.nextLine();
			for (WalletUser walletUser : userList) 
			{
				if(loginName.equalsIgnoreCase(walletUser.getUserName()))
				{
					userCheck=false;
					break;
				}
			}
			if(userCheck==false) 
			{
				System.out.println("User Name Already  Exits Please Try Another User Name");
				userCheck=true;
			}
			if(userList.size()>0)
			{
				userCheck=false;
			}
		}while(!userCheck);
		
		do {
			System.out.print("Enter new Password :- ");
			password=inputDetails.nextLine();
			if(password.length()<9){
				System.out.println("Password Must be  Greater then 8");
			}
			else 
			{
				System.out.print("Re-Enter password :- ");
				rePassword=inputDetails.nextLine();
				if(password.equals(rePassword)) 
				{
					passwordCheck=false;
				}
				else {
					System.out.println("Both Password Must be Same..... ");
				}
			}		
		}while(passwordCheck);
		
		WalletUser newAccount=new WalletUser(userId, userName, password, phoneNumber, loginName);
		userList.add(newAccount);
		WalletAccount newWallet=new WalletAccount(accountId, 1000); 
		walletList.add(newWallet);
		accountId++;
		userId++;
	}
}
