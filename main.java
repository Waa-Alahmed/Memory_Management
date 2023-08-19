import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class main {
     static Partition[] partitionArray;
     static int numberOfPartitions;

    public static void main(String[] args) {
    	
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of partitions (M):");
        numberOfPartitions = input.nextInt();
        partitionArray = new Partition[numberOfPartitions];

        for (int i = 0; i < numberOfPartitions; i++) {
            System.out.println("Enter the size of partition " + (i + 1) + " in KB:");
            int size = input.nextInt();
            
            if (i == 0) {
                partitionArray[i] = new Partition("free", size, 0, size , "NULL", -1);
            } else {
                partitionArray[i] = new Partition("free", size, partitionArray[i - 1].endingAddress, partitionArray[i - 1].endingAddress + size, "NULL", -1);
            }
        }

        System.out.println("Enter the allocation strategy (First-fit(F) , Best-fit(B) , Worst-fit(W) )");
        char allocationStrategy = input.next().charAt(0);
        printMemoryState();

        int ans = 0;
        do {
            System.out.println("MENU:");
            System.out.println("1. Allocate a block of memory");
            System.out.println("2. Deallocate a block of memory:");
            System.out.println("3. Report detailed information about memory");
            System.out.println("4. Exit");
            ans = input.nextInt();

            if (ans == 1) {
                System.out.println("Enter the process ID and size (e.g P1 40)");
                String processId = input.next();
                int size = input.nextInt();

                int partitionIndex=-1;
                switch (allocationStrategy) {
                    case 'F','f':
                        partitionIndex = findFirstFreePartition(processId, size);
                        break;
                    case 'B','b':
                    	partitionIndex = findBestFreePartition(processId, size);
                    	break;
                    case 'W','w':
                    	partitionIndex = findWorstFreePartition(processId, size);
                    	break;
                }
                if (partitionIndex != -1) {
                	printMemoryState();
                	System.out.println("------------"+"Memory block allocation successful"+"------------");
                }else
                	System.out.println("------------"+"Memory block allocation failed."+"------------");
            }
            else if(ans==2) {
            	System.out.println("Enter the process ID (e.g P1)");
                String processId = input.next();
                deallocating(processId);
                printMemoryState();
            }
            else if(ans==3) {
                display();            	
            }
        } while (ans != 4);
        input.close();
    }

    static void printMemoryState() {
        System.out.print("[");
        for (int i = 0; i < numberOfPartitions; i++) {
            if (partitionArray[i].processID.equals("NULL")) {
                System.out.print("H");
            } else {
                System.out.printf(partitionArray[i].processID);
            }
            if (i < numberOfPartitions - 1) {
                System.out.print(" | ");
            }
        }
        System.out.println("]");
    }
    
    static int findFirstFreePartition(String processId, int size) {
        for (int i = 0; i < numberOfPartitions; i++) {
            if (partitionArray[i].partitionStatus.equals("free") && partitionArray[i].partitionSize >= size) {
            	partitionArray[i].setPartitionStatus("allocated");
                partitionArray[i].setProcessID(processId);
                partitionArray[i].setInternalFragmentation(partitionArray[i].partitionSize-size);
                return i;
            }
        }
        return -1;
    }
    
    static int findBestFreePartition(String processId, int size) {
    	int min= -1;
    	int index=-1;
    	 for (int i = 0; i < numberOfPartitions; i++) {
    		 if( size <= partitionArray[i].partitionSize && partitionArray[i].partitionStatus.equals("free") )
    			 if(min==-1) {
    				 min =  partitionArray[i].partitionSize;
    				 index=i;
    			 }
    			 else if(  partitionArray[i].partitionSize<min) {
    				 min= partitionArray[i].partitionSize;
    				 index=i;
    			 }
    	 }
    	if(index != -1) {
    		partitionArray[index].setPartitionStatus("allocated");
            partitionArray[index].setProcessID(processId);
            partitionArray[index].setInternalFragmentation(partitionArray[index].partitionSize-size);
    	}
    	return index;
    }

    static int findWorstFreePartition(String processId, int size) { 
        int max= -1; 
        int index=-1; 
         for (int i = 0; i < numberOfPartitions; i++) { 
          if( partitionArray[i].partitionStatus.equals("free") && size <= partitionArray[i].partitionSize ) 
           if(partitionArray[i].partitionSize>max) 
          { 
            max= partitionArray[i].partitionSize; 
            index=i; 
           } 
          } 
        if(index != -1) { 
         partitionArray[index].setPartitionStatus("allocated"); 
               partitionArray[index].setProcessID(processId); 
               partitionArray[index].setInternalFragmentation(partitionArray[index].partitionSize-size); 
        }  
        return index; 
       }

   static void deallocating(String processId) {
	   for (int i = 0; i < numberOfPartitions; i++) {
		  if( partitionArray[i].processID.equals(processId)) {
			  partitionArray[i].setPartitionStatus("free");
			  partitionArray[i].setProcessID("NULL");
			  partitionArray[i].setInternalFragmentation(-1);
		  }  
	   }
   }
   
   static void display() {  
	  try {
	   File f = new File ("Report.txt");
	   FileOutputStream SF = new FileOutputStream(f);
	   PrintWriter PF = new PrintWriter(SF);
	 
	   System.out.println("------------------------------"+"Display"+"------------------------------");
	   		   PF.println("------------------------------"+"Display"+"------------------------------");
	   for (int i = 0; i < numberOfPartitions; i++) {
		   System.out.println("Partition status: "+partitionArray[i].partitionStatus);
		   		   PF.println("Partition status: "+partitionArray[i].partitionStatus);		   		   
		   System.out.println("Partition size: "+partitionArray[i].partitionSize);
		   		   PF.println("Partition size: "+partitionArray[i].partitionSize);		   		   
		   System.out.println("Starting address: "+partitionArray[i].startingAddress);
   		   		   PF.println("Starting address: "+partitionArray[i].startingAddress);   		   		   
		   System.out.println("Ending address: "+partitionArray[i].endingAddress);
		     	   PF.println("Ending address: "+partitionArray[i].endingAddress);		     	   
		   System.out.println("Process ID: "+partitionArray[i].processID);
		   	  	   PF.println("Process ID: "+partitionArray[i].processID);	   	  	   
		   System.out.println("Internal fragmentation size: "+partitionArray[i].internalFragmentation);
		   		   PF.println("Internal fragmentation size: "+partitionArray[i].internalFragmentation);   
		   System.out.println("----------------------------------------------------------");
		   		   PF.println("----------------------------------------------------------");
	   }
	 PF.close();
	  }catch(Exception ex) {
		  System.out.println(ex.getMessage());
	  }
   }
}