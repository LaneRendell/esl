CSC 225 ADVANCED JAVA PROGRAMMING   Project 1
Name___________________________________________

Project Grade (100%)		 
 Due Date :  __________________ 

Documentation (30):

Cover Page (2)
Table of Content (2)
Abstract (5) 
Design (UML diagram and Javadocs) (11)
Source Code  (5)
Sample Input  (5)

	
Sample Output(10)
	
Correct Logic(40):
Clean compile	
Structure(20):
 white spacing (5)
 proper indentation(5)
 meaningful names(5)
 logical flow(5)


	
          Total points:	


 
AT THE BEGINNING OF LAB  (you must demonstrate to me  that the programs are working)




Comments:







 

CSC 225 Advanced Java Programming 
Project #1

•	Purpose: Process an array of objects


•	Assignment:
The ES&L Bank can handle up to 30 customers who have saving accounts.
Design and implement a program that manages the accounts.
Each customer has a name, customer number, phone number, and account balance.
Allow each customer to make deposits and withdrawals. Produce appropriate error message for invalid transactions. 

To solve the problem:
1.	Create a Customer class: 
•	Data members include the customer last name, customer number, balance, and phone number.  

•	Create two constructors, one which accepts last name, customer number, balance, and phone number. Also, create a default constructor.

•	 In the customer class create several methods. 

•	withdraw() method

1. Add a 1.50 fee for the transaction.

2. If the user tries to withdraw a negative amount, display the following   
    error message:

                             “Error: Withdraw amount is invalid.
                               Customer: xxx     
                               Requested: yyyy”
                           
                                where xxx equals the customer’s name
                                           yyy equals the amount
                                      
         3.  If the user tries to withdraw more money than he/she has in the 
              savings account (e.g., balance), refuse the transaction with the 
             message:

                            “Error: Insufficient funds
                             Customer: xxx 
                             Requested: yyyy
                            Available: zzz”

                             where xxx equals the customer’s name
                                        yyy equals the amount
                                       zzz equals the balance in the account

                      4. For a valid transaction, subtract the amount from the balance in the 
                               account.

•	deposit () method
 
1. If the user tries to deposit a negative amount, display the following   
    error message:

                             “Error: Deposit amount is invalid.
                               Customer: xxx     
                               Requested: yyyy”
                           
                                where xxx equals the customer’s name
                     where yyy equals the amount

             2. For a valid transaction, add the amount to the balance.  

 
•	Include a method called addInterest to add 4.5 percent interest 
     to all customer’s accounts whenever this method is invoked.

•	Include a method called addNewCustomer to add a new customer to the customer array. 

•	Include a method called nameSort to sort the customer array in alphabetical order by name. Use the select sort.

•	Include a method called findIndex to locate a customer in the customer array based on a given name. Return the index of the customer found.
If no customer is found, return a -1.
 
•	Include a method called deleteCustomer to delete a customer in the customer array based on a given name.  

•	Include a String toString () method.

•	Include accessors and mutator methods for the data members.
 

2.	Create BankDriver class(this class has a main method in it):
•	 Create an array of thirty customers with data members customer name, number, balance and phone number.  

     The bank class should have method called runBankTest() method to control  
     the invocation of all the required methods.

•	First read all records from the input file customer.txt. Place the records in the customer array. Keep a count of the records placed in the array. Display appropriate messages such as: 

         a. NumberFormatException: “ Error in input. Line ignored.”

        b.  FileNotFoundException:     “The file xxx was not found.”
                     where xxx is customer.txt
c.	IOException 

•	Once all records are read and tabled, sort the array and display the records on the screen. Close the input file.

•	Prompt the users to perform tasks
           1. Deposit sum to the account
           2. Withdraw sum from the account
           3. Create an account
           4. View all accounts
           5. Delete a customer’s account
           9. Quit Processing


•	Option 1. Find the index of the customer in the array. Call the  customer’s  findIndex()  method . The method will return the index of the customer in the list, or return -1 if the customer is not in the array.  

     Invoke the Customer’s deposit() method to deposit the amount in the 
     savings account. Call addInterest() method to add 4.5 percent interest to 
     the customer’s balance.

•	Option 2: Find the index of the customer in the array. Call the customer’s  findIndex()  method . The method will return the index of the customer in the list, or return -1 if the customer is not in the array.

     Invoke the Customer’s withdraw() method  to withdraw  the amount from
     the savings account. Call addInterest() method to add 4.5 percent interest 
     to the customer’s balance.

•	Option 3: If the array is not full, invoke the Customer’s addNewCustomer method to add a new customer to the end of the array. 

•	Option 4: Sort all records in the array Call the customer’s nameSort() method.  Display the records on the screen. 

•	Option 5: Delete a customer’s record. Prompt the user to enter the name of the customer to delete. Validate that the customer is valid. Find the index of the customer in the array. Call the customer’s  findIndex()  method . The method will return the index of the customer in the list, or return -1 if the customer is not in the array.   
                  If  a valid customer, call the Customer’s deleteCustomer( ) method to 
                  delete the customer.

•	Option 9: At end of processing, sort the customer’s array and display the records on the screen. Call the customer’s nameSort() method     

Test your program with the following data(customer.txt):
DansonTed  666666 102.56 5852226666
SmithJoan    777777   40.00 5853337777
DimeGeo  888888     759.32 5854448888
 
Add more data to your test. Create a test log with expected output and print screens.

Create a document using the javadoc tool.


Sample Input/Output
Option 1 Deposit sum to account:

 


 

 


 
` 
Options 1 and 2:  Deposit sum to account and Withdraw sum from the account

Options 1: Deposit sum to account


 

 

 

 

 

Option 2: Withdraw sum from the account
 

 

 

 

 
Option 2: Insufficient funds

 

 

 

 

 
Option 3: Create an account

 

 

 

 


 



Option 4: View all accounts

 


Replace with new one

 













 
Option 5: Delete an account

 

 

 


 



 


End-of-File Processing (sort the array and print the Customers in the array:
  
End-of-File Processing:


Hand In:
In a closeable large envelop (no folders) with your names clearly stated outside:
•	Please note you will compile and run your program in class.
•	A hard copy of the source code programs stapled together. 
•	A hard Copy of the test cases stapled together with your name marked on top 
•	You will be required to show me that the programs are working





M:\Courses\ICT\CSC Classes\CSC 225 Advanced Java Programming\ButlerFordeFolder\Projects\CSC 225 Project1_Summer2014.doc
