import javax.swing.JOptionPane;

public class Juice extends Cup {
	private String fruit;
	String[] fruits = {"Apple", "Orange", "Pineapple (+$0.50)"};

	public Juice(String size, int quantity, App app) {
		super(size, quantity);

        fruit = (String) JOptionPane.showInputDialog(app,
            "Select a fruit.",
            "Select a fruit",
            JOptionPane.QUESTION_MESSAGE,
            null,
            fruits,
            fruits[0]
        );
        
        double price;
        if (size.equals("Small")) {
            price = 3.0;
        } else if (size.equals("Medium")) {
            price = 4.0;
        } else { // Large
            price = 5.0;
        } 

        // Exit constructor when user closes dialog
        if (fruit == null) {
            return;
        }

        if (fruit.equals("Pineapple (+$0.50)")) {
            price += 0.50;
        }
        setPrice(price);
	}

	@Override
	public String getName() {
        if (fruit == null) {
            return "";
        }

        if (fruit.equals("Orange")) {
            return "Orange Juice";
        } else if (fruit.equals("Pineapple (+$0.50)")) {
            return "Pineapple Juice";
        } else {
            return "Apple Juice";
        }
	}
    
	@Override
	public String toString() {
		return super.toString() + getName();
	}

}
