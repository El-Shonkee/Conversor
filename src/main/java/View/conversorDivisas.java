package View;

import Utils.ConversorUtils;
import org.json.JSONObject;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class conversorDivisas extends JFrame {
    private JPanel panelDivisas;
    private JComboBox<Object> comboBoxMoneda1;
    private JComboBox<Object> comboBoxMoneda2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton aceptarButton;
    private JButton atrasButton;

    public conversorDivisas() {
        setContentPane(panelDivisas);
        setTitle("conversor Divisas");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        textField2.setEditable(false);

        try {
            // URL de la API para obtener el valor del dólar
            URL url = new URL("https://v6.exchangerate-api.com/v6/787166402da0f9e5133efe40/latest/USD");

            // Abrir conexión HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configurar el método de solicitud
            conn.setRequestMethod("GET");

            // Leer la respuesta de la API
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonString = response.toString();

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");

            ConversorUtils.agregarClavesAComboBox(conversionRates,comboBoxMoneda1,comboBoxMoneda2);

            ActionListener listener = e -> ConversorUtils.actualizarValor(conversionRates,comboBoxMoneda1,comboBoxMoneda2,textField1,textField2);

            comboBoxMoneda1.addActionListener(listener);
            comboBoxMoneda2.addActionListener(listener);
            textField1.addActionListener(listener);
            aceptarButton.addActionListener(listener);

        } catch (IOException e) {
            e.printStackTrace();
        }

        atrasButton.addActionListener(e -> {
            new menu();
            dispose();
        });



    }
}
