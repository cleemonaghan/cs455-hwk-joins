package src;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class JoinEngine {
	
	public static final String PATH = "C:\\Users\\Colin\\software\\cs455\\cs455-hwk-joins";

	/**
	 * Create a class in your project called JoinEngine that only has the main() method. 
	 * When the program starts, you should read every file in the data/ directory into a 
	 * object representing a relation. You must use the file name, minus the file extension 
	 * (.txt), as the name of the relation. For grading, I will be using completely different 
	 * files (with different file names). Your program must work, assuming that my data/ directory 
	 * will be placed in your project directory. You should test rigorously with your own toy-files 
	 * to ensure that your program can accept arbitrary data sets. Assignments that fail to work with 
	 * my data sets will be returned without a grade. Look into using the File class’ listFiles() method.
	 */
	public static void main(String[] args) {
		
		// try-catch block to handle exceptions
        try {
        	//initialize the hashMap of relations
        	HashMap<String, Relation> relations = new HashMap<String, Relation>();
  
            // Create a file object
            File f = new File(PATH+"\\data");
  
            // Get all the names of the files present
            // in the given directory
            File[] files = f.listFiles();
            

            // Phase 1: Display the available relations
            System.out.println("Available relations:\n");
            System.out.print("\t");
            for (int i = 0; i < files.length; i++) {
            	String fileName = files[i].getName();
            	// ignore any file whose filename starts with a dot
            	if(fileName.startsWith(".")) continue;
            	
            	//create a relation object for the file
            	Relation relation = new Relation();
            	
            	//add it to the HashMap of relations
            	relations.put(fileName, relation);
            	
            	// remove the .txt from each of the files
            	if(fileName.endsWith(".txt")) {
            		fileName = fileName.substring(0, fileName.length()-4);
            	}
            	
                System.out.print(fileName+ " ");
            }
            System.out.println("\n");
            
            
            // Phase 2: Retrieve user input
            System.out.print("Your selection (separated by space): ");
            
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            String[] inputs = scanner.nextLine().split(" ");  // Read user input and split it into substrings
           
            ArrayList<Relation> relationsToJoin = new ArrayList<Relation>();
            for (int i = 0; i < inputs.length; i++) {
            	Relation r = relations.get(inputs[i]+".txt");
            	if(r == null) throw new IllegalArgumentException("Illegal Argument Exception: "+inputs[i]);
            	//add the relation to the arrayList
            	relationsToJoin.add(r);
            }
            
            // Phase 3: Join method selection
            System.out.println("Choose a join algorithm:"); 
            System.out.println("\t1. Nested loop join");
            System.out.println("\t2. Hash Join");
            System.out.println("\t3. Sort-Merge Join");
            System.out.print("Your selection: ");
            
            // Read user input and split it into substrings
            int input = scanner.nextInt();  
            // close the scanner
            scanner.close();	
            
            //select which join to perform
            if(input == 1) {
            	//Nested loop join
            }
            else if(input == 2) {
            	//Hash Join
            }
            else if(input == 3) {
            	//Sort-Merge join
            }
            else {
            	throw new IllegalArgumentException("Illegal Argument Exception: "+input);
            }

            // Phase 4: Process the join and output the results
            Relation result = relationsToJoin.get(0);

            //time the join process
            long startTime = System.nanoTime();
            for(int i = 1; i < relationsToJoin.size(); i++) {
            	//result = join(result, relationsToJoin.get(i));
            }
            long endTime = System.nanoTime();
            
            //print out the relation, the time it took to join it, and the number of rows in the relation
            System.out.println(result);
            System.out.println("\nTime = "+1e-6*(endTime - startTime)+" ms");
            System.out.println("Total Rows = "+result.totalRows());
            
            
        }
        catch(IllegalArgumentException e) {
        	System.err.println(e.getMessage());
        	System.exit(1);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
	}
}
