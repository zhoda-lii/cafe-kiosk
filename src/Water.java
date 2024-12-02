import javax.swing.JOptionPane;

public class Water extends Cup {
	private boolean hasLemon;
    private int response;

	public Water(String size, int quantity, App app) {
		super(size, quantity);

		response = JOptionPane.showConfirmDialog(app,
				"Would you like to add lemon for extra $0.50?",
				"Milk", JOptionPane.YES_NO_OPTION);

		if (response == JOptionPane.YES_OPTION) {
			hasLemon = true;
		} else if (response == JOptionPane.NO_OPTION) {
			hasLemon = false;
		} else if (response == JOptionPane.CLOSED_OPTION) {
			return;
		}

		double price;
		if (size.equals("Small")) {
			price = 2.5;
		} else if (size.equals("Medium")) {
			price = 3.5;
		} else { // Large
			price = 4.5;
		}

		if (hasLemon) {
			price += 0.5;
		}
		setPrice(price);
	}

	@Override
	public String getName() {
        if (response == JOptionPane.CLOSED_OPTION) {
            return "";
        }
		
		if (hasLemon) {
			return "Sparkling Water with Lemon";
		} else {
			return "Sparkling Water";
		}
	}
    
	@Override
	public String toString() {
		return super.toString() + getName();
	}
}