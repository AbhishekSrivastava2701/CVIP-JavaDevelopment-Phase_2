import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int randomNumber;
    private int attempts;

    private JTextField guessField;
    private JButton submitButton;
    private JLabel resultLabel;
    private JLabel attemptsLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(450, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initialize();
        setupLayout();
        startNewGame();
    }

    private void initialize() {
        randomNumber = generateRandomNumber();
        attempts = 0;

        guessField = new JTextField(10);
        submitButton = new JButton("Submit Guess");
        resultLabel = new JLabel();
        attemptsLabel = new JLabel();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter your guess:"));
        inputPanel.add(guessField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.add(resultLabel);
        resultPanel.add(attemptsLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1; // Generate a random number between 1 and 100
    }

    private void startNewGame() {
        randomNumber = generateRandomNumber();
        attempts = 0;
        resultLabel.setText("");
        attemptsLabel.setText("");
        submitButton.setEnabled(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == randomNumber) {
                resultLabel.setText("Congratulations! You guessed the correct number.");
                attemptsLabel.setText("Number of attempts: " + attempts);
                submitButton.setEnabled(false);
            } else if (guess < randomNumber) {
                resultLabel.setText("Try a higher number.");
            } else {
                resultLabel.setText("Try a lower number.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter a number.");
        }

        guessField.setText("");
        guessField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
