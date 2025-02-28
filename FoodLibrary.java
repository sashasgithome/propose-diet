import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.*;

//represents all possible dishes from all categories
public class FoodLibrary {

	private  int size = 10;
	private  FoodItem[] carbohydrates;
    private  FoodItem[] proteins;
    private  FoodItem[] vegetables ;
    private  FoodItem[] fruits;
    
    //will be where we enter all the data for food
    public void initializeFoodLibrary() {
    	carbohydrates = readFromFile("carbohydrates.txt");
    	proteins = readFromFile("proteins");
    	vegetables = readFromFile("vegetables");
    	fruits = readFromFile("fruits");
    }

    //to retrieve a random food item from a specific category
    public FoodItem getRandomFoodItem(int category) {
    	int random = (int) (Math.random()*10);
        if(category == 0)
        	return carbohydrates[random];
        else if(category == 1)
        	return proteins[random];
        else if(category == 1)
        	return vegetables[random]; 
        return fruits[random];	
    }
    
    private FoodItem[] readFromFile(String filename) {
    	FoodItem[] temp = new FoodItem[size];
    	int i = 0;
    	
    	try {
    		
    	      File currentFile = new File(filename);
    	      Scanner sc = new Scanner(currentFile);
    	      while (sc.hasNextLine()) {
    	        String data = sc.nextLine();
    	        String[] result = data.split("-");
    	        temp[i] = new FoodItem(result[0],Integer.parseInt(result[1]));
    	        i++;
    	      }
    	      sc.close();
    	      
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
    	
    	return temp;
    }
}
