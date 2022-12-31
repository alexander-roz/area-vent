import javax.swing.*;
import java.util.stream.IntStream;

public class Transitions extends JDialog {
    private JPanel contentPane;
    private JPanel TransitionPanel;
    private JTextField redSqSqA;
    private JTextField redSqSqB;
    private JTextField redSqSqA1;
    private JTextField redSqSqQuantity;
    private JTextField redSqRD;
    private JTextField redSqRL;
    private JTextField redSqRQuantity;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField redRRD;
    private JTextField redRRL;
    private JTextField redRRQuantity;
    private JTextField textField3;
    private JTextField redSqSqB1;
    private JTextField redSqSqL;
    private JTextField redRRD1;
    private JTextField redSqRA;
    private JTextField redSqRB;
    private JButton Button1;
    private JButton Button2;
    private JButton Button3;
    private JScrollPane TransitionScrollPane;

    public Transitions() {
        try {
            setContentPane(contentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button1.addActionListener(e -> {
            //обработка значений перехода прямоугольный/прямоугольный
            try {
                String[] values = getSqSqValues();
                double width = Double.parseDouble((values[0]))/1000;
                double height = Double.parseDouble((values[1]))/1000;
                double width1 = Double.parseDouble((values[2]))/1000;
                double height1 = Double.parseDouble((values[3]))/1000;
                double length = Double.parseDouble((values[4]))/1000;
                double quantity = Double.parseDouble((values[5]));

                double ratioWidth = Math.sqrt( ( (height - height1) / 2 )*( (height - height1) / 2 ) + (length * length) );
                double ratioHeight = Math.sqrt( ( (width - width1) / 2 )*( (width - width1) / 2 ) + (length * length) );
                double transition_rectangular_rectangular_square = (2 * (width + width1) / 2 * ratioWidth + 2 * (height + height1) / 2 * ratioHeight + (2*width1 + 2*height1 + 2*width + 2*height)*0.05);

                double result = transition_rectangular_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TransitionPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button2.addActionListener(e -> {
            //обработка значений перехода прямоугольный/круглый
            try {
                String[] values = getSqRValues();
                double diameter = Double.parseDouble((values[0]))/1000;
                double width = Double.parseDouble((values[1]))/1000;
                double height = Double.parseDouble((values[2]))/1000;
                double length = Double.parseDouble((values[3]))/1000;
                double quantity = Double.parseDouble((values[4]));

                double transition_round_rectangular_square = ( ((width + height)*2.8 + Math.PI*diameter) / 2 * length );
                double result = transition_round_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TransitionPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button3.addActionListener(e -> {
            //обработка значений перехода круглый/круглый
            try {
                String[] values = getRRValues();
                double diameter = Double.parseDouble((values[0]))/1000;
                double diameter1 = Double.parseDouble((values[1]))/1000;
                double length = Double.parseDouble((values[2]))/1000;
                double quantity = Double.parseDouble((values[3]));

                double transition_round_round_square = ( Math.PI * Math.sqrt( length * length + ( (diameter - diameter1) / 2 )*( (diameter - diameter1) / 2 ) ) * ( diameter / 2 + diameter1 / 2) + Math.PI * diameter *0.05 + Math.PI * diameter1 * 0.05);
                double result = transition_round_round_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField3.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TransitionPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //Сбор значений перехода прямоугольный/прямоугольный
    public String[] getSqSqValues() {
        String[] values = new String[6];
        values[0] = redSqSqA.getText();
        values[1] = redSqSqB.getText();
        values[2] = redSqSqA1.getText();
        values[3] = redSqSqB1.getText();
        values[4] = redSqSqL.getText();
        values[5] = redSqSqQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений перехода прямоугольный/круглый
    public String[] getSqRValues() {
        String[] values = new String[5];
        values[0] = redSqRD.getText();
        values[1] = redSqRA.getText();
        values[2] = redSqRB.getText();
        values[3] = redSqRL.getText();
        values[4] = redSqRQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений перехода круглый/круглый
    public String[] getRRValues() {
        String[] values = new String[4];
        values[0] = redRRD.getText();
        values[1] = redRRD1.getText();
        values[2] = redRRL.getText();
        values[3] = redRRQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }
}

