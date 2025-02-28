//represent a single food item that belongs to only one of the four food categories
public class FoodItem {
	String name;
	int calories;
	
	public FoodItem(String n, int c) {
		name = n;
		calories = c;
	}
	
	public String getData() {
		return this.name + " (" + this.calories + ") ";
	}
	
	public boolean equals(FoodItem other) {
		if(this.name == other.name && this.calories == other.calories)
			return true;
		return false;
	}

}
