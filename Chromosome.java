//represents one single individual, having 4 genes
public class Chromosome {

	//represents one single individual, having 4 genes
    private final FoodItem[] genes;
    private int fitness = -1;

    //constructor
    public Chromosome() {
        genes = new FoodItem[4];
    }

    //get the food item for a specific category
    public FoodItem getGene(int category) {
        return genes[category];
    }

    //change the food item for a specific category
    public void setGene(int category, FoodItem foodItem) {
        genes[category] = foodItem;
        fitness = -1; // Reset fitness when genes change
    }
    
    //getting the individual's fitness value
    public int getFitness() {
    	return this.fitness;
    }

    //setting the individual's fitness value
    public void setFitness(int targetCalories) {
        if (fitness == -1) {
            // Calculate fitness as the absolute difference from the target calories
            double totalCalories = getTotalCalories();
            fitness = (int) Math.abs(totalCalories - targetCalories);
        }
    }

    //getting the total calories of all items in this individual dish
    public double getTotalCalories() {
        double totalCalories = 0;
        for (int i=0; i<4; i++){
            totalCalories += genes[i].calories;
        }
        return totalCalories;
    }
    
    //returns string of all food items and each of their calories
    public String printAllFoodItems() {
    	return genes[0].getData() + genes[1].getData() + genes[2].getData() + genes[3].getData();
    }
    
    //to compare between 2 different dishes
    public boolean equals(Chromosome c) {
    	for(int i=0; i<genes.length; i++) {
    		if(!this.genes[i].equals(c.genes[i]))
    			return false;
    	}
		return true;
    }
    
    public int compareTo(Chromosome c) {
    	return this.fitness - c.fitness;
    }
}
