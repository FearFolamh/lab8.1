import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationApp extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JButton calculateButton;
    private JComboBox<String> formulaSelector;
    private JTextField inputField2;

    public CalculationApp() {
        setTitle("Calculation Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Верхняя панель с полем ввода и выбором формулы
        JPanel topPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel inputLabel = new JLabel("Введите X:");
        inputField = new JTextField();
        inputField2 = new JTextField();
        JLabel inputLabel2 = new JLabel("Введите Y:");
        JLabel inputLabel3 = new JLabel("Введите N:");
        JLabel inputLabel4 = new JLabel("Введите R:");

        JLabel formulaLabel = new JLabel("Выберите формулу:");
        formulaSelector = new JComboBox<>(new String[]{"Формула 1", "Формула 2"});

        topPanel.add(inputLabel);
        topPanel.add(inputField);
        topPanel.add(inputLabel2);
        topPanel.add(inputField2);
        topPanel.add(formulaLabel);
        topPanel.add(formulaSelector);

        formulaSelector.addActionListener(e -> {
            if (formulaSelector.getSelectedIndex() == 0) { // Вариант 1
                topPanel.removeAll();
                topPanel.add(inputLabel);
                topPanel.add(inputField);
                topPanel.add(inputLabel2);
                topPanel.add(inputField2);
                topPanel.add(formulaLabel);
                topPanel.add(formulaSelector);
                topPanel.revalidate();
                topPanel.repaint();
            } else { // Вариант 2
                topPanel.removeAll();
                topPanel.add(inputLabel3);
                topPanel.add(inputField);
                topPanel.add(inputLabel4);
                topPanel.add(inputField2);
                topPanel.add(formulaLabel);
                topPanel.add(formulaSelector);
                topPanel.revalidate();
                topPanel.repaint();
            }
        });

        // Центральная область для отображения результатов
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Нижняя панель с кнопкой расчета
        calculateButton = new JButton("Рассчитать");
        calculateButton.addActionListener(new CalculateListener());

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(calculateButton, BorderLayout.SOUTH);
    }

    // Внутренний класс для обработки событий
    private class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int X = Integer.parseInt(inputField.getText());
                int Y = Integer.parseInt(inputField2.getText());
                String formula = (String) formulaSelector.getSelectedItem();
                double result = 0.0;

                if ("Формула 1".equals(formula)) {
                    result = calculateFormula1(X,Y);
                } else if ("Формула 2".equals(formula)) {
                    result = calculateFormula2(X);
                }

                resultArea.append("Результат: " + result + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(CalculationApp.this,
                        "Введите корректное число!", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
            }
        }

        private double calculateFormula1(int X, int Y) {
            double sum = 1;
            for (int i = 1; i <= X; i++) {
                sum -= Math.pow(X,i) / 2;
            }
            return sum;
        }

        private double calculateFormula2(int n) {
            double sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i * i;
            }
            return sum;
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculationApp app = new CalculationApp();
            app.setVisible(true);
        });
    }
}
