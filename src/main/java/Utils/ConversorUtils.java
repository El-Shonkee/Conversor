package Utils;

import org.json.JSONObject;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Objects;

public class ConversorUtils {
    public static void actualizarValor(JSONObject conversionRates, JComboBox comboBox1, JComboBox comboBox2, JTextField textField1, JTextField textField2) {
        String tasaConversion1 = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();
        double valor1 = conversionRates.getDouble(tasaConversion1);

        String tasaConversion2 = Objects.requireNonNull(comboBox2.getSelectedItem()).toString();
        double valor2 = conversionRates.getDouble(tasaConversion2);

        double valorUsuario = Double.parseDouble(textField1.getText());

        double resultado = valorUsuario * (valor2 / valor1);

        DecimalFormat decimalFormat = new DecimalFormat("0.000000000000000000");
        String resultadoFormateado = decimalFormat.format(resultado);

        textField2.setText(resultadoFormateado);
    }

    public static void agregarClavesAComboBox(JSONObject conversionRates, JComboBox<Object> comboBox1, JComboBox<Object> comboBox2) {
        Iterator<String> keys = conversionRates.keys();
        while (keys.hasNext()) {
            String unitName = keys.next();
            comboBox1.addItem(unitName);
            comboBox2.addItem(unitName);
        }
    }
}
