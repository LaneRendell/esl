/******************************************************************************
 * Program Name: BankDriver.java
 *
 *
 *
 *
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 */
public class BankDriver {

    /**
     * Displays numbers in currency format.
     */
    private NumberFormat fmt = NumberFormat.getCurrencyInstance();

    /**
     * Fee with
     */
    final double FEE = 1.50;
    final int MAX = 30;
    final String BANK = "Bank System";

    Customer[] custArray = new Customer[MAX];
    int count = 0;
    int index;

    StringTokenizer tokenizer;
    String line;
    String file = "customer.txt";

    String name;
    long custNumber;
    double balance;
    String phone;

    boolean quit = false;
    int choice;

    String OutPutText = "";
    String xnam;

    public void runBankTest(){
        System.out.println("Welcome to the Bank");

        try{
            FileReader fr = new FileReader(file);
            BufferedReader inFile = new BufferedReader(fr);

            line = inFile.readLine();

            while(line != null && count < MAX){
                tokenizer = new StringTokenizer(line);

                name = tokenizer.nextToken();
                try{
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

            inFile.close();

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

                    case 1:

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

                            if(result != -1){

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

                    case 2:

                        xnam = JOptionPane.showInputDialog(null, "Enter the Customer's Name: ", BANK,
                                JOptionPane.QUESTION_MESSAGE);

                        index = custArray[0].findIndex(custArray, xnam, count);

                        if(index != -1){
                            System.out.println();

                            String strWithdrawAmt = JOptionPane.showInputDialog(null,
                                    "Enter the withdrawal, e.g., 10.00: ",
                                    BANK, JOptionPane.QUESTION_MESSAGE);

                            double withdrawAmt = Double.parseDouble(strWithdrawAmt);

                            double result = custArray[index].withdraw(withdrawAmt, FEE);

                            String successfulWithdraw = (xnam + " balance after withdrawal: " +
                                    fmt.format(custArray[index].getBalance()) + "\n"
                                    + xnam + " balance after interest is added: " +
                                    fmt.format(custArray[index].addInterest()));

                            // If no errors thrown by withdraw method
                            if(result != -1){
                                JOptionPane.showMessageDialog(null, successfulWithdraw, BANK, JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            System.out.println();
                            System.out.println(xnam + " was not found");
                            JOptionPane.showMessageDialog(null, xnam + " was not found",
                                    BANK, JOptionPane.INFORMATION_MESSAGE);
                        }

                        break;

                    case 3:

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

                    case 4:
                        OutPutText = "";

                        for(int scan = 0; scan < count; scan++){
                            OutPutText = (OutPutText + custArray[scan].getCustomerName() + " " +
                                    custArray[scan].getCustomerNumber() + " " +
                                    fmt.format(custArray[scan].getBalance()) + " " +
                                    custArray[scan].getPhoneNumber() + "\n");
                        }

                        JOptionPane.showMessageDialog(null, OutPutText,	BANK, JOptionPane.INFORMATION_MESSAGE);

                        break;

                    case 5:

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

                    default:
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
        } catch(IOException exception) {
            System.err.println(exception);
        }
    }

    /**
     * Main method. Calls runBankTest() where program is carried out.
     *
     * @param args parameter is ignored.
     * @return void.
     */
    public static void main(String[] args) {

        BankDriver bankTest = new BankDriver();
        bankTest.runBankTest();
        System.exit(0);
    }
}