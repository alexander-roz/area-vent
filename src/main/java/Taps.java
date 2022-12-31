import javax.swing.*;
import java.util.stream.IntStream;

public class Taps extends JDialog {
    private JPanel contentPane;
    private JPanel Tapspanel;
    private JTextField tapSqWidth;
    private JTextField tapSqHeight;
    private JComboBox<String> tapSqDegree;
    private JTextField tapSqQuantity;
    private JTextField tapRSize;
    private JComboBox<String> tapRDegree;
    private JTextField tapRQuantity;
    private JTextField textField1;
    private JTextField textField2;
    private JButton Button1;
    private JButton Button2;
    private JScrollPane TapsScrollPane;

    public Taps() {
        try {
            setContentPane(contentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String[] angles = new String[5];
        angles[0] = "90";
        angles[1] = "60";
        angles[2] = "45";
        angles[3] = "30";
        angles[4] = "15";

        IntStream.range(0, 5).forEach(i -> tapRDegree.addItem(angles[i]));
        IntStream.range(0, 5).forEach(i -> tapSqDegree.addItem(angles[i]));

        Button1.addActionListener(e -> {
            //обработка значений прямоугольного отвода
            try {
                String[] values = getRectangularTap();
                double width = Double.parseDouble((values[0]))/1000;
                double height = Double.parseDouble((values[1]))/1000;
                double angle = Double.parseDouble((values[2]));
                double quantity = Double.parseDouble((values[3]));

                double tap_rectangular_square = ( (width + height)*0.2 + Math.PI * ( ( (0.125 + width) * (0.125 + width) ) - (0.125 * 0.125) ) * angle * 2 / 360 + Math.PI * 0.125 * angle * height / 180 + Math.PI * (0.125 + width) * angle * height / 180);
                double result = tap_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(Tapspanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button2.addActionListener(e -> {
            //обработка значений круглого отвода
            try {
                String[] values = getRoundTapValues();
                double diameter = Double.parseDouble((values[0]));
                double angle = Double.parseDouble((values[1]));
                double quantity = Double.parseDouble((values[2]));
                double elementNumber = (angle == 90 || angle == 60) ? 2 : 0;

                int ratio = switch ((int) angle) {
                    case 90 -> 2;
                    case 60 -> 3;
                    case 45 -> 4;
                    case 30 -> 6;
                    case 15 -> 12;
                    default -> throw new IllegalStateException("Unexpected value: " + (int) angle);
                };

                double neck = (Math.PI / ratio * diameter / 2) / (elementNumber + 2 ) + 15;
                double section = (Math.PI / ratio * diameter) / (elementNumber * 2 + 2);

                double tap_round_square1 = Math.PI * diameter / 1000 * 0.1;
                double tap_round_square2 = (Math.PI * diameter * (2 * (neck + section / 2) + elementNumber * (neck + section))) / 1000000;

                double tap_round_square = (tap_round_square1 + tap_round_square2);

                double result = ( tap_round_square * quantity );

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(Tapspanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //Сбор значений прямоугольного отвода
    public String[] getRectangularTap() {
        String[] values = new String[4];
        values[0] = tapSqWidth.getText();
        values[1] = tapSqHeight.getText();
        values[2] = String.valueOf(tapSqDegree.getSelectedItem());
        values[3] = tapSqQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений круглого отвода
    public String[] getRoundTapValues() {
        String[] values = new String[3];
        values[0] = tapRSize.getText();
        values[1] = String.valueOf(tapRDegree.getSelectedItem());
        values[2] = tapRQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }
}

