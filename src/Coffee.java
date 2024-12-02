import javax.swing.JOptionPane;

public class Coffee extends Cup {
	private boolean hasMilk; 
    private int response;

	public Coffee(String size, int quantity, App app) {
		super(size, quantity);

		response = JOptionPane.showConfirmDialog(app,
				"Would you like to add almond milk for extra $0.25?",
				"Milk", JOptionPane.YES_NO_OPTION);

		if (response == JOptionPane.YES_OPTION) {
			hasMilk = true;
		} else if (response == JOptionPane.NO_OPTION) {
			hasMilk = false;
		} else if (response == JOptionPane.CLOSED_OPTION) {
			return;
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
	public String getName() {
        if (response == JOptionPane.CLOSED_OPTION) {
            return "";
        }
        
		if (hasMilk) {
			return "Coffee with Almond Milk";
		} else {
			return "Coffee";
		}
	}

	@Override
	public String toString() {
		return super.toString() + getName();
	}
}