package QRCodeGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Creator {
public static void main(String[] args){
	Queue<Integer> q=new LinkedList<Integer>();
	q.add(10);
	q.add(110);
	q.add(213);
	Stack<Integer> s=new Stack<Integer>();
	s.addAll(q);
	System.out.println(s.peek()+" "+q.peek());
}
}
