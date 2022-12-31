import javax.swing.*;
import java.util.stream.IntStream;

public class Ducks extends JDialog {
    private JPanel contentPane;
    private JPanel DucksPanel;
    private JTextField ducks1H;
    private JTextField ducks2A;
    private JTextField ducks2Q;
    private JTextField ducks1B;
    private JTextField ducks2B;
    private JTextField ducks2L;
    private JButton Button1;
    private JTextField textField1;
    private JButton Button2;
    private JTextField textField2;
    private JTextField ducks1A;
    private JTextField ducks1L;
    private JTextField ducks1Q;
    private JTextField ducks2H;
    private JTextField ducks2H1;
    private JScrollPane DucksScrollPane;

    public Ducks() {
        try {
            setContentPane(contentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button1.addActionListener(e -> {
            //обработка значений воздуховода со смещением в одной плоскости
            try {
                String[] values = getDuck1Values();
                double width = Double.parseDouble((values[0]))/1000;
                double height = Double.parseDouble((values[1]))/1000;
                double length = Double.parseDouble((values[2]))/1000;
                double shift = Double.parseDouble((values[3]))/1000;
                double quantity = Double.parseDouble((values[4]));

                double duck_one_square = ( 2*(width * Math.sqrt(length * length + shift * shift)+ height * length + 0.1 * (width + height)) );
                double result = duck_one_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(DucksPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        Button2.addActionListener(e -> {
            //обработка значений воздуховода со смещением в двух плоскостях
            try {
                String[] values = getDuck2Values();
                double width = Double.parseDouble((values[0]))/1000;
                double height = Double.parseDouble((values[1]))/1000;
                double length = Double.parseDouble((values[2]))/1000;
                double shift = Double.parseDouble((values[3]))/1000;
                double shift1 = Double.parseDouble((values[4]))/1000;
                double quantity = Double.parseDouble((values[5]));

                double duck_two_square = ( 2 * ( height * Math.sqrt( length * length + shift1 * shift1 ) + width * Math.sqrt( length * length + shift * shift) + 0.1 * ( width + height ) ) );
                double result = duck_two_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(DucksPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //Сбор значений утки 1 (смещение в одной плоскости)
    public String[] getDuck1Values() {
        String[] values = new String[5];
        values[0] = ducks1A.getText();
        values[1] = ducks1B.getText();
        values[2] = ducks1L.getText();
        values[3] = ducks1H.getText();
        values[4] = ducks1Q.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений утки 2 (смещением в 2х плоскостях)
    public String[] getDuck2Values() {
        String[] values = new String[6];
        values[0] = ducks2A.getText();
        values[1] = ducks2B.getText();
        values[2] = ducks2L.getText();
        values[3] = ducks2H.getText();
        values[4] = ducks2H1.getText();
        values[5] = ducks2Q.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }
}

