package View;

import javax.swing.*;


public class menu extends JFrame{
    private JComboBox<String> selectorConversor;
    private JButton aceptarButton;
    private JButton cancelarButton;
    private JPanel menu;

    public menu(){
        setContentPane(menu);
        setTitle("menu");
        setSize(400,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        selectorConversor.addItem("Conversor divisas");
        selectorConversor.addItem("Conversor pesos");
        selectorConversor.addItem("Conversor distancias");
        selectorConversor.addItem("Conversor temperaturas");

        aceptarButton.addActionListener(e -> {

            String opcionSeleccionada = (String) selectorConversor.getSelectedItem();

            assert opcionSeleccionada != null;
            switch (opcionSeleccionada) {
                case "Conversor divisas":
                    new conversorDivisas();
                    break;
                case "Conversor pesos":
                    new conversorPesos();
                    break;
                case "Conversor distancias":
                    new conversorDistancia();
                    break;
                case "Conversor temperaturas":
                    new conversorTemperaturas();
                    break;
                // Puedes agregar más casos aquí para futuras opciones del menú
                default:
                    break;
            }

            dispose();

        });

        cancelarButton.addActionListener(e -> System.exit(0));
    }


}
