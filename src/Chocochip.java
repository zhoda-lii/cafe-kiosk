import javax.swing.JOptionPane;

public class Chocochip extends Cake {
    private boolean hasChoco;

    public Chocochip(String size, int quantity, App app) {
        super(size, quantity);

        boolean yesSelected = (JOptionPane.showConfirmDialog(app, "Would you like to add Choco Chips?", "Choco Chips",
                JOptionPane.YES_NO_OPTION) == 0);
        if (yesSelected) {
            hasChoco = true;
        } else {
            hasChoco = false;
        }

        double price;
        if (size.equals("Slice")) {
            price = 5;
        } else { // Whole Cake
            price = 10;
        }

        if (hasChoco) {
            price += 2.50;
        }
        setPrice(price);
    }

    @Override
    public String toString() {
        if (hasChoco) {
            return super.toString() + "Chocolate Cake with Choco Chip[s]";
        } else {
            return super.toString() + "Plain Chocolate Cake";
        }
    }

}
