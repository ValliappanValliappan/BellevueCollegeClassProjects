import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HuffmanNode implements Comparable<HuffmanNode> {
	public int frequency;
	public char character;
	public HuffmanNode left;
	public HuffmanNode right;

	@Override
	public int compareTo(HuffmanNode o) {
		return frequency - o.frequency;// Will be positive if the object calling
										// the method has greater frequency than
										// o, negative if less than and 0 if
										// equal to.
	}

	public boolean isLeaf() {
		return (left == null) && (right == null);// will return true if the node
													// calling the method is a
													// leaf
	}

	public static Map<Character, Integer> getCounts(InputStream input) {
		Map<Character, Integer> mapOfFrequencies = new HashMap<Character, Integer>();// Creating a HashMap
		int asciiValue = 0;// ascii value of each char that is going to be read
		while (true) {// the condition to end the loop is inside the loop
			try {
				asciiValue = input.read();// gives the next byte from the file that input is assigned to read
				if(asciiValue == -1){
					break;//input.read() returns -1 if there are no bytes left in the input stream
				}
				char key = (char)asciiValue;//ascii value to character
				int initialValue;
				try{
					initialValue=mapOfFrequencies.get(key);
				}
				catch(NullPointerException e){//mapOfFrequencies.get(key) returns null if key-value pair doesnt exist
					initialValue=0;
				}
				mapOfFrequencies.put(key, initialValue+1);//adding one to initial value as we just found one more of the same character

			} catch (Exception e) {// input.read() might cause an exception
				//Do nothing
			}
		}//The map would have all the characters and corresponding frequencies by this point
		return mapOfFrequencies;
	}
}
