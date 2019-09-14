import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class hashTable{
   static int[] tablesizeTest = new int[5];
   static int tablesize = 0;
   static String file = null;
   static String structure = null;
   static String answer = null;
   static int nKeys = 0;
   static int nKeysTest = 0;
   static String isTest = null;
   public static ArrayList<String>  allData = new ArrayList<String>(); 
   
   public static void ask(){
   Scanner scanner = new Scanner(System.in);
   System.out.println("Do you want to test?(yes/no)");
   isTest = scanner.nextLine();
   if(isTest.equals("yes")){
   System.out.println("Enter File Name(cleaned_data.csv) ");
   file = scanner.nextLine();
   System.out.println("Enter 5 Table Sizes ");
   tablesizeTest[0] = scanner.nextInt();
   tablesizeTest[1] = scanner.nextInt();
   tablesizeTest[2] = scanner.nextInt();
   tablesizeTest[3] = scanner.nextInt();
   tablesizeTest[4] = scanner.nextInt();
   System.out.println("How many keys Do you want to test?");
   nKeysTest = scanner.nextInt();
   }else{
   System.out.println("Enter Hash Table Structure(linear,quadratic, chaining) ");
   structure = scanner.nextLine();
   System.out.println("Enter File Name(cleaned_data.csv) ");
   file = scanner.nextLine();
   System.out.println("Enter Table Size ");
   tablesize = scanner.nextInt();
   if(!isPrime(tablesize)){
   tablesize = getNextPrime(tablesize);
   }
   System.out.println("Do you want to search keys?(yes/no)");
   answer = scanner.nextLine();
   answer = scanner.nextLine();
   if(answer.equals("yes")){
   System.out.println("How many keys Do you want to search?");
   nKeys = scanner.nextInt();
   }
   }
   scanner.close();
   }
   
    public static boolean isPrime(int num) {
           // Eliminate the need to check versus even numbers
	        if (num%2== 0)
	            return false;
	        // Check against all odd numbers
           for (int i=3;i*i<=num;i+=2) {
               if (num%i==0)
               return false;
           }
           return true;
           }
   
    public static int getNextPrime(int minNumberToCheck) {
	        for (int i = minNumberToCheck; true; i++) {
               if (isPrime(i))
	                return i;
	        }
	    }
      
   public static void createArrayList(){
    try{
            Scanner csvFile=new Scanner(new File(file));
            
            csvFile.nextLine();            
            while (csvFile.hasNextLine()){
                allData.add(csvFile.nextLine().split(",")[0]);
                
        }
        csvFile.close();}
        catch(Exception e){
            System.out.println(e.getMessage());
        }

   Collections.shuffle(allData);
   }
   
   public static void main(String[] args){
   ask();
   createArrayList();
    if(isTest.equals("yes")){
     for(int i=0;i<5;i++){
     linearProbing lpTest = new linearProbing();
     lpTest.insert(file ,tablesizeTest[i]);
     quadraticProbing qpTest=new quadraticProbing();   
     qpTest.insert(file ,tablesizeTest[i]);
     chainingProbing cpTest=new chainingProbing(file ,tablesizeTest[i]);
     lpTest.searchKeys(nKeysTest,allData);
     qpTest.searchKeys(nKeysTest,allData);
     cpTest.searchKeys(nKeysTest,allData);
     }
   }else{
   if(structure.equals("linear")){   
   linearProbing lp = new linearProbing();
   lp.insert(file ,tablesize);
   if(answer.equals("yes")){
   
   lp.searchKeys(nKeys,allData);  
   }
   }
   
   if(structure.equals("quadratic")){   
   quadraticProbing qp=new quadraticProbing();   
   qp.insert(file ,tablesize);
   if(answer.equals("yes")){
   qp.searchKeys(nKeys,allData);   
   }
   }

   if(structure.equals("chaining")){   
   chainingProbing cp=new chainingProbing(file ,tablesize);
   if(answer.equals("yes")){
   cp.searchKeys(nKeys,allData);  
   }
   }
   }
  
   
   }}