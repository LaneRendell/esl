/******************************************************************************
 * Program Name:            Customer.java
 * Your Name(s):            Charles Nicholson, Osama Alqahtani, and Nazar Al-Wattar
 * Professor's Name:        Christine Forde
 *
 * Classes: BankDriver
 *
 * Formulas:
 * Balance After Deposit: Amount  + (Amount * Interest Rate)
 * Balance After Withdrawal: Balance – (Amount + 1.50 Transaction Fee) + (newBalance * Interest Rate)
 * Interest Calculation:  newBalance * Interest Rate
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *  The class that has the main method in it.
 *  Creates an array of customer objects by reading a file.
 *  Uses JOptionPane to create a menu.
 */
public class BankDriver {

    /**
     * Displays numbers in currency format.
     */
    private NumberFormat fmt = NumberFormat.getCurrencyInstance();

    /**
     * Fee that comes with withdrawal
     */
    final double FEE = 1.50;

    /**
     * Max number in customer array
     */
    final int MAX = 30;

    /**
     * String used for JOption Pane Title
     */
    final String BANK = "Bank System";

    /**
     * Array of customer objects.
     */
    Customer[] custArray = new Customer[MAX];

    /**
     * Array that is used to loop over arrays.
     */
    int count = 0;

    /**
     * Used to return a found index.
     */
    int index;

    /**
     * Tokenizer is used when reading file.
     */
    StringTokenizer tokenizer;

    /**
     * Keeps track of line in file.
     */
    String line;

    /**
     * Name of text file with records in it.
     */
    String file = "customer.txt";


    // variables that correspond to private data fields
    String name;
    long custNumber;
    double balance;
    String phone;

    /**
     * Boolean used to keep program running. Change this to exit.
     */
    boolean quit = false;

    /**
     * Used to make menu choices.
     */
    int choice;

    String OutPutText = "";
    String xnam;

    /**
     * The main driver of the program. Does manipulations on an array of Customer
     * objects.
     */
    public void runBankTest(){
        System.out.println("Welcome to the Bank");

        try{
            FileReader fr = new FileReader(file);
            BufferedReader inFile = new BufferedReader(fr);

            line = inFile.readLine();

            while(line != null && count < MAX){
                tokenizer = new StringTokenizer(line);

                name = tokenizer.nextToken();
                try {
                    custNumber = Long.parseLong(tokenizer.nextToken());
                    balance = Double.parseDouble(tokenizer.nextToken());
                    phone = tokenizer.nextToken();
                    custArray[count++] = new Customer(name, custNumber, balance, phone);
                }
                catch(NumberFormatException exception){
                    System.out.println("Error in input. Line ignored: ");
                    System.out.println(line);
                }

                line = inFile.readLine();
            }

            inFile.close(); // close the file

            System.out.println("\t\tUnsorted " + file + " records");
            System.out.println();

            for(int scan = 0; scan < count; scan++){
                System.out.println(custArray[scan]);
            }

            // Run program while boolean is set to false
            while(!quit){

                String strChoice = JOptionPane.showInputDialog(null, "Please select an option: \n"
                        + "\t1. Deposit sum to account\n"
                        + "\t2. Withdraw sum from the account\n"
                        + "\t3. Create an account\n"
                        + "\t4. View all accounts\n"
                        + "\t5. Delete a customer's account\n"
                        + "\t9. Quit Processing", BANK, JOptionPane.QUESTION_MESSAGE);

                try{
                    choice = Integer.parseInt(strChoice);
                }
                catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "Error. Enter a valid option. ", BANK,
                            JOptionPane.INFORMATION_MESSAGE);
                    continue;
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null, "Error. Enter a valid option. ", BANK,
                            JOptionPane.INFORMATION_MESSAGE);
                    continue;
                }

