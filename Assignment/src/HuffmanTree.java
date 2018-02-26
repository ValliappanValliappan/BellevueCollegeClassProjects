import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
//Valliappan Valliappan
//Class:CS 211
public class HuffmanTree {
	HuffmanNode root;
	Map<Character,String> codes=new HashMap<Character,String>();
	public HuffmanTree(Map<Character, Integer> counts) // Constructor
	{
		// Put the contents of the map into a priority queue of Huffman nodes
		Queue<HuffmanNode> nodeQueue = new PriorityQueue<HuffmanNode>();
		for (char key : counts.keySet()) {
			HuffmanNode toInsert = new HuffmanNode();
			toInsert.frequency = counts.get(key);// Setting values for the new
													// Huffman node that was
													// created
			toInsert.character = key;
			nodeQueue.add(toInsert);// Adding the node that will be sorted based
									// on the frequency by the priority queue
		}
		// Organize elements of the queue into a huffman tree structure by
		// reorganizing the huffman nodes in the queue
		while (nodeQueue.size() > 1) {// Keep organizing the structure until
										// there's only the root node of the
										// huffman tree in the queue
			HuffmanNode leastFrequencyNode = Collections.min(nodeQueue);
			nodeQueue.remove(leastFrequencyNode);
			HuffmanNode secondLeastFrequencyNode = Collections.min(nodeQueue);// Removed
																		// the
																		// top
																		// two
																		// least
																		// frequency
																		// nodes
			nodeQueue.remove(secondLeastFrequencyNode);
			int sumOfFrequencies = leastFrequencyNode.frequency + secondLeastFrequencyNode.frequency;// adding
																										// their
																										// frequencies
			HuffmanNode root = new HuffmanNode();// to be root of the two nodes
													// that were just removed
			root.frequency = sumOfFrequencies;
			root.right = secondLeastFrequencyNode;// left must have the node
													// with the higher frequency
			root.left = leastFrequencyNode;// right should have the node with
											// the least frequency
			nodeQueue.add(root);// Adding root after combining two least
								// frequency nodes
		}
		root = nodeQueue.peek();// peeking the completed node hierarchy from
									// nodeQueue and putting it all in root
	}

	public StringBuilder compress(FileInputStream inputFile) throws IOException // inputFile is a
															// textfile
	{ // Create a Map with the character mapped to a frequency of that character
		// in the given input file
		Map<Character, Integer> counts = HuffmanNode.getCounts(inputFile);// Create
																			// a
																			// HashMap
		inputFile.getChannel().position(0);//resetting the input stream
		HuffmanTree tree = new HuffmanTree(counts);// making a huffman tree from
													// the counts map that can give the binary codes
		traverseAnGenerateBinaryCodes(tree.root,"");
		StringBuilder compressedText=new StringBuilder("");
		int asciiValue = 0;// ascii value of each char that is going to be read
		while (true) {// the condition to end the loop is inside the loop
			try {
				asciiValue = inputFile.read();// gives the next byte from the file that input is assigned to read
				if(asciiValue == -1){
					break;//input.read() returns -1 if there are no bytes left in the input stream
				}
				char character = (char)asciiValue;//ascii value to character
				compressedText.append(codes.get(character));
			} catch (Exception e) {// input.read() might cause an exception
				//Do nothing
			}
		}
		return compressedText;
	}

	private void traverseAnGenerateBinaryCodes(HuffmanNode node, String binaryCode) {
		HuffmanNode right=node.right;
		HuffmanNode left=node.left;
		if(right!=null){
			traverseAnGenerateBinaryCodes(right, binaryCode+"1");
			if(right.isLeaf()){
				codes.put(right.character, binaryCode+"0");
			}
		}
		if(left!=null){
			traverseAnGenerateBinaryCodes(left, binaryCode+"0");
			if(left.isLeaf()){
				codes.put(left.character, binaryCode+"0");
			}
		}
	}
	private HuffmanNode characterForBinaryCode(HuffmanNode node,String binary){
		if(binary.equals("0")){
			return node.left;
		}
		return node.right;
	}
	public StringBuilder decompress(StringBuilder inputString) // inputString
																// 1’s & 0’s
	{	StringBuilder builder=new StringBuilder("");
		HuffmanNode destinationNode=root;
		for(int i=0;i<inputString.length();i++){
			if(destinationNode.isLeaf()){
				builder.append(destinationNode.character);
				destinationNode=root;
			}
			else{
				destinationNode=characterForBinaryCode(destinationNode, inputString.substring(i, i+1));
			}
		}
		return builder;
	}
	private String printSideways(HuffmanNode node,int level,String consolidatedTree){
		if(root!=null&&!root.isLeaf()){
			printSideways(node.right,level+1, consolidatedTree);//To print the right side since the tree is rotated left 90 degrees
			for(int i=0;i<level;i++){
				consolidatedTree+="    ";//Adding four spaces for each level. The number of space characters gives the level of the element seen in the string representation of the tree
			}
			consolidatedTree+=node.frequency+"=";//Data for the current node
			if(!node.isLeaf()){//Leaf nodes are always the ones with the character.Checking if node is a count node or if it contains a character
				consolidatedTree+="count";
			}
			else{
				consolidatedTree+="char("+((Character)(node.character)).hashCode()+")";
			}
			printSideways(node.left,level+1,consolidatedTree);//To print left side of the node since its the only remaining part of the root node that hasn't been printed
		}
		return consolidatedTree;
	}
	public String printSideways() // as per the method presented in Chapter 17.
	{
		return printSideways(root,0,"");//making the starting node the root node of the tree, the starting level 0 and the initial string representation of the tree an empty string
	}
}