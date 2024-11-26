import javax.swing.JOptionPane;

public class Oreo extends Cake {
    private boolean hasOreo;

    public Oreo(String size, int quantity, App app) {
        super(size, quantity);

        boolean yesSelected = (JOptionPane.showConfirmDialog(app, "Would you like to add Oreo Crumbs?", "Orea Crumbs",
                JOptionPane.YES_NO_OPTION) == 0);
        if (yesSelected) {
            hasOreo = true;
        } else {
            hasOreo = false;
        }

        double price;
        if (size.equals("Slice")) {
            price = 5;
        } else { // Whole Cake
            price = 10;
        }

        if (hasOreo) {
            price += 2.50;
        }
        setPrice(price);
    }

    @Override
    public String toString() {
        if (hasOreo) {
            return super.toString() + "Oreo Cake with Oreo Crumb[s]";
        } else {
            return super.toString() + "Plain Oreo Cake";
        }
    }
}
