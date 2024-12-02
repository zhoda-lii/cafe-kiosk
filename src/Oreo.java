import javax.swing.JOptionPane;

public class Oreo extends Cake {
    private boolean hasOreo;
    private int response;

    public Oreo(String size, int quantity, App app) {
        super(size, quantity);

        response = JOptionPane.showConfirmDialog(app, 
            "Would you like to add extra oreo crumbs for $0.50?", 
            "Choco Chips", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            hasOreo = true;
        } else if (response == JOptionPane.NO_OPTION) {
            hasOreo = false;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return;
        }

        double price;
        if (size.equals("Slice")) {
            price = 5;
        } else { // Whole Cake
            price = 40;
        }

        if (hasOreo) {
            price += 0.50;
        }
        setPrice(price);
    }

	@Override
	public String getName() {
        if (response == JOptionPane.CLOSED_OPTION) {
            return "";
        }
        
        if (hasOreo) {
            return "Oreo Cake with Oreo Crumbs";
        } else {
            return "Plain Oreo Cake";
        }
	}

    @Override
    public String toString() {
		return super.toString() + getName();
    }
}
