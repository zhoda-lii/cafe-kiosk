import javax.swing.JOptionPane;

public class Coffee extends Cup {
	private boolean hasMilk; 

	public Coffee(String size, int quantity, App app) {
		super(size, quantity);

		boolean yesSelected = (JOptionPane.showConfirmDialog(app, "Would you like milk?", "Milk", JOptionPane.YES_NO_OPTION) == 0);
		if (yesSelected) {
			hasMilk = true;
		} else {
			hasMilk = false;
		}

		double price;
		if (size.equals("Small")) {
			price = 2;
		} else if (size.equals("Medium")) {
			price = 2.5;
		} else { // Large
			price = 3;
		}
		
		if (hasMilk) {
			price += 0.25;
		}
		setPrice(price);
	}

	@Override
	public String toString() {
		if (hasMilk) {
			return super.toString() + "Coffee with milk";
		} else {
			return super.toString() + "Coffee without milk";
		}
	}
}