

import java.text.NumberFormat;

public class Item {
	private String name;
	private double price;
	private int bulkQ = -1;
	private double bulkPrice = -1;

	public Item(String name, double price) {
		if (price < 0) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.price = price;
	}

	public Item(String name, double price, int bulkQuantity, double bulkPrice) {
		this(name, price);
		if (bulkQuantity < 0 || bulkPrice < 0) {
			throw new IllegalArgumentException();
		}
		bulkQ = bulkQuantity;
		this.bulkPrice = bulkPrice;
	}

	public double priceFor(int quantity) {
		if (bulkQ < 0) {// Means that it never changed from default value
			return price * (double) quantity;
		}
		int bulkDiscountGroups = quantity / bulkQ;
		int noBulkDiscount = quantity % bulkQ;
		return ((double) bulkDiscountGroups * bulkPrice) + ((double) noBulkDiscount * price);
	}
	public String toString(){
		NumberFormat toExpressInDollars=NumberFormat.getCurrencyInstance();
		String extraStuff=(bulkQ==-1)?"":(" "+bulkQ+" for "+toExpressInDollars.format(bulkPrice));
		return name+", "+toExpressInDollars.format(price)+extraStuff;
	}
}
