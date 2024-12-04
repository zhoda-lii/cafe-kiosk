import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

// Use JFrame as the parent class of the App
// Use ActionListener as the interface of the App
public class App extends JFrame implements ActionListener {
	// Common Variables
	private JLabel lblSize, lblProduct, lblPrice, lblQuantity, lblOrders;
	private JSeparator sepHorLine;
	private JTextArea txtArea;
	private Border borTextArea;
	private JButton btnOrder;

	// Cups Variables
	private JComboBox<String> cmbCupSize;
	private ButtonGroup btnGrpCupProd;
	private JRadioButton rdbCoffee, rdbJuice, rdbTea, rdbWater;
	private JButton btnAddCupProd;
	private JTextField txtQtyCups;

	// Cakes Variables
	private JComboBox<String> cmbCakeSize;
	private ButtonGroup btnGrpCakeProd;
	private JRadioButton rdbChoco, rdbOreo, rdbStraw;
	private JButton btnAddCakeProd;
	private JTextField txtQtyCakes;

	// Colour for UI app
	Color greenColor = new Color(0x06241);

	// Arrays for products
	ArrayList<Cup> cup_order_items = new ArrayList<Cup>();
	ArrayList<Cake> cake_order_items = new ArrayList<Cake>();

	@Override
	public void actionPerformed(ActionEvent e) {
		// Action for the Add Item for Cup Products
		if (e.getSource().equals(btnAddCupProd)) {
			if (!(txtQtyCups.getText().isEmpty()) &&
					rdbCoffee.isSelected() || rdbJuice.isSelected() || rdbTea.isSelected() || rdbWater.isSelected()) {
				try {
					String cupSize = (String) cmbCupSize.getSelectedItem();
					int cupQty = Integer.parseInt(txtQtyCups.getText().trim());

					Cup cup;
					if (rdbCoffee.isSelected()) {
						cup = new Coffee(cupSize, cupQty, this);
					} else if (rdbJuice.isSelected()) {
						cup = new Juice(cupSize, cupQty, this);
					} else if (rdbTea.isSelected()) {
						cup = new Tea(cupSize, cupQty, this);
					} else { // rdbWater
						cup = new Water(cupSize, cupQty, this);
					}

					// Exit if cup is not initialized
					if (cup.getName().equals("")) {
						return;
					}

					txtQtyCups.setText(null);
					cup_order_items.add(cup);
					txtArea.append(cup.toString() + "\n");
					btnGrpCupProd.clearSelection();

					// Enable order button
					btnOrder.setEnabled(true);

					// If the data in quantity field cannot be converted to integer
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "Enter quantity as integer.");
				}

				// If none of the radio buttons are selected or the quantity field is empty
			} else {
				JOptionPane.showMessageDialog(this, "Select a drink and enter quantity.");
			}
		}

		// Action for the Add Item for Cake Products
		if (e.getSource().equals(btnAddCakeProd)) {
			if (!(txtQtyCakes.getText().isEmpty()) &&
					rdbChoco.isSelected() || rdbStraw.isSelected() || rdbOreo.isSelected()) {
				try {
					String cakeSize = (String) cmbCakeSize.getSelectedItem();
					int cakeQty = Integer.parseInt(txtQtyCakes.getText().trim());

					Cake cake;
					if (rdbChoco.isSelected()) {
						cake = new Chocochip(cakeSize, cakeQty, this);
					} else if (rdbStraw.isSelected()) {
						cake = new Cheesecake(cakeSize, cakeQty, this);
					} else { // rdbOrea
						cake = new Oreo(cakeSize, cakeQty, this);
					}

					// Exit if cake is not initialized
					if (cake.getName().equals("")) {
						return;
					}

					txtQtyCakes.setText(null);
					cake_order_items.add(cake);
					txtArea.append(cake.toString() + "\n");
					btnGrpCakeProd.clearSelection();

					// Enable order button
					btnOrder.setEnabled(true);

					// If the data in quantity field cannot be converted to integer
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(this, "Enter quantity as integer.");
				}

				// If none of the radio buttons are selected or the quantity field is empty
			} else {
				JOptionPane.showMessageDialog(this, "Select a cake and enter quantity.");
			}
		}

