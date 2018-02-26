import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Reges and Stepp, Building Java Programs
 * modified for Bellevue College
 * for CS211 class
 *
 */
// ShoppingMain provides method main for a simple shopping program.

public class ShoppingMain {
    public static void main(String[] args) {
    	Map<Character, Integer> mapOfFrequencies = new HashMap<Character, Integer>();
    	mapOfFrequencies.put('i', 2000);
    	mapOfFrequencies.put('i',21);
    	System.out.println(mapOfFrequencies.get('i'));
    }
}
