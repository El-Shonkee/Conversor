package View;

import Utils.*;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class conversorPesos extends JFrame {
    private JComboBox<Object> comboBoxUnidad1;
    private JTextField textFieldPeso1;
    private JTextField textFieldPeso2;
    private JPanel panelPesos;
    private JComboBox<Object> comboBoxUnidad2;
    private JButton aceptarButton;
    private JButton atrasButton;

    public conversorPesos() {
        setContentPane(panelPesos);
        setTitle("Conversor Pesos");
        setSize(500,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        textFieldPeso2.setEditable(false);

        // Leer el archivo JSON y obtener el objeto "conversion_rates"
        try {
            JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader("src/main/java/View/unidadesPesos.JSON")));
            JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");

            ConversorUtils.agregarClavesAComboBox(conversionRates,comboBoxUnidad1,comboBoxUnidad2);

            ActionListener listener = e -> ConversorUtils.actualizarValor(conversionRates,comboBoxUnidad1,comboBoxUnidad2,textFieldPeso1,textFieldPeso2);

            comboBoxUnidad1.addActionListener(listener);
            comboBoxUnidad2.addActionListener(listener);
            aceptarButton.addActionListener(listener);
            textFieldPeso1.addActionListener(listener);

        } catch (IOException e) {
            e.printStackTrace();
        }

        atrasButton.addActionListener(e -> {
            new menu();
            dispose();
        });


    }
}
