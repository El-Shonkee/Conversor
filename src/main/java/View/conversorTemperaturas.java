package View;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class conversorTemperaturas extends  JFrame {
    private JComboBox<String> comboBoxUnidad1;
    private JComboBox<String> comboBoxUnidad2;
    private JTextField textFieldUnidad1;
    private JTextField textFieldUnidad2;
    private JButton aceptarButton;
    private JButton atrasButton;
    private JPanel panelTemperaturas;

    public conversorTemperaturas(){
        setContentPane(panelTemperaturas);
        setTitle("conversor temperatura");
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        textFieldUnidad2.setEditable(false);

        String[] nombres = {"Celsius","Kelvin","Fahrenheit"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nombres);
        comboBoxUnidad1.setModel(model);
        DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>(nombres);
        comboBoxUnidad2.setModel(model2);

        ActionListener listener = e -> actualizarValor();
        aceptarButton.addActionListener(listener);
        comboBoxUnidad1.addActionListener(listener);
        comboBoxUnidad2.addActionListener(listener);
        textFieldUnidad1.addActionListener(listener);

        atrasButton.addActionListener(e -> {
            new menu();
            dispose();
        });
    }

    private void actualizarValor() {
        String selectedUnit1 = Objects.requireNonNull(comboBoxUnidad1.getSelectedItem()).toString();
        String selectedUnit2 = Objects.requireNonNull(comboBoxUnidad2.getSelectedItem()).toString();

        double valor = Double.parseDouble(textFieldUnidad1.getText());

        double temperaturaBase = getTemperatureInCelsius(valor, selectedUnit1);
        double resultado = getTemperatureFromCelsius(temperaturaBase, selectedUnit2);

        textFieldUnidad2.setText(String.valueOf(resultado));
    }

    private double getTemperatureInCelsius(double valor, String selectedUnit) {
        switch (selectedUnit) {
            case "Celsius":
                return valor;
            case "Kelvin":
                return valor - 273.15;
            case "Fahrenheit":
                return (valor - 32) * 5 / 9;
            default:
                return valor; // Si no se reconoce la unidad, devolver el valor original
        }
    }

    private double getTemperatureFromCelsius(double celsiusValue, String selectedUnit) {
        switch (selectedUnit) {
            case "Celsius":
                return celsiusValue;
            case "Kelvin":
                return celsiusValue + 273.15;
            case "Fahrenheit":
                return celsiusValue * 9 / 5 + 32;
            default:
                return celsiusValue; // Si no se reconoce la unidad, devolver el valor original
        }
    }

}
