import javax.swing.JOptionPane;

public class Tea extends Cup {
    private boolean hasSugar;
    private int response;

    public Tea(String size, int quantity, App app) {
        super(size, quantity);

		response = JOptionPane.showConfirmDialog(app,
				"Would you like to add maple syrup for extra $0.25?",
				"Milk", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            hasSugar = true;
        } else if (response == JOptionPane.NO_OPTION) {
            hasSugar = false;
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

        if (hasSugar) {
            price += 0.25;
        }
        setPrice(price);
    }

	@Override
	public String getName() {
        if (response == JOptionPane.CLOSED_OPTION) {
            return "";
        }
        
        if (hasSugar) {
            return "Tea with Maple Syrup";
        } else {
            return "Tea";
        }
	}
    
	@Override
	public String toString() {
		return super.toString() + getName();
	}
}
