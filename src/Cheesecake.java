import javax.swing.JOptionPane;

public class Cheesecake extends Cake {
    private boolean hasStraw;
    private int response;

    public Cheesecake(String size, int quantity, App app) {
        super(size, quantity);

        response = JOptionPane.showConfirmDialog(app, 
            "Would you like to add extra strawberries for extra $0.75?", 
            "Choco Chips", JOptionPane.YES_NO_OPTION);
            
        if (response == JOptionPane.YES_OPTION) {
            hasStraw = true;
        } else if (response == JOptionPane.NO_OPTION) {
            hasStraw = false;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return;
        }

        double price;
        if (size.equals("Slice")) {
            price = 6;
        } else { // Whole Cake
            price = 45;
        }

        if (hasStraw) {
            price += 0.75;
        }
        setPrice(price);
    }

	@Override
	public String getName() {
        if (response == JOptionPane.CLOSED_OPTION) {
            return "";
        }

        if (hasStraw) {
            return "Strawberry Cake with Berries";
        } else {
            return "Plain Strawberry Cake";
        }
	}

    @Override
    public String toString() {
		return super.toString() + getName();
    }

}