		// Action for the Order Button
		if (e.getSource().equals(btnOrder)) {

			// Get the current date and time
			LocalDateTime currentDateTime = LocalDateTime.now();

			// Format the date and time
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' hh:mm:ss a");
			String formattedDateTime = currentDateTime.format(formatter);

			String invoiceReport = "CUPS & CAKES\n";
			invoiceReport += "Date: " + formattedDateTime + "\n";
			invoiceReport += "--------------------------------------------------------------------\n";
			invoiceReport += String.format("%-35s %-10s %-11s %-11s\n", "Item", "Quantity", "Price", "Total");
			invoiceReport += "--------------------------------------------------------------------\n";

			// Initialize Subtotal
			double subTotal = 0.0;

			// Loop through the cups
			for (int i = 0; i < cup_order_items.size(); i++) {
				// Add label on first iteration
				if (i == 0) {
					invoiceReport += "Cups\n";
				}
				Cup cupi = cup_order_items.get(i);
				double totalPriceCups = cupi.getQuantity() * cupi.getPrice();
				subTotal += totalPriceCups;

				// Append item details to the report
				invoiceReport += String.format("%-35s %-10d $%-10.2f $%-10.2f\n",
						cupi.getSize().substring(0, 1) + " " + cupi.getName(),
						cupi.getQuantity(),
						cupi.getPrice(),
						totalPriceCups);
			}

			// Loop through the cakes
			for (int i = 0; i < cake_order_items.size(); i++) {
				// Add label on first iteration
				if (i == 0) {
					if (cup_order_items.size() > 0) {
						invoiceReport += "\nCakes\n";
					} else {
						invoiceReport += "Cakes\n";
					}
				}
				Cake cakei = cake_order_items.get(i);
				double totalPriceCakes = cakei.getQuantity() * cakei.getPrice();
				subTotal += totalPriceCakes;

				// Append item details to the report
				invoiceReport += String.format("%-35s %-10d $%-10.2f $%-10.2f\n",
						cakei.getSize().substring(0, 1) + " " + cakei.getName(),
						cakei.getQuantity(),
						cakei.getPrice(),
						totalPriceCakes);
			}

			// Format subtotal, tax and total amount
			invoiceReport += "--------------------------------------------------------------------\n";
			invoiceReport += String.format("%52s %5s $%-10.2f\n",
					"SUBTOTAL", " ", subTotal);
			invoiceReport += String.format("%52s %5s $%-10.2f\n",
					"TAX(5%)", " ", subTotal * 0.05);
			invoiceReport += "\n";
			invoiceReport += String.format("%52s %5s $%-10.2f\n",
					"TOTAL AMOUNT", " ", subTotal * 1.05);
			invoiceReport += "--------------------------------------------------------------------\n";

			// Create a JTextArea with monospaced font for alignment
			JTextArea textArea = new JTextArea(invoiceReport);
			textArea.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12));
			textArea.setEditable(false);
			textArea.setBackground(null); // Make it blend with the dialog

			// Show the message dialog with the JTextArea
			JOptionPane.showMessageDialog(null, textArea, "Invoice", JOptionPane.INFORMATION_MESSAGE);

			// Display a dialog to choose payment method
			String[] options = { "Cash", "Card", "Cancel" };
			String totalAmount = String.format("%.2f", subTotal * 1.05);
			int choice = JOptionPane.showOptionDialog(this,
					"Total Amount: $" + totalAmount + "\nPlease confirm your payment method",
					"Payment Confirmation",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					null,
					options,
					options[0]); // Default option, here "Cash"

			// Check the user's choice
			if (choice == 0) {
				JOptionPane.showMessageDialog(this, "Please pay $" + totalAmount + " at the counter.");
			} else if (choice == 1) {
				JOptionPane.showMessageDialog(this,
						"A total amount of $" + totalAmount + " will be deducted from your account.");
			} else {
				return;
			}

			// Thank you message
			JOptionPane.showMessageDialog(this,
					"Thank you for your purchase!\nWe look forward to seeing you again at Cups & Cakes.",
					"Thank You",
					JOptionPane.INFORMATION_MESSAGE);

			// Clear variables
			txtArea.setText(null);
			btnOrder.setEnabled(false);
			cup_order_items.clear();
			cake_order_items.clear();
		}

	}


	// Constructor for the App
	public App() {
		setLayout(null); // No layout manager
		setSize(600, 900);
		setLocationRelativeTo(null);
		setTitle("Cups & Cakes");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initElements();
		btnAddCupProd.addActionListener(this);
		btnAddCakeProd.addActionListener(this);
		btnOrder.addActionListener(this);
		setResizable(false);
		setVisible(true);
		// Gets the frame object and sets bg colour
		getContentPane().setBackground(greenColor);
	}

	// Methods for the Cups Section
	public void initCupSize() {
		// Label for Cup Sizes
		lblSize = new JLabel("Select the cup size:");
		lblSize.setSize(250, 50);
		lblSize.setLocation(100, 10);
		lblSize.setForeground(Color.white);
		add(lblSize);

		String[] cupSizes = { "Small", "Medium", "Large" };
		cmbCupSize = new JComboBox<String>(cupSizes);
		cmbCupSize.setSelectedIndex(0); // Default size is Small
		cmbCupSize.setSize(100, 25);
		cmbCupSize.setLocation(100, 50);
		cmbCupSize.setForeground(greenColor);
		add(cmbCupSize);

	}

	public void initCupProduct() {
		// Label for Cup Products
		lblProduct = new JLabel("Select the drink you want to order:");
		lblProduct.setSize(500, 50);
		lblProduct.setLocation(100, 75);
		lblProduct.setForeground(Color.white);
		add(lblProduct);
		// For Size Label
		lblPrice = new JLabel();
		lblPrice.setText("<html>S<br>M<br>L</html>");
		lblPrice.setSize(75, 75);
		lblPrice.setLocation(105, 120);
		lblPrice.setForeground(Color.white);
		add(lblPrice);

		// For Coffee
		rdbCoffee = new JRadioButton("Coffee");
		rdbCoffee.setSize(75, 25);
		rdbCoffee.setLocation(100, 110);
		rdbCoffee.setBackground(greenColor);
		add(rdbCoffee);
		// For Coffee Price
		lblPrice = new JLabel();
		lblPrice.setText("<html>$2.00<br>$2.50<br>$3.00</html>");
		lblPrice.setSize(75, 75);
		lblPrice.setLocation(125, 120);
		lblPrice.setForeground(Color.white);
		add(lblPrice);

		// For Juice
		rdbJuice = new JRadioButton("Juice");
		rdbJuice.setSize(75, 25);
		rdbJuice.setLocation(175, 110);
		rdbJuice.setBackground(greenColor);
		add(rdbJuice);
		// For Juice Price
		lblPrice = new JLabel();
		lblPrice.setText("<html>$3.00<br>$4.00<br>$5.00</html>");
		lblPrice.setSize(75, 75);
		lblPrice.setLocation(200, 120);
		lblPrice.setForeground(Color.white);
		add(lblPrice);

		// For Tea
		rdbTea = new JRadioButton("Tea");
		rdbTea.setSize(70, 25);
		rdbTea.setLocation(250, 110);
		rdbTea.setBackground(greenColor);
		add(rdbTea);
		// For Tea Price
		lblPrice = new JLabel();
		lblPrice.setText("<html>$2.50<br>$3.50<br>$4.50</html>");
		lblPrice.setSize(75, 75);
		lblPrice.setLocation(275, 120);
		lblPrice.setForeground(Color.white);
		add(lblPrice);

		// For Water
		rdbWater = new JRadioButton("Sparkling Water");
		rdbWater.setSize(125, 25);
		rdbWater.setLocation(320, 110);
		rdbWater.setBackground(greenColor);
		add(rdbWater);
		// For Water Price
		lblPrice = new JLabel();
		lblPrice.setText("<html>$2.50<br>$3.50<br>$4.50</html>");
		lblPrice.setSize(75, 75);
		lblPrice.setLocation(345, 120);
		lblPrice.setForeground(Color.white);
		add(lblPrice);

		// For Cup Products Group
		btnGrpCupProd = new ButtonGroup();
		btnGrpCupProd.add(rdbCoffee);
		btnGrpCupProd.add(rdbJuice);
		btnGrpCupProd.add(rdbTea);
		btnGrpCupProd.add(rdbWater);

		// Color for UI
		rdbWater.setForeground(Color.white);
		rdbTea.setForeground(Color.white);
		rdbCoffee.setForeground(Color.white);
		rdbJuice.setForeground(Color.white);

	}

	public void initCupQuantity() {
		// Label for Cup Quantity
		lblQuantity = new JLabel("Input the quantity you want to order:");
		lblQuantity.setSize(500, 50);
		lblQuantity.setLocation(100, 185);
		lblQuantity.setForeground(Color.white);
		add(lblQuantity);

		// Text field for Cup Quantity
		txtQtyCups = new JTextField();
		txtQtyCups.setSize(100, 25);
		txtQtyCups.setLocation(100, 225);
		txtQtyCups.setForeground(greenColor);
		add(txtQtyCups);
	}

	public void initAddCupProd() {
		// Add Item Button for Cups
		btnAddCupProd = new JButton("Add Item(s)");
		btnAddCupProd.setSize(120, 40);
		btnAddCupProd.setLocation(350, 240);
		btnAddCupProd.setForeground(greenColor);
		add(btnAddCupProd);
	}

	// Methods for the Cakes Section
	public void initCakeSize() {
		// Label for Cake Sizes
		lblSize = new JLabel("Select the cake size:");
		lblSize.setSize(250, 50);
		lblSize.setLocation(100, 310);
		lblSize.setForeground(Color.white);
		add(lblSize);

		String[] cakeSizes = { "Slice", "Whole" };
		cmbCakeSize = new JComboBox<String>(cakeSizes);
		cmbCakeSize.setSelectedIndex(0); // Default size is Slice
		cmbCakeSize.setSize(100, 25);
		cmbCakeSize.setLocation(100, 350);
		cmbCakeSize.setForeground(greenColor);

		add(cmbCakeSize);
	}

	public void initCakeProduct() {
		// Label for Cake Products
		lblProduct = new JLabel("Select the cake you want to order:");
		lblProduct.setSize(500, 50);
		lblProduct.setLocation(100, 375);
		lblProduct.setForeground(Color.WHITE);
		add(lblProduct);
		// For Size Label
		lblPrice = new JLabel();
		lblPrice.setText("<html>S<br>W</html>");
		lblPrice.setSize(75, 50);
		lblPrice.setLocation(105, 425);
		lblPrice.setForeground(Color.WHITE);
		add(lblPrice);

		// For Chocolate Chip
		rdbChoco = new JRadioButton("Chocolate Chip");
		rdbChoco.setSize(120, 25);
		rdbChoco.setLocation(100, 410);
		rdbChoco.setForeground(Color.WHITE);
		rdbChoco.setBackground(greenColor);
		add(rdbChoco);
		// For Chocolate Chip Price
		lblPrice = new JLabel();
		lblPrice.setText("<html>$4.50<br>$35.00</html>");
		lblPrice.setSize(75, 50);
		lblPrice.setLocation(125, 425);
		lblPrice.setForeground(Color.WHITE);
		add(lblPrice);

		// For Oreo
		rdbOreo = new JRadioButton("Oreo cake");
		rdbOreo.setSize(100, 25);
		rdbOreo.setLocation(220, 410);
		rdbOreo.setForeground(Color.WHITE);
		rdbOreo.setBackground(greenColor);
		add(rdbOreo);
		// For Oreo Price
		lblPrice = new JLabel();
		lblPrice.setText("<html>$5.00<br>$40.00</html>");
		lblPrice.setSize(75, 50);
		lblPrice.setLocation(245, 425);
		lblPrice.setForeground(Color.WHITE);
		add(lblPrice);

		// For Strawberry Cheesecake
		rdbStraw = new JRadioButton("Strawberry Cheesecake");
		rdbStraw.setSize(170, 25);
		rdbStraw.setLocation(320, 410);
		rdbStraw.setForeground(Color.WHITE);
		rdbStraw.setBackground(greenColor);
		add(rdbStraw);
		// For Strawberry Cheesecake Price
		lblPrice = new JLabel();
		lblPrice.setText("<html>$6.00<br>$45.00</html>");
		lblPrice.setSize(75, 50);
		lblPrice.setLocation(350, 425);
		lblPrice.setForeground(Color.WHITE);
		add(lblPrice);

		// For Cake Products Group
		btnGrpCakeProd = new ButtonGroup();
		btnGrpCakeProd.add(rdbChoco);
		btnGrpCakeProd.add(rdbStraw);
		btnGrpCakeProd.add(rdbOreo);
	}

	public void initCakeQuantity() {
		// Label for Cake Quantity
		lblQuantity = new JLabel("Input the quantity you want to order:");
		lblQuantity.setSize(500, 50);
		lblQuantity.setLocation(100, 485);
		lblQuantity.setForeground(Color.WHITE);
		add(lblQuantity);

		// Text field for Cake Quantity
		txtQtyCakes = new JTextField();
		txtQtyCakes.setSize(100, 25);
		txtQtyCakes.setLocation(100, 525);
		txtQtyCakes.setForeground(greenColor);
		add(txtQtyCakes);
	}

	public void initAddCakeProd() {
		// Add Item Button for Cakes
		btnAddCakeProd = new JButton("Add Item(s)");
		btnAddCakeProd.setSize(120, 40);
		btnAddCakeProd.setLocation(350, 540);
		btnAddCakeProd.setForeground(greenColor);
		add(btnAddCakeProd);
	}

	// Method for initializing the GUI elements
	public void initElements() {
		// Methods for the Cups Section
		initCupSize();
		initCupProduct();
		initCupQuantity();
		initAddCupProd();

		// Add Horizontal Line Separator
		sepHorLine = new JSeparator();
		sepHorLine.setBounds(100, 300, 375, 2);
		add(sepHorLine);

		// Methods for the Cakes Section
		initCakeSize();
		initCakeProduct();
		initCakeQuantity();
		initAddCakeProd();

		// Add Horizontal Line Separator
		sepHorLine = new JSeparator();
		sepHorLine.setBounds(100, 600, 375, 2);
		add(sepHorLine);

		// For the Order Items
		lblOrders = new JLabel("Order Items:");
		lblOrders.setSize(500, 100);
		lblOrders.setLocation(100, 585);
		lblOrders.setForeground(Color.white);
		add(lblOrders);

		// Create a solid border with a gray color and 1px width for text area
		borTextArea = BorderFactory.createLineBorder(java.awt.Color.GRAY, 0);

		// Text Area for multi-line text
		txtArea = new JTextArea("");
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setBorder(borTextArea);
		txtArea.setForeground(greenColor);

		// Wrap the JTextArea inside a JScrollPane
		JScrollPane scrollPane = new JScrollPane(txtArea);
		scrollPane.setBounds(100, 650, 300, 100); // Set position and size of the scroll pane
		add(scrollPane);

		// Add Order Button
		btnOrder = new JButton("Order");
		btnOrder.setSize(120, 40);
		btnOrder.setLocation(100, 775);
		btnOrder.setEnabled(false);
		add(btnOrder);

	}

	// Main Function
	public static void main(String[] args) {
		new App();
	}
}
