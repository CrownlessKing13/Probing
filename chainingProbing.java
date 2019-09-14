
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class chainingProbing {
    public static Node[] llArr;
    public static int tableSize;
    public static int total=0;
    public static int countSearch=0;
    public static int countInsert=0;
    public static String fileN;
    
    public chainingProbing(String file,int tSize){
        
        tableSize=tSize;
        fileN=file;
        llArr = new Node[tableSize];
        try{
            Scanner csvFile=new Scanner(new File(fileN));
            
            csvFile.nextLine();
            
            
            while (csvFile.hasNextLine()){
                
                String[] temp=csvFile.nextLine().split(",");
                
                
                long uniVal=hash(temp[0]);
                
                insertChaining(uniVal,temp[0]+","+temp[1]+","+temp[3]);
                total++;
                }
                         csvFile.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Sucessfully stored in table with chainingHashing structure");
        double factor=(double) total/tableSize;
        System.out.printf("The load Factor is: "+"%.2f", factor);
        System.out.println("\nThe number of probing done while inserting is: "+countInsert);
       
       
        
    }
      
    
    
    public static void searchKeys(int nKeys,ArrayList<String>  allData){
        int max=0;
        int totalCountSearch = 0;
        for (int i =0;i<nKeys;i++){
           // System.out.println("Searching key: "+allData.get(i));
            countSearch = 0;
            chainingSearch(allData.get(i));
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
  
    public static long hash(String key){
        long hashnum=0;
        for (int i=0;i<key.length();i++){
            long ascii=(int)key.charAt(i);
            hashnum=(37*hashnum)+ascii;
        }
        return hashnum*-1;
    }
    
    
    public static void insertChaining(long uniNum,String data){
        int temp=Integer.parseInt(uniNum%tableSize+"");
       llArr[temp]= insert(llArr[temp],data);
           
            
                     
    }
    
    public static void chainingSearch(String key){
        long num=hash(key);
        int hashnum=Integer.parseInt(num%tableSize+"");
        String ans=search(llArr[hashnum], key);
        if(ans==null){
      //  System.out.println("Date/Time not found");
        }else{
       // System.out.println("Found "+ans);
        }
        
        
       
    }
    
    
    public static Node insert(Node head,String key){
                 
     Node new_node = new Node(key); 
  
    
    if ( head== null) 
    { 
        head = new Node(key); 
        return head; 
    } 
    new_node.next = null; 
    countInsert++;
    Node last = head;  
    while (last.next != null&&!last.data.equals(key)){
         countInsert++; 
         last = last.next; }
       if(!last.data.equals(key)){
         last.next = new_node;     
       }else{
       System.out.println("This data is already stored "+key);
       }
       
    return head; 
                }
    public static String search(Node n,String key){
                  
           Node current = n;    
             while (current != null) 
        { 
            countSearch++;
            if (key.equals(current.data.split(",")[0])){ 
                return current.data;}    
            current = current.next; 
        } 
        return null;     
                
                }
                
                
}
