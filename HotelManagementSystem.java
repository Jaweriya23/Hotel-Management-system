import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// 🌿 Constants Class to store all configuration values
class Constants {
    // Colors
    public static final Color PRIMARY_COLOR = Color.decode("#3b82f6");
    public static final Color SECONDARY_COLOR = Color.decode("#fef9c3");
    public static final Color ACCENT_COLOR = Color.decode("#16a34a");
    public static final Color DANGER_COLOR = Color.decode("#dc2626");
    public static final Color BACKGROUND_HOME = Color.decode("#dbeafe");
    public static final Color BACKGROUND_ROOM = Color.decode("#dcfce7");
    public static final Color BACKGROUND_SERVICE = Color.decode("#f0f9ff");
    public static final Color BACKGROUND_PAYMENT = Color.decode("#fef2f2");
    public static final Color BACKGROUND_BILL = Color.decode("#faf5ff");
    public static final Color BRAND_PURPLE = Color.decode("#7c3aed");

    // Room Prices
    public static final int SINGLE_DELUXE = 8000;
    public static final int DOUBLE_DELUXE = 12000;
    public static final int SUITE = 20000;
    public static final int EXECUTIVE_SUITE = 30000;

    // Service Prices
    public static final int WIFI = 500;
    public static final int BREAKFAST = 1500;
    public static final int SPA = 3000;
    public static final int PICKUP = 2500;
    public static final int LAUNDRY = 1000;
}

