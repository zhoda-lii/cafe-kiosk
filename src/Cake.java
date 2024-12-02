public class Cake {
    private String size;
    private double price;
    private int quantity;

    public Cake(String size, int quantity) {
		// super();
        this.size = size;
        this.quantity = quantity;
        price = 0.0;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
	
	public String getName() {
		return "";
	}

    public String toString() {
        return String.format("%d %s cake(s) ", this.getQuantity(), this.getSize());
    }

}
