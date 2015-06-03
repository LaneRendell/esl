
/**
 * Created by Charles on 6/2/2015.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.*;
import javax.swing.JOptionPane;
import java.text.NumberFormat;

public class BankDriver {
    //********************************************************************
//  BankTestDriver.java       Christine Forde
//  Driver to exercise the use of multiple Customer objects.
//********************************************************************


        //----------------------------------------------------------------------
        // Reads data from a Customer input file (customer.txt). Put the records
        // in an array. Display the unsorted records to the user. Present a menu
        // of choices to the user:1) deposit sum, 2) withdraw amount,3) create a
        // new customer, 4) view all customers, 5) delete a customer 9) Quit
        // At end of file, sort the array in alphabetical order by name and
        // display all customer's accounts,
        //-----------------------------------------------------------------

        private NumberFormat fmt = NumberFormat.getCurrencyInstance();
        final int MAX = 30;

        Customer [] custsArray = new Customer[MAX];
        int count = 0;  //keeps track of the number of objects placed in
        // the custArray array

        int index;      // points to the current object in the array that
        // is being accessed

        StringTokenizer tokenizer;
        String line;
        String file = "customer.txt";

        String name;
        String custNumber;
        double balance;
        long phone;


        boolean quit = false;
        int choice;

        String OutPutText = "";
        String xnam;



        /**
         *The runBankTest method runs the BankTest system.
         *
         * @param args command line arguments - ignored
         * It reads the input file and store the records in custsArray
         * The method contains a loop that prompts the users with a
         * JOptionPane.showInput dialog window for choices.
         */
