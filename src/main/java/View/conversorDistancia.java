package View;

import Utils.ConversorUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class conversorDistancia extends JFrame{
    private JComboBox<Object> comboBoxUnidad1;
    private JComboBox<Object> comboBoxUnidad2;
    private JButton aceptarButton;
    private JButton atrasButton;
    private JPanel panelDistancia;
    private JTextField textFieldUnidad1;
    private JTextField textFieldUnidad2;

    public conversorDistancia() {
        setContentPane(panelDistancia);
        setTitle("conversor distancias");
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        textFieldUnidad2.setEditable(false);

        try {
            JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader("src/main/java/View/unidadesDistancia.JSON")));
            JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");

            ConversorUtils.agregarClavesAComboBox(conversionRates,comboBoxUnidad1,comboBoxUnidad2);

            ActionListener listener = e -> ConversorUtils.actualizarValor(conversionRates,comboBoxUnidad1,comboBoxUnidad2,textFieldUnidad1,textFieldUnidad2);

            comboBoxUnidad1.addActionListener(listener);
            comboBoxUnidad2.addActionListener(listener);
            aceptarButton.addActionListener(listener);
            textFieldUnidad1.addActionListener(listener);

        } catch (IOException e) {
            e.printStackTrace();
        }

        atrasButton.addActionListener(e -> {
            new menu();
            dispose();
        });
    }
}
