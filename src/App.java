import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class App extends JFrame implements ActionListener {
    // Common Variables
	private JLabel              lblSize, lblProduct, lblQuantity, lblOrders;
	private JTextField          txtQuantity;
    private JSeparator          sepHorLine;
    private JTextArea           txtArea;
    private Border              borTextArea;
	private JButton             btnOrder;

    // Cups Variables
	private JComboBox<String>   cmbCupSize;
	private ButtonGroup         btnGrpCupProd;
	private JRadioButton        rdbCoffee, rdbJuice, rdbTea, rdbWater;
	private JButton             btnAddCupProd;

    // Cakes Variables
	private JComboBox<String>   cmbCakeSize;
	private ButtonGroup         btnGrpCakeProd;
	private JRadioButton        rdbChoco, rdbStraw, rdbOreo;
	private JButton             btnAddCakeProd;


    // Method from ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // Constructor for the App 
    public App() {
		setLayout(null);
		setSize(600, 900);
		setLocationRelativeTo(null);
		setTitle("Cups & Cakes");
		initElements();
		// btnAdd.addActionListener(this); 
		// btnOrder.addActionListener(this);
		setVisible(true);
    }

    // Methods for the Cups Section 
    public void initCupSize() {
        // Label for Cup Sizes
		lblSize = new JLabel("Select the cup size:");
		lblSize.setSize(250, 50);
		lblSize.setLocation(100, 10);
		add(lblSize);
        
		String[] cupSizes = {"Small", "Medium", "Large"};
		cmbCupSize = new JComboBox<String>(cupSizes);
		cmbCupSize.setSelectedIndex(0); // Default size is Small
		cmbCupSize.setSize(100, 25);
		cmbCupSize.setLocation(100, 50);
		add(cmbCupSize);
    }

    public void initCupProduct() {
        // Label for Cup Products
		lblProduct = new JLabel("Select the drink you want to order:");
		lblProduct.setSize(500, 50);
		lblProduct.setLocation(100, 75);
		add(lblProduct);
        
		// For Coffee
		rdbCoffee = new JRadioButton("Coffee");
		rdbCoffee.setSize(75, 50);
		rdbCoffee.setLocation(100, 110);
		add(rdbCoffee);

		// For Juice
		rdbJuice = new JRadioButton("Juice");
		rdbJuice.setSize(75, 50);
		rdbJuice.setLocation(175, 110);
		add(rdbJuice);
		
		// For Tea
		rdbTea = new JRadioButton("Tea");
		rdbTea.setSize(70, 50);
		rdbTea.setLocation(250, 110);
		add(rdbTea);

		// For Water
		rdbWater = new JRadioButton("Water");
		rdbWater.setSize(75, 50);
		rdbWater.setLocation(320, 110);
		add(rdbWater);
		
        // For Cup Products Group
        btnGrpCupProd = new ButtonGroup();
		btnGrpCupProd.add(rdbCoffee);
		btnGrpCupProd.add(rdbJuice);
		btnGrpCupProd.add(rdbTea);
		btnGrpCupProd.add(rdbWater);
    }

    public void initCupQuantity() {
        // Label for Cup Quantity
        lblQuantity = new JLabel("Input the cup quantity you want to order:");
		lblQuantity.setSize(500, 50);
		lblQuantity.setLocation(100, 145);
		add(lblQuantity);
		
        // Text field for Cup Quantity
		txtQuantity = new JTextField();
		txtQuantity.setSize(100, 25);
		txtQuantity.setLocation(100, 185);
		add(txtQuantity);
    }

    public void initAddCupProd() {
        // Add Item Button for Cups
		btnAddCupProd = new JButton("Add Item(s)");
		btnAddCupProd.setSize(120, 40);
		btnAddCupProd.setLocation(100, 230);
		add(btnAddCupProd);
    }

    // Methods for the Cakes Section
    public void initCakeSize() {
        // Label for Cake Sizes
		lblSize = new JLabel("Select the cake size:");
		lblSize.setSize(250, 50);
		lblSize.setLocation(100, 310);
		add(lblSize);
        
		String[] cakeSizes = {"Slice", "Whole"};
		cmbCakeSize = new JComboBox<String>(cakeSizes);
		cmbCakeSize.setSelectedIndex(0); // Default size is Slice
		cmbCakeSize.setSize(100, 25);
		cmbCakeSize.setLocation(100, 350);
		add(cmbCakeSize);
    }

    public void initCakeProduct() {
        // Label for Cake Products
		lblProduct = new JLabel("Select the cake you want to order:");
		lblProduct.setSize(500, 50);
		lblProduct.setLocation(100, 375);
		add(lblProduct);
        
		// For Chocolate Chip
		rdbChoco = new JRadioButton("Chocolate Chip");
		rdbChoco.setSize(120, 50);
		rdbChoco.setLocation(100, 410);
		add(rdbChoco);

		// For Strawberry Cheesecake
		rdbStraw = new JRadioButton("Strawberry Cheesecake");
		rdbStraw.setSize(170, 50);
		rdbStraw.setLocation(220, 410);
		add(rdbStraw);
		
		// For Oreo
		rdbOreo = new JRadioButton("Oreo");
		rdbOreo.setSize(100, 50);
		rdbOreo.setLocation(390, 410);
		add(rdbOreo);
		
        // For Cake Products Group
        btnGrpCakeProd = new ButtonGroup();
		btnGrpCakeProd.add(rdbChoco);
		btnGrpCakeProd.add(rdbStraw);
		btnGrpCakeProd.add(rdbOreo);
    }

    public void initCakeQuantity() {
        // Label for Cake Quantity
        lblQuantity = new JLabel("Input the cake quantity you want to order:");
		lblQuantity.setSize(500, 50);
		lblQuantity.setLocation(100, 445);
		add(lblQuantity);
		
        // Text field for Cake Quantity
		txtQuantity = new JTextField();
		txtQuantity.setSize(100, 25);
		txtQuantity.setLocation(100, 485);
		add(txtQuantity);
    }

    public void initAddCakeProd() {
        // Add Item Button for Cakes
		btnAddCakeProd = new JButton("Add Item(s)");
		btnAddCakeProd.setSize(120, 40);
		btnAddCakeProd.setLocation(100, 530);
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
		add(lblOrders);

        // Create a solid border with a gray color and 1px width for text area
        borTextArea = BorderFactory.createLineBorder(java.awt.Color.GRAY, 1);

        // Text Area for multi-line text
        txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setBorder(borTextArea);
        txtArea.setSize(300, 100);
		txtArea.setLocation(100, 650);
		add(txtArea);

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
