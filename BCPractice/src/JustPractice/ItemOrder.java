package JustPractice;

public class ItemOrder {
	private Item itemToOrder;
	private int quantity;
	public ItemOrder(Item item,int quantity){
		itemToOrder=item;
		this.quantity=quantity;
	}
	public double getPrice(){
		return itemToOrder.priceFor(quantity);
	}
	public Item getItem(){
		return itemToOrder;
	}
}
