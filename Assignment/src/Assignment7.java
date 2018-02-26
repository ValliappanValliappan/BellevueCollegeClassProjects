import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

//Name:Valliappan Valliappan	Class:CS 211
//Date:November 17,2017		Assignment #7
public class Assignment7 {
	public static void main(String[] args) throws IOException{
    	File hi=new File("test.txt");
    	hi.createNewFile();
    	PrintStream f=new PrintStream(hi);
    	f.print("ab ab cab");
    }
}
