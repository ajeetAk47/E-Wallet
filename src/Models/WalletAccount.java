package Models;

import java.util.ArrayList;
import java.util.List;

import Models.WalletTransactions;

public class WalletAccount
{
	int accountId;
	double accountBalance;
	enum status
	{
		PENDING,APPROVED,REJECT;
	}
	
	List<WalletTransactions> TransactionHistory=new ArrayList<WalletTransactions>();
	
	
		

	public WalletAccount(int accountId, double accountBalance) 
	{
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
	}


	public int getAccountId()
	{
		return accountId;
	}
	
	public void setAccountId(int accountId)
	{
		this.accountId = accountId;
	}
	
	public double getAccountBalance()
	{
		return accountBalance;
	}
	
	public void setAccountBalance(double accountBalance)
	{
		this.accountBalance = accountBalance;
	}
	
	public List<WalletTransactions> getTransactionHistory() 
	{
		return TransactionHistory;
	}
	
	public void setTransactionHistory(List<WalletTransactions> transactionHistory)
	{
		TransactionHistory = transactionHistory;
	}
	
}
