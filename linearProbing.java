import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class linearProbing{

   public static String[] table;
   public static int tableSize;
   public boolean flag = true;
   public static int countSearch=0;
   public static int countInsert=0;
   public static int total=0;
       
    public long probe(long uninum,String key){
        int hashnum=Integer.parseInt(uninum%tableSize+"");
        
        if (table[hashnum] == null){
            return hashnum;
        }
        else{
            int x = 0;
            while(true){
            if(x == tableSize){break;}
            hashnum = (hashnum+1)%tableSize;
            if (table[hashnum] == null){
            return hashnum;
            }
            if (table[hashnum].split(",")[0].equals(key)){
            return -2;
            }
            countInsert++;
            x++;
            }
            
            }
            return -1;
    }
    
     public static long hash(String key){
        long hashnum=0;
        for (int i=0;i<key.length();i++){
            long ascii=(int)key.charAt(i);
            hashnum=(37*hashnum)+ascii;
        }
        return hashnum*-1;
    }
      
   public void insert(String file,int Size){
        table=new String[Size];
        tableSize=Size;
        try{
            Scanner csvFile=new Scanner(new File(file));
            
            csvFile.nextLine();
            csvFile.useDelimiter("\n");
            while (csvFile.hasNextLine()){
                
                String[] temp=csvFile.nextLine().split(",");
                long uniNum=hash(temp[0]);
                int index=Integer.parseInt(probe(uniNum,temp[0])+"");
                if (index == -1){System.out.println("Table size is not enough");System.exit(0);}
                if (index == -2){System.out.println("This data is already stored");}else{
                table[index]=temp[0]+","+temp[1]+","+temp[3];}
                total++;
    
            }
        System.out.println("Sucessfully stored in table with LinearHashing structure");
        double factor=(double) total/tableSize;
        System.out.printf("The load Factor is: "+"%.2f", factor);
        System.out.println("\nThe number of probing done while inserting is: "+countInsert);
            csvFile.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
    }
   
   public static String search(String key){
    
    int start=Integer.parseInt(hash(key)%tableSize+"");
   if(table[start]==null){
     
    return "Date/Time is not found";
    }
    if(table[start].split(",")[0].equals(key)){
         
         return table[start];}
         
   while(true){
     start = (start+1)%tableSize;
     countSearch++;
    
     if(countSearch>start){return "Date/Time is not found";}
     if(table[start]==null){
    return "Date/Time is not found";
    }
     if(table[start].split(",")[0].equals(key)){
         return table[start];
   
   }
   
   }}
 
 
 public static void searchKeys(int nKeys,ArrayList<String>  allData){
        int max=0;
        int totalCountSearch = 0;
        for (int i =0;i<nKeys;i++){
          //  System.out.println("Searching key: "+allData.get(i));
            countSearch = 0;
           // System.out.println(search(allData.get(i)));
            search(allData.get(i));
            if(max<countSearch){
            max = countSearch;
            }
            totalCountSearch+=countSearch;            
     
        }
        System.out.println("Total number of probes for searching:"+totalCountSearch);
        double ave=(double)totalCountSearch/nKeys;
        System.out.println("The average number of probes: "+ave);
        System.out.println("The maximum Probe is: "+ max);
    }




}

         


