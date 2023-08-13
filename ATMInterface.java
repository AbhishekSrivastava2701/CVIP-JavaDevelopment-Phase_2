import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterface extends JFrame {
    private JLabel accountLabel;
    private JLabel balanceLabel;
    private JTextField accountField;
    private JTextField balanceField;
    private JButton depositButton;
    private JButton withdrawButton;

    private double currentBalance = 0.0;

    public ATMInterface() {

        balanceField = new JTextField(15);
        balanceField.setEditable(false);
        balanceField.setText("₹ " + currentBalance);

        setTitle("ATM Interface");
        setSize(600, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        accountLabel = new JLabel("Account:");
        balanceLabel = new JLabel("Balance:");
        accountField = new JTextField(15);
        balanceField = new JTextField(15);
        balanceField.setEditable(false);

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        ImageIcon depositIcon = new ImageIcon("deposit.png");
        ImageIcon withdrawIcon = new ImageIcon("money-withdrawal.png");

        Image depositImage = depositIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image withdrawImage = withdrawIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        depositButton.setIcon(new ImageIcon(depositImage));
        withdrawButton.setIcon(new ImageIcon(withdrawImage));

        Font buttonFont = depositButton.getFont().deriveFont(Font.BOLD, 15);
        depositButton.setFont(buttonFont);
        withdrawButton.setFont(buttonFont);

        depositButton.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawButton.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new GridLayout(4, 2));
        add(accountLabel);
        add(accountField);
        add(balanceLabel);
        add(balanceField);
        add(depositButton);
        add(withdrawButton);
        // ActionListener for Deposit button

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountField.getText();
                if (accountNumber.length() != 13 || accountNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Account number must be exactly 13 digits long and not empty.");
                    accountField.requestFocus();
                    return; // Exit the deposit action if account validation fails
                }

                String depositAmountStr = JOptionPane.showInputDialog("Enter deposit amount:");
                try {
                    double depositAmount = Double.parseDouble(depositAmountStr);
                    if (depositAmount > 0) {
                        currentBalance += depositAmount;
                        balanceField.setText("₹ " + currentBalance);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a positive value.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input.");
                }
            }
        });

        // ActionListener for Withdraw button

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String withdrawAmountStr = JOptionPane.showInputDialog("Enter withdraw amount:");
                try {
                    double withdrawAmount = Double.parseDouble(withdrawAmountStr);
                    if (withdrawAmount <= currentBalance) {

                        currentBalance -= withdrawAmount;
                        balanceField.setText("₹ " + currentBalance);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input.");
                }
            }
        });

        accountField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                String accountNumber = accountField.getText();
                if (accountNumber.length() != 13 || accountNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Account number must be exactly 13 digits long and not empty.");
                    accountField.requestFocus();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface().setVisible(true);
            }
        });
    }
}
