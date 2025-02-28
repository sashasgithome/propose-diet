import java.util.*;
import java.util.ArrayList;

public class GA {

	//defines food categories and other GA parameters
    private static final String[] categories = {"Carbohydrates", "Protein", "Vegetables", "Fruits"};

    private static int populationSize = 24;
    private static double mutationRate = 0.1;
    private static int maxGenerations = 15;
    private static double crossoverProbability = 0.9;
    private static double mutationProbability = 0.3;
    
    //WILL BE USER INPUT
    private static int numOfSolutions = 3; 
    private static int target = 500; 
    
    static FoodLibrary foodLibrary = new FoodLibrary();
    //initialize the foodLibrary (get the data into list) here------
    
    //runs the evolution loop
	public static void main(String[] args) {
		//initialize the population
        Chromosome[] population = initializePopulation();
        
        //keep evolving the population (choose best ones, do crossover, do mutatation) for 15 generations
        for (int generation = 1; generation <= maxGenerations; generation++) {
            
            //do selection : getting parents for next generation
        	Chromosome[] parents = selection(population);
        	int totalParents = parents.length;
        	Chromosome[] offsprings = new Chromosome[populationSize];
        	
            //do crossover : mating the parents
        	for(int o = 0; o < offsprings.length; o++) {
        		//choosing 2 parents randomly from the list
        		Chromosome parent1 = parents[(int) (Math.random()*totalParents)];
        		Chromosome parent2 = null;
        		
        		//making sure both parents are not the same individual
        		do {
        			parent2 = parents[(int) (Math.random()*totalParents)];
        		}while(! parent1.equals(parent2));
        		
        		offsprings[o] = crossover(parent1, parent2);
        		o++;
        		offsprings[o] = crossover(parent2, parent1);
        	}
            
            //do mutation only if neccesary
        	for(int o = 0; o < offsprings.length; o++) {
        		if(Math.random() < mutationProbability) {
	    			offsprings[o].setGene(o, foodLibrary.getRandomFoodItem(o));
	    			offsprings[o].setFitness(target);
        		}
        	}
            
        	population = offsprings;
        }
        
        ArrayList<Chromosome> solutions = new ArrayList<Chromosome>();
        solutions = (ArrayList<Chromosome>) Arrays.asList(population);
        Collections.sort(solutions, Collections.reverseOrder()); 
        
        System.out.println("Top Three Solutions : ");
        for(int f = 0; f < numOfSolutions; f++) {
        	Chromosome s = solutions.get(f);
        	System.out.println(f + ") " + s.printAllFoodItems() + " with total calories of " + s.getTotalCalories());
        }
    }
	
	//returns one offspring each crossover -- uses uniform crossover with binary mask
    private static Chromosome crossover(Chromosome parentOne, Chromosome parent2) {
    	//for mask 0, the offspring 
    	int[] mask = {1,0,0,1};
    	Chromosome offspring = new Chromosome();
    	
    	for(int i=0; i < mask.length; i++) {
    		//if(Math.random() < crossoverProbability) {
	    		if(mask[i] == 0)
	    			offspring.setGene(i, parentOne.getGene(i));
	    		else
	    			offspring.setGene(i, parentOne.getGene(i));
	    		offspring.setFitness(target);
    		//}
    	}
    	return offspring;
    	
    }
	
	//returns 12 individuals (6 pairs) as parents, later will have 2 offsprings from each pair
    private static Chromosome[] selection(Chromosome[] population) {
    	//select half of the population as parents for next gen
        Chromosome[] parents = new Chromosome[populationSize / 2];
        int c = 0;
        
        while (parents[parents.length-1] == null) { 
        	
        	//selecting a num of random individuals per tournament
        	int tourSize = 4;
            Chromosome[] participants = new Chromosome[tourSize];
            
            for (int i = 0; i < tourSize; i++) {
            	participants[i] = population[(int)(Math.random()*populationSize)];
            }
            
            //finding best individual in that specific tour
            Chromosome bestP = participants[0];
            for (Chromosome p : participants) {
                if (p.getFitness() < bestP.getFitness()) {
                    bestP = p;
                }
            }
            
            //STILL NEED TO CHECK if bestP has already existed in parents list or not yet
            List temp = Arrays.asList(parents);
            if(temp.indexOf(bestP)==-1) {
            	parents[c] = bestP;
            	c++;
            }
        }
        return parents;
    }
	
	private static Chromosome[] initializePopulation() {
		Chromosome[] population = new Chromosome[populationSize];
        for (int i = 0; i < populationSize; i++) {
            Chromosome newChromosome = new Chromosome();
            for (int c = 0; c < 4; c++) {
            	newChromosome.setGene(c, foodLibrary.getRandomFoodItem(c));
            }
            population[i] = newChromosome;
        }
        return population;
    }
}
