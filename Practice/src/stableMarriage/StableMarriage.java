package stableMarriage;
// This program reads an input file of preferences and find a stable marriage
// scenario.  The algorithm gives preference to either men or women depending
// upon whether this call is made from main:
//      makeMatches(men, women);
// or whether this call is made:
//      makeMatches(women, men);


//NAME:VALLIAPPAN VALLIAPPAN
//ASSIGNMENT #4 - CHAPTER 11
import java.io.*;
import java.util.*;

public class StableMarriage {
    public static final String LIST_END = "END";

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.print("What is the input file? ");
        String fileName = console.nextLine();
        Scanner input = new Scanner(new File(fileName));
        System.out.println();

        List<Person> men = readHalf(input);
        List<Person> women = readHalf(input);
        makeMatches(men, women);
        writeList(men, women, "Matches for men");
        writeList(women, men, "Matches for women");
    }

    public static Person readPerson(String line) {
        int index = line.indexOf(":");
        Person result = new Person(line.substring(0, index));
        Scanner data = new Scanner(line.substring(index + 1));
        while (data.hasNextInt()) {
            result.addChoice(data.nextInt());
        }
        return result;
    }

    public static List<Person> readHalf(Scanner input) {
        List<Person> result = new ArrayList<Person>();
        String line = input.nextLine();
        while (!line.equals(LIST_END)) {
            result.add(readPerson(line));
            line = input.nextLine();
        }
        return result;
    }

    public static void makeMatches(List<Person> list1, List<Person> list2) {//lis1 is for men and list2 is for women according to how the method is used in main method
        for(Person a:list1){//Iterating throughout the list of men
    		a.erasePartner();//Removing partners of all men
    	}
    	for(Person a:list2){//Iterating throughout the list of women
    		a.erasePartner();//Removing partners of all women
    	}
    	int manIndex=0;
    	for(manIndex=0;manIndex<list1.size();manIndex++){
    		if(!list1.get(manIndex).hasPartner() && list1.get(manIndex).hasChoices()){
    		Person man=list1.get(manIndex);
    		Person woman=list2.get(man.getFirstChoice());
    		if(woman.hasPartner()){
    			list1.get(woman.getPartner()).erasePartner();//Erasing woman from another partner
    		}
    		//Engaging man and woman
    		woman.setPartner(manIndex);
    		man.setPartner(man.getFirstChoice());
    	
    		for(int i=woman.getChoices().indexOf(manIndex)+1;i<woman.getChoices().size();){
    			int manNumber=woman.getChoices().get(i);//Rank of man who needs to be removed from woman's preferences
    			woman.getChoices().remove(i);//erasing woman's lesser preference man
    			list1.get(manNumber).getChoices().remove(list1.get(manNumber).getChoices().indexOf(man.getFirstChoice()));//erasing woman because she is engaged now
    		}
    		manIndex=0;
    		}
    	}
    }
    public static void writeList(List<Person> list1,  List<Person> list2,
                                 String title) {
        System.out.println(title);
        System.out.println("Name           Choice  Partner");
        System.out.println("--------------------------------------");
        int sum = 0;
        int count = 0;
        for (Person p : list1) {
            System.out.printf("%-15s", p.getName());
            if (!p.hasPartner()) {
                System.out.println("  --    nobody");
            } else {
                int rank = p.getPartnerRank();
                sum += rank;
                count++;
                System.out.printf("%4d    %s\n", rank,
                                  list2.get(p.getPartner()).getName());
            }
        }
        System.out.println("Mean choice = " + (double) sum / count);
        System.out.println();
    }
}
