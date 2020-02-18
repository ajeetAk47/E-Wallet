import java.time.LocalDateTime;
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
					System.err.println("\nThank You.....\nExit");
					System.exit(0);
				}
				
		default:
				System.err.println("\nUnexpected Selection -> " + choice);
		}
		
		
	}

	private static void login() 
	{	
		String user;
		String password;
		
		Scanner inputLogin=new Scanner(System.in);
		System.out.println("\n	Login Screen");
		System.out.println("-----------------------");
		System.out.print("User Id :- ");
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
			System.err.println("\nPlease Create Account.....");
			System.err.println("****************************");
			System.err.println("Redirecting to  Home Screen");
			welcomeScreen();
		}
		else {
			int count=0;
			for (WalletUser walletUser : userList) {
				if(user.equals(walletUser.getLoginName()))
				{	
				
						if(password.equalsIgnoreCase(walletUser.getPassword())) 
						{
							System.out.println("Welcome To  Your Wallet");
							profile(walletUser);
							break;
						}
						else {
							
								count++;
							
						}	

				}		
			}
			if (count==userList.size())
			{
				System.err.println("\nPlease Create Account Or Incorrect Password.....");
				System.err.println("****************************");
				System.err.println("Redirecting to  Home Screen");
				welcomeScreen();
			}
		}
		//inputLogin.close();
		
	}

	private static void adminView() 
	{
		double money=0.0;
		Scanner adminInput=new Scanner(System.in);
		System.out.println("---------------------------------");
		System.out.println("	Admin Panel");
		System.out.println("---------------------------------");
		System.out.println("Total User Registered -"+userList.size());
		System.out.println("Total Transactions -"+transactionsLog.size());
		System.out.print("Total Money in Wallets -");
		
		for(WalletAccount walletAccount :	walletList) 
		{
			money=money+walletAccount.getAccountBalance();
		}
		while(true) {
			System.out.println(money+"/-");
			System.out.println("---------------------------------");
			System.out.println("	Admin Menu");
			System.out.println("1. Transaction logs");
			System.out.println("2. Search User");
			//System.out.println("3. Approve Wallets");  //Pending
			System.out.print("Press Any Other Key for Home Screen :-");
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
				System.err.println("\nUnexpected Selection -> " + choice);
				System.err.println("****************************");
				System.err.println("Redirecting to  Home Screen");
				welcomeScreen();
			}
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
		System.out.println("Enter  Login Id of User to  be searched :-");
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
		Scanner profileInput=new Scanner(System.in);
		System.out.println("\n	Wallet Information");
		System.out.println("---------------------------------------------");
		System.out.println("Wallet Holder Name    - "+walletUser.getUserName());
		System.out.println("Wallet Number		- "+walletUser.getUserId());
		
		for(WalletAccount walletAccount :	walletList) 
		{
			if(walletAccount.getAccountId()==walletUser.getUserId()) 
			{
				System.out.println("Wallet Balance		- "+walletAccount.getAccountBalance()+" /-");
				break;
			}
		}
		
		System.out.println("---------------------------------------------");
		System.out.println("	Wallet Menu");
		System.out.println("1. Add Money  to  Wallet");
		System.out.println("2. Transfer Money");
		System.out.println("Any Other Input to Sign Out");
		System.out.println("---------------------------------------------");
		System.out.print("Select - ");
		choice=profileInput.nextInt();
		switch (choice) {
		case 1: 
		{
			System.out.println("-----------------------------------");
			System.out.print("Enter Money  to Add in Your Wallet -");
			double addmoney=profileInput.nextDouble();
			for(WalletAccount walletAccount :	walletList) 
			{
				if(walletAccount.getAccountId()==walletUser.getUserId()) 
				{	
					walletAccount.setAccountBalance(walletAccount.getAccountBalance()+addmoney);
					System.out.println("New Wallet Balance is "+walletAccount.getAccountBalance()+" /-");
					break;
				}
			}
			break;
		}
		case 2:
		{	
			double transfer_amount;
			String transfer_id;
			
			System.out.println("------------------------------------");
			System.out.println("	Money Transfer ");
			System.out.print("Enter Money to Transfer :- ");
			transfer_amount=profileInput.nextDouble();
			System.out.print("Enter User id for transfer :- ");
			transfer_id=profileInput.nextLine();
			WalletUser receiver=null;
			WalletAccount sender=null;
			for(WalletAccount walletAccount :	walletList) 
			{
				if(walletAccount.getAccountId()==walletUser.getUserId()&&walletAccount.getAccountBalance()>=transfer_amount) 
				{	
					
					for (WalletUser findUser : userList) 
					{
						if(transfer_id.equalsIgnoreCase(findUser.getLoginName()))
						{
							System.out.println(transfer_amount+" "+transfer_id);
							receiver=findUser;
							sender=walletAccount;
							break;
						}
					}

				}
				else if(walletAccount.getAccountId()==walletUser.getUserId()&&walletAccount.getAccountBalance()<transfer_amount)
				{
					System.err.println("Your account  Balance  is  insufficient for  this  Transaction");
					profile(walletUser);
					break;
				}
			}
			
			if(receiver!=null) 
			{
				transferMoney(sender,transfer_amount,receiver);
				
				System.out.println("Money  Transfered.....");
				System.out.println("New Wallet Balance is "+sender.getAccountBalance());
				profile(walletUser);
				
			}
			else
			{
				System.err.println("No user Exits with  this Number...");
				profile(walletUser);
			}
			break;
		}
		default:
			System.err.println("\nUnexpected Selection -> " + choice);
			System.err.println("Signing Out.....");
			System.err.println("****************************");
			System.out.println("Redirecting to  Home Screen");
			welcomeScreen();
		}
		
	}

	private static void transferMoney(WalletAccount senderWallet, double transfer_amount,  WalletUser receiver)
	{	
		String  description;	
		Scanner transferInput=new  Scanner(System.in);
		System.out.print("Enter  description for  transaction :- ");
		description=transferInput.nextLine();
		
		WalletTransactions senderTransactions=new WalletTransactions(transactionId, description, LocalDateTime.now(), transfer_amount, senderWallet.getAccountBalance());
		senderWallet.getTransactionHistory().add(senderTransactions);
		transactionsLog.add(senderTransactions);
		
		senderWallet.setAccountBalance(senderWallet.getAccountBalance()-transfer_amount);
		
		for (WalletAccount walletAccount : walletList) 
		{
			if(receiver.getUserId()==walletAccount.getAccountId()) 
			{	
				walletAccount.setAccountBalance(walletAccount.getAccountBalance()+transfer_amount);
				WalletTransactions receiverTransactions=new WalletTransactions(transactionId, description, LocalDateTime.now(), transfer_amount,walletAccount.getAccountBalance());
				walletAccount.getTransactionHistory().add(receiverTransactions);
				transactionsLog.add(senderTransactions);
				transactionId++;
				break;
			}
		}
		

		
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
		loginName=inputDetails.nextLine();
		if(userList.size()>0)
		{
			while(userCheck){
				int userCount=0;
				for (WalletUser walletUser : userList) 
				{	
					if(loginName.equals(walletUser.getLoginName()))
					{
						userCheck=true;
						break;
					}
					else 
					{
						userCount++;
					}
				}
				if(userCount==userList.size()) {
					userCount=0;
					userCheck=false;
					break;
				}
				else 
				{
					System.out.println("User Name Already  Exits Please Try Another User Name");
					loginName=inputDetails.nextLine();
				}
			}
		}
		
		
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
