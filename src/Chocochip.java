import javax.swing.JOptionPane;

public class Chocochip extends Cake {
    private boolean hasChoco;
    private int response;

    public Chocochip(String size, int quantity, App app) {
        super(size, quantity);

        response = JOptionPane.showConfirmDialog(app, 
            "Would you like to add extra choco chips for $0.50?", 
            "Choco Chips", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            hasChoco = true;
        } else if (response == JOptionPane.NO_OPTION) {
            hasChoco = false;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return;
        }

        double price;
        if (size.equals("Slice")) {
            price = 4.5;
        } else { // Whole Cake
            price = 35;
        }

        if (hasChoco) {
            price += 0.50;
        }
        setPrice(price);
    }

	@Override
	public String getName() {
        if (response == JOptionPane.CLOSED_OPTION) {
            return "";
        }
        
        if (hasChoco) {
            return "Chocolate Cake with Choco Chips";
        } else {
            return "Plain Chocolate Cake";
        }
	}

    @Override
    public String toString() {
		return super.toString() + getName();
    }

}