public class HotelManagementSystem extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField nameField, emailField, phoneField, nightsField;
    private JComboBox<String> roomTypeBox, paymentBox;
    private JCheckBox wifiBox, breakfastBox, spaBox, pickupBox, laundryBox;
    private JLabel billLabel;

    private ArrayList<String> selectedServices = new ArrayList<>();

    public HotelManagementSystem() {
        setTitle("🌿 GreenLeaf Hotel Booking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        addHomePage();
        addRegistrationPage();
        addRoomSelectionPage();
        addServicePage();
        addPaymentPage();
        addBillPage();

        add(mainPanel);
        setVisible(true);
    }

    private void addHomePage() {
        JPanel homePanel = createPanel(Constants.BACKGROUND_HOME);
        homePanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("🏨 GreenLeaf Grand Hotel 🌿", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setBorder(new EmptyBorder(50, 0, 30, 0));

        JButton startBtn = new JButton("Start Booking");
        styleButton(startBtn, Constants.PRIMARY_COLOR, Color.WHITE, 18);
        startBtn.setPreferredSize(new Dimension(200, 60));
        startBtn.addActionListener(e -> cardLayout.show(mainPanel, "registration"));

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.add(startBtn);

        homePanel.add(title, BorderLayout.NORTH);
        homePanel.add(center, BorderLayout.CENTER);

        mainPanel.add(homePanel, "home");
    }

    private void addRegistrationPage() {
        JPanel regPanel = createPanel(Constants.SECONDARY_COLOR);
        regPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("📝 Customer Registration", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        regPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        regPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        regPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        regPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        regPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        regPanel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(15);
        regPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton nextBtn = new JButton("Confirm & Continue →");
        styleButton(nextBtn, Constants.ACCENT_COLOR, Color.WHITE, 16);
        nextBtn.addActionListener(e -> {
            if (validateRegistration()) {
                cardLayout.show(mainPanel, "room");
            }
        });
        regPanel.add(nextBtn, gbc);

        mainPanel.add(regPanel, "registration");
    }

    private void addRoomSelectionPage() {
        JPanel roomPanel = createPanel(Constants.BACKGROUND_ROOM);
        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("🏨 Select Your Room", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        roomPanel.add(title);

        roomTypeBox = new JComboBox<>(new String[]{
                "Single Deluxe - " + Constants.SINGLE_DELUXE,
                "Double Deluxe - " + Constants.DOUBLE_DELUXE,
                "Suite - " + Constants.SUITE,
                "Executive Suite - " + Constants.EXECUTIVE_SUITE
        });
        styleCombo(roomTypeBox);

        nightsField = new JTextField(10);
        nightsField.setFont(new Font("SansSerif", Font.PLAIN, 15));

        JPanel form = new JPanel(new GridLayout(2, 2, 15, 15));
        form.setOpaque(false);
        form.add(new JLabel("Room Type:"));
        form.add(roomTypeBox);
        form.add(new JLabel("No. of Nights:"));
        form.add(nightsField);

        form.setBorder(new EmptyBorder(20, 60, 20, 60));
        roomPanel.add(form);

        JButton nextBtn = new JButton("Continue →");
        styleButton(nextBtn, Constants.ACCENT_COLOR, Color.WHITE, 16);
        nextBtn.addActionListener(e -> cardLayout.show(mainPanel, "services"));

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(nextBtn);
        roomPanel.add(btnPanel);

        mainPanel.add(roomPanel, "room");
    }

    private void addServicePage() {
        JPanel servicePanel = createPanel(Constants.BACKGROUND_SERVICE);
        servicePanel.setLayout(new BoxLayout(servicePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("💎 Select Additional Services", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(20, 0, 15, 0));
        servicePanel.add(title);

        wifiBox = new JCheckBox("Wi-Fi (" + Constants.WIFI + ")");
        breakfastBox = new JCheckBox("Breakfast (" + Constants.BREAKFAST + ")");
        spaBox = new JCheckBox("Spa (" + Constants.SPA + ")");
        pickupBox = new JCheckBox("Airport Pickup (" + Constants.PICKUP + ")");
        laundryBox = new JCheckBox("Laundry Service (" + Constants.LAUNDRY + ")");

        JPanel serviceGrid = new JPanel(new GridLayout(3, 2, 15, 15));
        serviceGrid.setOpaque(false);
        serviceGrid.add(wifiBox);
        serviceGrid.add(breakfastBox);
        serviceGrid.add(spaBox);
        serviceGrid.add(pickupBox);
        serviceGrid.add(laundryBox);

        serviceGrid.setBorder(new EmptyBorder(30, 80, 30, 80));
        servicePanel.add(serviceGrid);

        JButton nextBtn = new JButton("Continue →");
        styleButton(nextBtn, Constants.ACCENT_COLOR, Color.WHITE, 16);
        nextBtn.addActionListener(e -> cardLayout.show(mainPanel, "payment"));
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(nextBtn);
        servicePanel.add(btnPanel);

        mainPanel.add(servicePanel, "services");
    }

    private void addPaymentPage() {
        JPanel paymentPanel = createPanel(Constants.BACKGROUND_PAYMENT);
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("💳 Select Payment Method", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(30, 0, 20, 0));
        paymentPanel.add(title);

        paymentBox = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "Cash", "Bank Transfer"});
        styleCombo(paymentBox);

        JPanel comboPanel = new JPanel();
        comboPanel.setOpaque(false);
        comboPanel.add(paymentBox);
        paymentPanel.add(comboPanel);

        JButton billBtn = new JButton("Generate Bill");
        styleButton(billBtn, Constants.DANGER_COLOR, Color.WHITE, 16);
        billBtn.addActionListener(e -> {
            generateBill();
            cardLayout.show(mainPanel, "bill");
        });

        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(billBtn);
        paymentPanel.add(btnPanel);

        mainPanel.add(paymentPanel, "payment");
    }

    private void addBillPage() {
        JPanel billPanel = createPanel(Constants.BACKGROUND_BILL);
        billPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("🧾 Final Bill", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBorder(new EmptyBorder(20, 0, 10, 0));

        billLabel = new JLabel("", SwingConstants.CENTER);
        billLabel.setFont(new Font("Monospaced", Font.PLAIN, 15));
        billLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JButton homeBtn = new JButton("← Back to Home");
        styleButton(homeBtn, Constants.BRAND_PURPLE, Color.WHITE, 15);
        homeBtn.addActionListener(e -> cardLayout.show(mainPanel, "home"));

        billPanel.add(title, BorderLayout.NORTH);
        billPanel.add(billLabel, BorderLayout.CENTER);
        billPanel.add(homeBtn, BorderLayout.SOUTH);

        mainPanel.add(billPanel, "bill");
    }

    private void generateBill() {
        String roomType = (String) roomTypeBox.getSelectedItem();
        int roomPrice = Integer.parseInt(roomType.split(" - ")[1].replaceAll("[^0-9]", ""));
        int nights = Integer.parseInt(nightsField.getText());
        int totalRoomCost = roomPrice * nights;

        selectedServices.clear();
        if (wifiBox.isSelected()) selectedServices.add("Wi-Fi (" + Constants.WIFI + ")");
        if (breakfastBox.isSelected()) selectedServices.add("Breakfast (" + Constants.BREAKFAST + ")");
        if (spaBox.isSelected()) selectedServices.add("Spa (" + Constants.SPA + ")");
        if (pickupBox.isSelected()) selectedServices.add("Airport Pickup (" + Constants.PICKUP + ")");
        if (laundryBox.isSelected()) selectedServices.add("Laundry Service (" + Constants.LAUNDRY + ")");

        int serviceTotal = selectedServices.stream().mapToInt(service ->
                Integer.parseInt(service.split(" \\(")[1].replace(")", ""))).sum();
        int grandTotal = totalRoomCost + serviceTotal;

        billLabel.setText("<html><b>Customer:</b> " + nameField.getText() +
                "<br><b>Room:</b> " + roomType +
                "<br><b>No. of Nights:</b> " + nights +
                "<br><b>Additional Services:</b><br>" + String.join("<br>", selectedServices) +
                "<br><b>Total Cost:</b> ₹" + grandTotal +
                "<br><b>Payment Method:</b> " + paymentBox.getSelectedItem() + "</html>");
    }

    private boolean validateRegistration() {
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private JPanel createPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        return panel;
    }

    private void styleButton(JButton button, Color bgColor, Color textColor, int fontSize) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        button.setFocusPainted(false);
    }

    private void styleCombo(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
        comboBox.setPreferredSize(new Dimension(200, 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HotelManagementSystem::new);
    }
}