//--------------------------------------------------------------------

        public void runBankTest()
        {
            System.out.println("Welcome to the ES&L Bank");
            System.out.println(System.getProperty("user.dir"));
            try
            {
                FileReader fr = new FileReader (file);
                BufferedReader inFile = new BufferedReader (fr);

                line = inFile.readLine();

                while (line != null && count < MAX)
                {
                    tokenizer = new StringTokenizer (line);
                    name       = tokenizer.nextToken();


                    try
                    {
                        custNumber = tokenizer.nextToken();

                        balance    = Double.parseDouble(tokenizer.nextToken());

                        phone      = Long.parseLong(tokenizer.nextToken());

                        custsArray [count++] = new Customer(name, custNumber,
                                balance, phone);



                    }// end inner try block



                    catch (NumberFormatException exception)
                    {
                        System.out.println( "Error in input. Line ignored: ");
                        System.out.println (line);

                    } // end catch block

                    line = inFile.readLine(); // read another  record

                } //end while block

                inFile.close();

                System.out.println(" \t\tUnsorted customer.txt records");
                System.out.println("");

                for (int scan = 0; scan < count; scan++)
                    System.out.println (custsArray[scan]);

//--------------------------------------------------------------
// Get choices from the user. Loop until the users enteres a 9


                while (!quit)
                {
                    String strChoice =
                            JOptionPane.showInputDialog(null,"Please select an option: \n"
                                    + "\t1. Deposit sum to account\n"
                                    + "\t2. Withdraw sum from account\n"
                                    + "\t3. Create account\n"
                                    + "\t4. View all accounts\n"
                                    + "\t5. Delete an account\n"
                                    + "\t9. Quit", "ES&L Bank", JOptionPane.QUESTION_MESSAGE);

                    choice = Integer.parseInt(strChoice);
//-----------------------------------------------------------------------
                    switch (choice)
                    {
                        case 1://deposit
                            xnam  =
                                    JOptionPane.showInputDialog
                                            (null,"Enter the Customer's Name: ",
                                                    "ES&L Bank System",JOptionPane.QUESTION_MESSAGE);

                            index = custsArray[0].findIndex(custsArray, xnam, count);

                            if (index != -1)
                            {
                                System.out.println("");

                                String strDepositAmt =
                                        JOptionPane.showInputDialog
                                                (null,"Enter the deposit, e.g., 10000.00: ",
                                                        "ES&L Bank System",JOptionPane.QUESTION_MESSAGE);

                                double depositAmt = Double.parseDouble(strDepositAmt);

                                custsArray[index].deposit(depositAmt);

                                JOptionPane.showMessageDialog
                                        (null, xnam + " balance after deposit: "        +
                                                        fmt.format(custsArray[index].getBalance() )
                                                        + "\n"  +
                                                        xnam + " balance after interest is added: " +
                                                        fmt.format(custsArray[index].addInterest() ),
                                                "ES&L Bank System",
                                                JOptionPane.INFORMATION_MESSAGE);


                            } // end if stmt

                            else
                            {
                                System.out.println("");
                                System.out.println( xnam + " was not found");
                            }  //end else stmt

                            break; // end choice equals 1


//---------------------------------------------------------------
                        case 2:   //withdraw

                            //viewCustomers();
                            xnam  =
                                    JOptionPane.showInputDialog(null,"Enter the Customer's Name: ",
                                            "ES&L Bank System",JOptionPane.QUESTION_MESSAGE);

                            index = custsArray[0].findIndex(custsArray, xnam, count);

                            if (index != -1)
                            {
                                System.out.println("");

                                String strWithdrawAmt =
                                        JOptionPane.showInputDialog
                                                (null,"Enter the withdrawal, e.g., 10.00: ",
                                                        "ES&L Bank System",JOptionPane.QUESTION_MESSAGE);

                                double withdrawAmt = Double.parseDouble(strWithdrawAmt);

                                JOptionPane.showMessageDialog
                                        (null, xnam + " balance after withdrawal: "       +
                                                        fmt.format(custsArray[index].withdraw(withdrawAmt) )
                                                        + "\n"  +

                                                        xnam + " balance after interest is added: " +
                                                        fmt.format(custsArray[index].addInterest() ),
                                                "ES&L Bank System",
                                                JOptionPane.INFORMATION_MESSAGE);

                            } // end if stmt

                            else
                            {
                                System.out.println("");
                                System.out.println( xnam + " was not found");
                            }  //end else stmt

                            break; //end choice equals 2

//-------------------------------------------------------------

                        case 3:    //create an account

                            if (count < custsArray.length)
                            {
                                name = JOptionPane.showInputDialog
                                        (null,"Enter Customer's name: ",
                                                "ES&L Bank System",JOptionPane.QUESTION_MESSAGE);

                                String strCustNum = JOptionPane.showInputDialog
                                        (null,"Enter Customer's Number, e.g., 11111: ",
                                                "ES&L Bank System",JOptionPane.QUESTION_MESSAGE);

                                custNumber = (strCustNum);

                                String strBalance = JOptionPane.showInputDialog
                                        (null,"Enter Customer's Balance, e.g., 1000.00: ",
                                                "ES&L Bank System",JOptionPane.QUESTION_MESSAGE);

                                balance = Double.parseDouble(strBalance);

                                phone = Long.parseLong(JOptionPane.showInputDialog
                                        (null,"Enter Customer's phone number: ",
                                                "ES&L Bank System",JOptionPane.QUESTION_MESSAGE));


                                //--------------------------------------------------------------------
                                custsArray[count]= new Customer();
                                custsArray[count].addNewCustomer(custsArray, count,
                                        name, custNumber, balance,
                                        phone);
                                count++;
                            }

                            else
                                JOptionPane.showMessageDialog(null,
                                        "The array is full. No new record added ",
                                        "ES&L Bank System",
                                        JOptionPane.INFORMATION_MESSAGE);

                            break; //end choice equals 3


//------------------------------------------------------------------
                        case 4:   //view all accounts

                            //sort for output by names
                            custsArray[0].nameSort(custsArray,count);

                            OutPutText = "";

                            for (int scan = 0; scan < count; scan++)
                            {
                                OutPutText =
                                        (OutPutText + custsArray[scan].getName() + " " +
                                                custsArray[scan].getIdNumber() + " "   +
                                                fmt.format(custsArray[scan].getBalance()) + " "  +
                                                custsArray[scan].getPhoneNumber()+ "\n");

                            }

                            JOptionPane.showMessageDialog(null,OutPutText,
                                    "ES&L Bank System",
                                    JOptionPane.INFORMATION_MESSAGE);

                            break;  // end choice 4
                        //-----------------------------------------------------------------
                        case 5: //Delete a customer

                            xnam  =
                                    JOptionPane.showInputDialog(null,"Enter the Customer's Name: ",
                                            "ES&L Bank System",JOptionPane.QUESTION_MESSAGE);

                            index = custsArray[0].findIndex(custsArray, xnam, count);

                            if (index != -1)
                            {
                                if (count >= 1 && count <= custsArray.length)
                                {
                                    custsArray[index] = custsArray[count-1]; //pack the hole
                                    count--; //decrement count now that we have one less
                                    //element

                                    JOptionPane.showMessageDialog
                                            (null, xnam + " is deleted. ",
                                                    "ES&L Bank System",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                } // end nested if statement

                            } // end if stmt

                            else
                            {
                                JOptionPane.showMessageDialog
                                        (null, xnam + " was not found ",
                                                "ES&L Bank System",
                                                JOptionPane.INFORMATION_MESSAGE);

                            }  //end else stmt

                            break; // end choice 5

                        //-----------------------------------------------------------------

                        default: quit = true;  //set done to true to exit the system

//--------------------------------------------------------------------

                    }// end switch statement

                }// end while loop  for menu





//----------------------------------------------------------------------

                custsArray[0].nameSort(custsArray,count);//sort for output by names

                System.out.println("");
                System.out.println(" \t\tSorted updated customer.txt records");

                for (int scan = 0; scan < count; scan++)
                    System.out.println (custsArray[scan]);


            }//end try block

//----------------------------------------------------------------

            catch (FileNotFoundException exception)
            {
                System.out.println ("The file " + file + " was not found");
            }



            catch (IOException exception)
            {
                System.out.println (exception);
            }


//----------------------------------------------------------



        } //end method runBankTest()

//------------------------------------------------------------


        public static void main (String[] args)
        {
            BankDriver bankTest = new BankDriver();
            bankTest.runBankTest();
            System.exit(0);
        } // end main method

//---------------------------------------------------------------

    }// end class BankTestDriver
