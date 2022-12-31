import javax.swing.*;
import java.util.stream.IntStream;

public class Insets extends JDialog{
    private JPanel contentPane;
    private JPanel InsetsPanel;
    private JTextField insetRoundStraightD;
    private JTextField insetRoundStraightQ;
    private JTextField insetRectangularStraightA;
    private JTextField insetRectangularStraightQ;
    private JTextField insetRoundCollarD;
    private JTextField insetRoundCollarL1;
    private JTextField insetRoundCollarQ;
    private JTextField insetRoundStraightL;
    private JTextField insetRoundCollarL;
    private JTextField insetRectangularStraightB;
    private JTextField insetRectangularStraightL;
    private JTextField insetRoundCollarD1;
    private JTextField insetRectangularCollarL1;
    private JTextField insetRectangularCollarA;
    private JTextField insetRectangularCollarB;
    private JTextField insetRectangularCollarD;
    private JTextField insetRectangularCollarQ;
    private JButton Button1;
    private JTextField textField1;
    private JButton Button2;
    private JTextField textField2;
    private JButton Button3;
    private JTextField textField3;
    private JButton Button4;
    private JTextField textField4;
    private JScrollPane InsetsScrollPane;

    public Insets() {
        try {
            setContentPane(contentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button1.addActionListener(e -> {
            //обработка значений прямой круглой врезки
            try {
                String[] values = getRoundStraightValues();
                double diameter = Double.parseDouble((values[0]))/1000;
                double length = Double.parseDouble((values[1]))/1000;
                double quantity = Double.parseDouble((values[2]));

                double incutStraightRoundSquare = ( ((diameter + 0.02 ) * Math.PI * length) ); //ОК
                double result = incutStraightRoundSquare * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(InsetsPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button2.addActionListener(e -> {
            //обработка значений прямоугольной прямой врезки
            try {
                String[] values = getRectangularStraightValues();
                double width = Double.parseDouble((values[0])) / 1000;
                double height = Double.parseDouble(values[1]) / 1000;
                double length = Double.parseDouble(values[2]) / 1000;
                double quantity = Double.parseDouble(values[3]);

                //принимаем, что отбортовка 20 мм
                double result = (width * length * 2 + height * length * 2 + (((width + 0.04) * 0.02) * 2) + (((height + 0.04) * 0.02) * 2)) * quantity; // ОК

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(InsetsPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button3.addActionListener(e -> {
            //обработка значений круглой воротниковой врезки.
            try {
                String[] values = getRoundCollarValues();
                double diameter = Double.parseDouble((values[0]))/1000;
                double diameter1 = Double.parseDouble((values[1]))/1000;
                double length1 = Double.parseDouble((values[2]))/1000;
                double quantity = Double.parseDouble((values[3]));

                //принимаем, что отбортовка 20 мм. Частные случаи врезок с широким основанием не учитываются.

                double incutCollarRoundSquare = (Math.PI * diameter1 * length1) + (Math.PI * diameter * 0.02);
                double result = incutCollarRoundSquare * quantity;

                String formatresult = String.format("%.2f", result);
                textField3.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(InsetsPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button4.addActionListener(e -> {
            //обработка значений прямоугольной вороткниковой врезки
            try {
                String[] values = getRectangularCollarValues();
                double width = Double.parseDouble((values[0]))/1000;
                double height = Double.parseDouble((values[1]))/1000;
                double diameter = Double.parseDouble((values[2]))/1000;
                double length = Double.parseDouble((values[3]))/1000;
                double quantity = Double.parseDouble((values[4]));

                //принимем, что отбортовка 20 мм. Частные случаи врезок с широким основанием не учитываются.
                double incutCollarRectangularSquare = 2 * (width + height) * length + (Math.PI * diameter * 0.02);
                double result = incutCollarRectangularSquare * quantity;

                String formatresult = String.format("%.2f", result);
                textField4.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(InsetsPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //Сбор значений прямой круглой врезки
    public String[] getRoundStraightValues() {
        String[] values = new String[3];
        values[0] = insetRoundStraightD.getText();
        values[1] = insetRoundStraightL.getText();
        values[2] = insetRoundStraightQ.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений прямоугольной прямой врезки
    public String[] getRectangularStraightValues() {
        String[] values = new String[4];
        values[0] = insetRectangularStraightA.getText();
        values[1] = insetRectangularStraightB.getText();
        values[2] = insetRectangularStraightL.getText();
        values[3] = insetRectangularStraightQ.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений воротниковой круглой врезки
    public String[] getRoundCollarValues() {
        String[] values = new String[4];
        values[0] = insetRoundCollarD.getText();
        values[1] = insetRoundCollarD1.getText();
        values[2] = insetRoundCollarL1.getText();
        values[3] = insetRoundCollarQ.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений воротниковой прямоугольной врезки
    public String[] getRectangularCollarValues() {
        String[] values = new String[5];
        values[0] = insetRectangularCollarA.getText();
        values[1] = insetRectangularCollarB.getText();
        values[2] = insetRectangularCollarD.getText();
        values[3] = insetRectangularCollarL1.getText();
        values[4] = insetRectangularCollarQ.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }
}

