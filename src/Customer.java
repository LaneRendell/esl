/******************************************************************************
 * Program Name:            Customer.java
 * Your Name(s):            Charles Nicholson, Osama ..., Nazar ...
 * Professor's Name:        Christine Forde
 *
 *
 *
 * Formulas:
 * Balance After Deposit: Amount  + (Amount * Interest Rate)
 * Balance After Withdrawal: Balance – (Amount + 1.50 Transaction Fee) + (newBalance * Interest Rate)
 * Interest Calculation:  newBalance * Interest Rate
 */


import javax.swing.JOptionPane;

/**
 * This class holds information about a customer.
 * It does transactions with their balance.
 */
public class Customer {

    /**
     *  Amount of interest used for calculations.
     */
    final double INTEREST = 4.5;

    /**
     *  The name of the customer.
     */
    private String name;

    /**
     *  The customer's id number.
     */
    private long idNumber;

    /**
     *  The customer's balance in their account.
     */
    private double balance;

    /**
     *  The customer's phone number. No calculations are done, so we use a
     *  string.
     *
     */
    private String phoneNumber;

    /**
     * Default constructor. Creates object with empty data-fields.
     */
    public Customer(){

    }

    /**
     * Non-default constructor. Let's us instantiate an object with specified values.
     * @param name          String Full name of customer, no spaces.
     * @param idNumber      long The customer's id number.
     * @param balance       double The balance the customer has in their account.
     * @param phoneNumber   String The customer's phone number.
     */
    public Customer(String name, long idNumber, double balance, String phoneNumber){
        this.name = name;
        this.idNumber = idNumber;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the customer's name.
     *
     * @return String The customer's name.
     */
    public String getCustomerName() {

        return this.name;
    }

    /**
     * Sets the customer's name.
     * @param name String The customer's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the customer's id Number.
     *
     * @return long The customer's id number.
     */
    public long getCustomerNumber() {

        return this.idNumber;
    }

    /**
     * Sets the customer's id number.
     * @param idNumber long Customer's id number/
     */
    public void setID(long idNumber) {

        this.idNumber = idNumber;
    }

    /**
     * Returns the balance.
     *
     * @return double The balance in the customer's account.
     */
    public double getBalance() {

        return this.balance;
    }

    /**
     * Sets the customer's account balance.
     * @param balance double Set customer's balance.
     */
    public void setBalance(double balance) {

        this.balance = balance;
    }

    /**
     * Returns the customer's phone number.
     *
     * @return String The customer's phone number.
     */
    public String getPhoneNumber() {

        return this.phoneNumber;
    }

    /**
     * Sets the customer's phone number.
     *
     * @param phoneNumber String Phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @param amount double The amount you wish to withdraw
     * @param fee double The amount charged per withdrawal.
     * @return double Returns the balance in the account.
     */
    public double withdraw(double amount, double fee){

        if((amount + fee) < 0){ // If withdrawal amount + fee is negative

            System.err.println("Error: Withdraw amount is invalid.");
            System.err.println("Customer: " + this.name);
            System.err.println("Requested: " + amount);
            JOptionPane.showMessageDialog(null, "Error: Withdraw amount is invalid.\n"
                    + "Customer: " + this.name + "\nRequested: " + amount);

            return -1; // Return an error.

        } else if((this.balance - (amount + fee)) < 0) {
            // Funds are negative

            System.err.println("Error: Insufficient funds");
            System.err.println("Customer: " + this.name);
            System.err.println("Requested: " + amount);
            System.err.println("Available: " + this.balance);

            JOptionPane.showMessageDialog(null, "Error: Insufficient funds\n"
                    + "Customer: " + this.name + "\nRequested: " + amount + "\nAvailable: " + this.balance);

            return -1;  // Return an error
        } else {
            /* No error */
            this.balance = this.balance - (amount + fee);
            return this.balance;
        }
    }

    /**
     * Deposits money into the account and returns the total before interest.
     *
     * @param amount double The amount being deposited into the account.
     * @return double Returns the balance in the account after deposit.
     */
    public double deposit(double amount){

        if(amount < 0)
        { // cannot have negative amounts

            System.err.println("Error: Deposit amount is invalid.");
            System.err.println("Customer: " + this.name);
            System.err.println("Requested: " + amount);
            JOptionPane.showMessageDialog(null, "Error: Deposit amount is invalid.\n"
                    + "Customer: " + this.name + "\nRequested: " + amount);

            return -1;  // error
        } else {
            this.balance = this.balance + amount;
            return this.balance;
        }
    }

    /**
     * Adds interest amount by using formula:
     * New balance = (balance * interest rate) + balance
     *
     * @return double The new balance.
     */
    public double addInterest(){
        this.balance = this.balance + (this.balance * INTEREST/100);
        return this.balance;
    }

    /**
     * Creates a new customer object and puts it in the customer array.
     *
     *
     * @param custArray Customer[] An array of customer objects.
     * @param index int The place in the array where the customer object goes.
     * @param name String The name of the customer.
     * @param custNumber long The ID of the customer.
     * @param balance double The starting balance in the customer's account.
     * @param phone String The phone number of the customer.
     */
    public void addNewCustomer(Customer[] custArray, int index, String name,
                               long custNumber, double balance, String phone){
        custArray[index].name = name;
        custArray[index].idNumber = custNumber;
        custArray[index].balance = balance;
        custArray[index].phoneNumber = phone;
    }

    /**
     * Removes a customer from the customer array. The garbage collector gets it later.
     *
     * @param custArray Customer[] An array of customer objects.
     * @param index int The position of the object to remove.
     * @param count int The length of the array.
     */
    public void DeleteCustomer(Customer[] custArray, int index, int count){
        custArray[index] = custArray[count - 1];
    }

    /**
     * Sorts the customer array alphabetically.
     *
     *
     * @param custArray The array of customer objects.
     * @param count The amount of objects in the array.
     */
    public void nameSort(Customer[] custArray, int count){
        // use select sort
        for (int i = 0; i < count - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < count; j++)
                if (custArray[j].name.compareTo(custArray[index].name) < 0)
                    index = j;

            Customer smallCust = custArray[index];
            custArray[index] = custArray[i];
            custArray[i] = smallCust;
        }
    }

    /**
     * Searches an array of Customer objects for a name and returns the index if found. -1 if not.
     *
     * @param custArray The array of customer objects.
     * @param name      The name we are searching fpr
     * @param count     Count is the length of the array.
     * @return Returns the index where the name is or -1 if not found.
     */
    public int findIndex(Customer[] custArray, String name, int count){

        // Cannot be given a null name
        if((name == null) || (name == "")){
            return -1;
        }

        // loops through array, compares to name with .equals() method.
        for(int i = 0; i < count; i++){
            if(custArray[i] != null && name.equals(custArray[i].name)){
                return i;
            }
        }

        // if it hits this, something very wrong.
        return -1;
    }

    /**
     * Converts data fields to a string.
     *
     * @return Returns the data fields as Strings.
     */
    public String toString(){
        return (this.name + "\t" + this.idNumber + "\t$" + this.balance + "\t" + this.phoneNumber);
    }
}
