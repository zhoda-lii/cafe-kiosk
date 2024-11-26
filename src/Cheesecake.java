import javax.swing.JOptionPane;

public class Cheesecake extends Cake {
    private boolean hasStraw;

    public Cheesecake(String size, int quantity, App app) {
        super(size, quantity);

        boolean yesSelected = (JOptionPane.showConfirmDialog(app, "Would you like to add Strawberries?", "Strawberry",
                JOptionPane.YES_NO_OPTION) == 0);
        if (yesSelected) {
            hasStraw = true;
        } else {
            hasStraw = false;
        }

        double price;
        if (size.equals("Slice")) {
            price = 5;
        } else { // Whole Cake
            price = 10;
        }

        if (hasStraw) {
            price += 2.50;
        }
        setPrice(price);

    }

    @Override
    public String toString() {
        if (hasStraw) {
            return super.toString() + "Strawberry Cake with Berries";
        } else {
            return super.toString() + "Plain Strawberry Cake";
        }
    }

}
