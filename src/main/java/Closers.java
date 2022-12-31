import javax.swing.*;
import java.util.stream.IntStream;

public class Closers extends JDialog {
    private JPanel contentPane;
    private JPanel CloserPanel;
    private JTextField closerSqWidth;
    private JTextField closerSqHeight;
    private JTextField closerSqQuantity;
    private JTextField closerRSize;
    private JTextField closerRQuantity;
    private JButton Button1;
    private JTextField textField1;
    private JButton Button2;
    private JTextField textField2;
    private JScrollPane CloserScrollPane;

    public Closers() {
        try {
            setContentPane(contentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button1.addActionListener(e -> {
            //Расчёт значений для прямоугольного сечения
            try {
                String[] values = getSQValues();
                double width = Double.parseDouble((values[0]))/1000;
                double height = Double.parseDouble((values[1]))/1000;
                double quantity = Double.parseDouble((values[2]));

                double cap_rectangular_square = ( width * height + (width + height) * 0.05 );
                double result = cap_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(CloserPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        Button2.addActionListener(e -> {
            //Расчёт значений для круглого сечения
            try {
                String[] values = getRValues();
                double diameter = Double.parseDouble((values[0]))/1000;
                double quantity = Double.parseDouble((values[1]));

                double cap_round_square = ( Math.PI * diameter * diameter / 4 + (Math.PI * diameter)*0.05 );
                double result = cap_round_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(CloserPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    //Сбор значений прямоугольного сечения
    public String[] getSQValues() {
        String[] values = new String[3];
        values[0] = closerSqWidth.getText();
        values[1] = closerSqHeight.getText();
        values[2] = closerSqQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений круглого сечения
    public String[] getRValues() {
        String[] values = new String[2];
        values[0] = closerRSize.getText();
        values[1] = closerRQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }
}

