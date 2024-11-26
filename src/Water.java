import javax.swing.JOptionPane;

public class Water extends Cup {
	private boolean hasIce;

	public Water(String size, int quantity, App app) {
		super(size, quantity);

		boolean yesSelected = (JOptionPane.showConfirmDialog(app, "Would you like ice?", "Ice", JOptionPane.YES_NO_OPTION) == 0);
		if (yesSelected) {
			hasIce = true;
		} else {
			hasIce = false;
		}

		double price;
		if (size.equals("Small")) {
			price = 2.5;
		} else if (size.equals("Medium")) {
			price = 3;
		} else { // Large
			price = 3.5;
		}
		
		if (hasIce) {
			price += 0.25;
		}
		setPrice(price);
	}

	@Override
	public String toString() {
		if (hasIce) {
			return super.toString() + "Sparkling Water with ice";
		} else {
			return super.toString() + "Sparkling Water without ice";
		}
	}
}