package Models;
import java.time.LocalDateTime;


public class WalletTransactions 
{
	int transactionId;
	String description;
	LocalDateTime dateOfTransaction;
	double amount;
	double accountBalance;
	
	
	public WalletTransactions(int transactionId, String description, LocalDateTime dateOfTransaction, double amount,
			double accountBalance) 
	{
		super();
		this.transactionId = transactionId;
		this.description = description;
		this.dateOfTransaction = dateOfTransaction;
		this.amount = amount;
		this.accountBalance = accountBalance;
	}
	
	public int getTransactionId()
	{
		return transactionId;
	}
	
	public void setTransactionId(int transactionId)
	{
		this.transactionId = transactionId;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public LocalDateTime getDateOfTransaction() 
	{
		return dateOfTransaction;
	}
	
	public void setDateOfTransaction(LocalDateTime dateOfTransaction)
	{
		this.dateOfTransaction = dateOfTransaction;
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public void setAmount(double amount) 
	{
		this.amount = amount;
	}
	
	public double getAccountBalance()
	{
		return accountBalance;
	}
	
	public void setAccountBalance(double accountBalance) 
	{
		this.accountBalance = accountBalance;
	}
	
}
