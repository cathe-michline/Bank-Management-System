import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;  
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.*;

class MyException extends Exception
{
MyException(String message)
{
super(message);
}
}

interface Account
{
void CreateAcc();
void Deposit();
void Withdraw();
}

class DemoLogger {
private final static Logger LOGGER =
Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
public void makeSomeLog()
{
LOGGER.log(Level.INFO, "\n\tAccount Successfully Created!");
}
}

class BankUtilities
{ int i=0;
int acctype,year=1,accnum;
float intr;

String name="";
String loc="";//branch name
String ssn="";// phone number
Scanner in = new Scanner(System.in);
Random rnd = new Random();//to generate account number randomly
double temp=0.0,bal=0.0;
}

class Bank extends BankUtilities implements Account
{

      void getInfo() {
      try{

    System.out.print("\n\tEnter your Name: ");
    name=in.nextLine();
    System.out.print("\n\tEnter Phone Number: ");
    ssn=in.nextLine();
    System.out.print("\n\tEnter Branch Name: ");
    loc=in.nextLine();
    System.out.println("\n\tACCOUNT TYPE:\n\t1.Savings (7% intr)\n\t2.Current (5% intr) ");
    System.out.print("\n\tYOUR CHOICE :");
    acctype=in.nextInt();
      switch(acctype)
      {
      case 1:
      System.out.print("\n\tEnter the initial amount of deposit:");
      temp=in.nextFloat();
      Deposit(temp);
      System.out.print("\n\tEnter No. of years:");
      year=in.nextInt();
      break;
      case 2:
      System.out.println("\n\tINITIAL DEPOSIT:");
      temp=in.nextFloat();
      Deposit(temp);
      break;
      default: System.out.println("\n\tINVALID!");
      } //Switch
      }
      catch(Exception e)
        {
        System.out.println("Inbuilt Exception --> "+e);
        System.exit(0);
        }
      }//getInfo



      public void CreateAcc()
      {
        try{
      getInfo();
      accnum=rnd.nextInt(1000)+1;
      System.out.println("\n--Hello "+name+" your account number is :" +accnum+".--\n");}
      catch(Exception e)
        {
        System.out.println("Inbuilt Exception --> "+e);
        System.exit(0);
        }

      }

        void Deposit(double temp) // Initial Deposit ...method overriding
        {try{
        if(temp>=100)
        {
        bal+=temp;
        System.out.println("\n\tSUCCESSFULLY CREDITED");
        }
        return;}
        catch(Exception e)
        {
        System.out.println("Inbuilt Exception --> "+e);
        System.exit(0);
        }
        }

        public void Deposit() // regular deposit..method overriding
        {try{
        System.out.println("\n\tDEPOSIT AMOUNT :");
        temp=in.nextFloat();
        if(temp>0)
        {
        bal+=temp;
        System.out.println("\n\tSUCCESSFULLY CREDITED");}
        }
        catch(Exception e)
        {
        System.out.println("Inbuilt Exception --> "+e);
        System.exit(0);
        }}
        


      public void Withdraw()// regular deposit
      {
      System.out.println("\n\tAMOUNT TO WITHDRAW:");
      temp=in.nextFloat();
      if(temp<=0)
      {
      System.out.println("\n\tINVALID!");
      System.exit(0);
      }
      if(temp<bal)
      {
      bal-=temp;
      System.out.println("\n\tSUCCESSFULLY DEBITED");
      }
      }


      void Interest()
      {
      if(acctype==1)
      {
      bal+=year*bal*0.07;
      System.out.println("\n\t7% interest added");
      }
      else if(acctype==2)
      {
      bal+=year*bal*0.03;
      System.out.println("\n\t3% interest added");
      }
      }


      void CheckBal()
      {
      System.out.println("\n\tBalance:"+bal);
      }

 
}

class Main
{
public static void main(String arg[])
{
Scanner in = new Scanner(System.in);
Bank b = new Bank();
System.out.println("\n\n--------WELCOME TO ONLINE BANKING SYSTEM---------");

      while(true){

        System.out.println("\n\t--------MENU-------");
        System.out.print("\n\t1. CREATE ACCOUNT\n\t2. BALANCE\n\t3. DEPOSIT\n\t4.WITHDRAW\n\t5. INTEREST\n\t6. EXIT\n\n\tYOUR CHOICE :");
        int ch = in.nextInt();
        try{
        switch(ch){
        case 1:
        b.CreateAcc();

        DemoLogger obj = new DemoLogger();
        obj.makeSomeLog();
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.log(Level.INFO, "This is a log message");

                      try {  
                    
                    FileWriter out = new FileWriter("sample.txt"); 
                    out.write("\nName : "+b.name);
                    out.write("\nPhone Number :"+b.ssn);
                    out.write("\nBranch  : "+b.loc);
                    out.write("\nType of account: "+b.acctype);
                    out.write("\ndeposit : "+b.temp);
                    out.write("\nTotal Balance : "+b.bal);
                    out.close();
                    System.out.println("\n\tSuccessfully wrote to the file.");
                  

                    System.out.println("\n\tReading From the file.");
                    File file = new File("sample.txt");
                    Scanner input = new Scanner(file);
                    String content = "";    
                    while (input.hasNextLine()) {
                    content = input.nextLine();
                    System.out.println("\n\t"+content); 
                    }
                  
                  // Close the file
                  input.close();
                  }
          
                  catch (IOException e)
                  {
                    System.out.println("An error occurred.");
                  }
      break;
      case 2:
      b.CheckBal();
      break;
      case 3:
      b.Deposit();
      break;
      case 4:
      b.Withdraw();
      break;
      case 5:
      b.Interest();
      break;
      case 6:
      System.exit(0);
      break;
      default: 
      System.out.println("Invalid Option");
      }
        }
      catch(Exception e)
      {
        System.out.println("Inbuilt Exception --> "+e);
        System.exit(0);
      }
    }

  }
}
