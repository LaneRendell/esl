/******************************************************************************
 * Program Name:            Customer.java
 * Your Name(s):            Charles Nicholson, Osama ..., Nazar ...
 * Professor's Name:        Christine Forde
 *
 *
 *
 * Formulas:
 */


import javax.swing.JOptionPane;

/**
 *
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
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @param amount
     * @param fee
     * @return
     */
    public double withdraw(double amount, double fee){

        if((amount + fee) < 0){
            System.err.println("Error: Withdraw amount is invalid.");
            System.err.println("Customer: " + this.name);
            System.err.println("Requested: " + amount);
            JOptionPane.showMessageDialog(null, "Error: Withdraw amount is invalid.\n"
                    + "Customer: " + this.name + "\nRequested: " + amount);

            return -1;
        } else if((this.balance - (amount + fee)) < 0) {
            System.err.println("Error: Insufficient funds");
            System.err.println("Customer: " + this.name);
            System.err.println("Requested: " + amount);
            System.err.println("Available: " + this.balance);
            JOptionPane.showMessageDialog(null, "Error: Insufficient funds\n"
                    + "Customer: " + this.name + "\nRequested: " + amount + "\nAvailable: " + this.balance);

            return -1;
        } else {
            this.balance = this.balance - (amount + fee);
            return this.balance;
        }
    }

    /**
     *
     * @param amount
     * @return
     */
    public double deposit(double amount){

        if(amount < 0){
            System.err.println("Error: Deposit amount is invalid.");
            System.err.println("Customer: " + this.name);
            System.err.println("Requested: " + amount);
            JOptionPane.showMessageDialog(null, "Error: Deposit amount is invalid.\n"
                    + "Customer: " + this.name + "\nRequested: " + amount);

            return -1;
        } else {
            this.balance = this.balance + amount;
            return this.balance;
        }
    }

    /**
     *
     * @return
     */
    public double addInterest(){
        this.balance = this.balance + (this.balance * INTEREST/100);
        return this.balance;
    }

    /**
     *
     * @param custArray
     * @param index
     * @param name
     * @param custNumber
     * @param balance
     * @param phone
     */
    public void addNewCustomer(Customer[] custArray, int index, String name,
                               long custNumber, double balance, String phone){
        custArray[index].name = name;
        custArray[index].idNumber = custNumber;
        custArray[index].balance = balance;
        custArray[index].phoneNumber = phone;
    }

    /**
     *
     * @param custArray
     * @param index
     * @param count
     */
    public void DeleteCustomer(Customer[] custArray, int index, int count){
        custArray[index] = custArray[count - 1];
    }

    /**
     *
     * @param custArray
     * @param count
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
