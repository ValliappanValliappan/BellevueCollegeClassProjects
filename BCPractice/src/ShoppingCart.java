

import java.util.ArrayList;

public class ShoppingCart {
	ArrayList<ItemOrder> listOfOrders;
	private boolean isDiscountable=false;
	public ShoppingCart(){
		listOfOrders=new ArrayList<ItemOrder>();
	}
	public void add(ItemOrder order){
		Item toRemove=order.getItem();
		for(ItemOrder oneOfThem:listOfOrders){
			if(oneOfThem.getItem().equals(toRemove)){
				listOfOrders.remove(oneOfThem);
			}
		}
		listOfOrders.add(order);
	}
	public void setDiscount(boolean value){
		isDiscountable=value;
	}
	public double getTotal(){
		double totalPrice=0.0;
		for(ItemOrder oneOfThem:listOfOrders){
			totalPrice+=oneOfThem.getPrice();
		}
		if(isDiscountable){
			totalPrice*=0.9;
		}
		return totalPrice;
	}
}
