import javax.swing.JOptionPane;

public class Tea extends Cup {
    private boolean hasSugar;

    public Tea(String size, int quantity, App app) {
        super(size, quantity);

        boolean yesSelected = (JOptionPane.showConfirmDialog(app, "Would you like sugar", "Sugar",
                JOptionPane.YES_NO_OPTION) == 0);
        if (yesSelected) {
            hasSugar = true;
        } else {
            hasSugar = false;
        }

        double price;
        if (size.equals("Small")) {
            price = 2.5;
        } else if (size.equals("Medium")) {
            price = 3;
        } else { // Large
            price = 3.5;
        }

        if (hasSugar) {
            price += 0.25;
        }
        setPrice(price);
    }

    @Override
    public String toString() {
        if (hasSugar) {
            return super.toString() + "Tea with Sugar";
        } else {
            return super.toString() + "Tea without Sugar";
        }
    }
}
