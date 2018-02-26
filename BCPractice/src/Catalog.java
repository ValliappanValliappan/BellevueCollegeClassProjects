

import java.util.ArrayList;

public class Catalog {
	String name;
	ArrayList<Item> itemList=new ArrayList<Item>();
	public Catalog(String name){
		this.name=name;
	}
	public void add(Item item){
		itemList.add(item);
	}
	public int size(){
		return itemList.size();
	}
	public Item get(int index){
		return itemList.get(index);
	}
	public String getName(){
		return name;
	}
	
}
