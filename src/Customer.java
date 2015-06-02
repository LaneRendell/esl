import javax.swing.*;

/**
 * Created by Charles on 6/2/2015.
 */
public class Customer {

    private String name;
    private String idNumber;
    private double balance;
    private long phoneNumber;

    public final double FEE = 1.25;

    public Customer()
    {

    }

    public Customer (String name, String idNumber, double balance, long phoneNumber)
    {
        this.name = name;
        this.idNumber = idNumber;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIdNumber()
    {
        return this.idNumber;
    }

    public void setID(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public double getBalance()
    {
        return this.balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void setPhoneNumber(long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public long getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public double withdraw(double amount)
    {
        if(amount < 0)
        {
            // Display Error
            JOptionPane.showMessageDialog(null, "Error: Amount cannot be negative.");
        } else if (amount > this.balance) {

        } else {

        }

        return amount;
    }

    public double addInterest()
    {
        return 0;
    }

    public int findIndex(Customer[] custArray, String name, int count)
    {
        return 0;
    }

    public double deposit(double amount)
    {
        return 0;
    }

    public void addNewCustomer(Customer[] custArray, int count, String name, String custNumber,
                               double balance, long phone)
    {

    }

    public void nameSort(Customer[] custArray, int count)
    {

    }


}