                switch(choice){

                    case 1: // Deposit

                        xnam = JOptionPane.showInputDialog(null, "Enter the Customer's Name: ", BANK,
                                JOptionPane.QUESTION_MESSAGE);

                        index = custArray[0].findIndex(custArray, xnam, count);

                        if(index != -1){
                            System.out.println();

                            String strDepositAmt = JOptionPane.showInputDialog(null,
                                    "Enter the deposit, e.g., 10000.00: ",
                                    BANK, JOptionPane.QUESTION_MESSAGE);

                            double depositAmt = Double.parseDouble(strDepositAmt);

                            double result = custArray[index].deposit(depositAmt);

                            if(result == -1){

                                // Cannot have negative amounts

                                System.err.println("Error: Deposit amount is invalid.");
                                System.err.println("Customer: " + this.name);
                                System.err.println("Requested: " + depositAmt);
                                JOptionPane.showMessageDialog(null, "Error: Deposit amount is invalid.\n"
                                        + "Customer: " + this.name + "\nRequested: " + depositAmt);

                            } else {
                                JOptionPane.showMessageDialog(null, xnam + " balance after deposit: " +
                                                fmt.format(custArray[index].getBalance()) + "\n"
                                                + xnam + " balance after interest is added: " +
                                                fmt.format(custArray[index].addInterest()),
                                        BANK, JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            // Record wasn't found alert the user.
                            System.out.println();
                            System.out.println(xnam + " was not found");
                            JOptionPane.showMessageDialog(null, xnam + " was not found",
                                    BANK, JOptionPane.INFORMATION_MESSAGE);
                        }

                        break;

                    case 2: // Withdraw

                        xnam = JOptionPane.showInputDialog(null, "Enter the Customer's Name: ", BANK,
                                JOptionPane.QUESTION_MESSAGE);

                        index = custArray[0].findIndex(custArray, xnam, count);

                        // If index was found
                        if(index != -1){
                            System.out.println();

                            String strWithdrawAmt = JOptionPane.showInputDialog(null,
                                    "Enter the withdrawal, e.g., 10.00: ",
                                    BANK, JOptionPane.QUESTION_MESSAGE);

                            double withdrawAmt = Double.parseDouble(strWithdrawAmt);

                            double result = custArray[index].withdraw(withdrawAmt, FEE);

                            if(result == -1)    // Error: negative amount
                            {
                                System.err.println("Error: Withdraw amount is invalid.");
                                System.err.println("Customer: " + this.name);
                                System.err.println("Requested: " + (withdrawAmt + FEE));
                                JOptionPane.showMessageDialog(null, "Error: Withdraw amount is invalid.\n"
                                        + "Customer: " + this.name + "\nRequested: " + withdrawAmt);

                            } else if (result == -2) {          // Error insufficent funds

                                System.err.println("Error: Insufficient funds");
                                System.err.println("Customer: " + this.name);
                                System.err.println("Requested: " + (withdrawAmt + FEE));
                                System.err.println("Available: " + this.balance);

                                JOptionPane.showMessageDialog(null, "Error: Insufficient funds\n"
                                        + "Customer: " + this.name + "\nRequested: " + (withdrawAmt + FEE) + "\nAvailable: " + this.balance);
                            } else {
                                // Build a string to show the user the withdrawal information.

                                String successfulWithdraw = (xnam + " balance after withdrawal: " +
                                        fmt.format(custArray[index].getBalance()) + "\n"
                                        + xnam + " balance after interest is added: " +
                                        fmt.format(custArray[index].addInterest()));

                                JOptionPane.showMessageDialog(null, successfulWithdraw, BANK, JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else {
                            // Record wasn't found, alert the user.
                            System.out.println();
                            System.out.println(xnam + " was not found");
                            JOptionPane.showMessageDialog(null, xnam + " was not found",
                                    BANK, JOptionPane.INFORMATION_MESSAGE);
                        }

                        break;

                    case 3: // Add an account

                        if(count < custArray.length){

                            name = JOptionPane.showInputDialog(null, "Enter the Customer's Name: ", BANK,
                                    JOptionPane.QUESTION_MESSAGE);

                            String strCustNum = JOptionPane.showInputDialog(null,
                                    "Enter Customer's Number, e.g., 11111: ",
                                    BANK, JOptionPane.QUESTION_MESSAGE);

                            custNumber = Long.parseLong(strCustNum);

                            String strBalance = JOptionPane.showInputDialog(null,
                                    "Enter Customer's Balance, e.g., 1000.00: ",
                                    BANK, JOptionPane.QUESTION_MESSAGE);

                            balance = Double.parseDouble(strBalance);

                            phone = JOptionPane.showInputDialog(null, "Enter Customer's phone number: ",
                                    BANK, JOptionPane.QUESTION_MESSAGE);

                            custArray[count] = new Customer();
                            custArray[count].addNewCustomer(custArray, count, name, custNumber, balance, phone);
                            count++;
                        } else {
                            JOptionPane.showMessageDialog(null, "The array if full. No new record added ",
                                    BANK, JOptionPane.INFORMATION_MESSAGE);
                        }

                        break;

                    case 4: // Display all records
                        OutPutText = "";

                        // Call nameSort() To alphabetize
                        custArray[0].nameSort(custArray, count);


                        // loop through Customer array and display records
                        for(int scan = 0; scan < count; scan++){
                            OutPutText = (OutPutText + custArray[scan].getCustomerName() + " " +
                                    custArray[scan].getCustomerNumber() + " " +
                                    fmt.format(custArray[scan].getBalance()) + " " +
                                    custArray[scan].getPhoneNumber() + "\n");
                        }

                        JOptionPane.showMessageDialog(null, OutPutText,	BANK, JOptionPane.INFORMATION_MESSAGE);

                        break;

                    case 5: // Delete customer

                        xnam = JOptionPane.showInputDialog(null, "Enter the Customer's Name: ", BANK,
                                JOptionPane.QUESTION_MESSAGE);

                        index = custArray[0].findIndex(custArray, xnam, count);

                        if(index != -1){
                            if(count >= 1 && count <= custArray.length){

                                custArray[0].DeleteCustomer(custArray, index, count);
                                count--;

                                JOptionPane.showMessageDialog(null, xnam + " is deleted. ",
                                        BANK, JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, xnam + " was not found ",
                                    BANK, JOptionPane.INFORMATION_MESSAGE);
                        }

                        break;

                    default:    // quit
                        quit = true;
                }
            }

            // call sorting method that alphabetizes records.
            custArray[0].nameSort(custArray, count);

            OutPutText = "\t\tSorted updated " + file + " records\n";

            for(int scan = 0; scan < count; scan++){
                OutPutText = (OutPutText + custArray[scan].getCustomerName() + " " +
                        custArray[scan].getCustomerNumber() + " " +
                        fmt.format(custArray[scan].getBalance()) + " " +
                        custArray[scan].getPhoneNumber() + "\n");
            }

            JOptionPane.showMessageDialog(null, OutPutText,	BANK, JOptionPane.INFORMATION_MESSAGE);

        } catch(FileNotFoundException exception) {
            System.err.println("The file " + file + " was not found.");
        } catch(IOException IOexception) {
            System.err.println(IOexception);
        }
    }

    /**
     * Main method. Calls runBankTest() where program is carried out.
     *
     * @param args parameter is ignored.
     */
    public static void main(String[] args) {

        BankDriver bankTest = new BankDriver();
        bankTest.runBankTest();
        System.exit(0);
    }
}