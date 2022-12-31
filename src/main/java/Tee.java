import javax.swing.*;
import java.util.stream.IntStream;

public class Tee extends JDialog {
    private JPanel contentPane;
    private JPanel TeePanel;
    private JTextField teeSqSqA;
    private JTextField teeSqSqB;
    private JTextField teeSqSqA1;
    private JTextField teeSqSqQuantity;
    private JTextField teeRRD;
    private JTextField teeRRL1;
    private JTextField teeRRQuantity;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField teeSqRdD;
    private JTextField teeSqRL1;
    private JTextField teeSqRQuantity;
    private JTextField textField3;
    private JTextField teeSqSqB1;
    private JTextField teeSqSqL;
    private JTextField teeSqRL;
    private JTextField teeRRD1;
    private JTextField teeRRL;
    private JTextField teeSqSqL1;
    private JTextField teeSqRA;
    private JTextField teeSqRB;
    private JTextField teeRSqA;
    private JTextField teeRSqQuantity;
    private JTextField teeRSqL1;
    private JTextField teeRSqB;
    private JTextField teeRSqL;
    private JTextField teeRSqD;
    private JButton Button1;
    private JButton Button2;
    private JButton Button3;
    private JButton Button4;
    private JTextField textField4;
    private JScrollPane TeeScrollPane;

    public Tee() {
        try {
            setContentPane(contentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Button1.addActionListener(e -> {
            //обработка значений тройник прямоугольный/прямоугольный
            try {
                String[] values = getSqSqValues();
                double width = Double.parseDouble((values[0]))/1000;
                double height = Double.parseDouble((values[1]))/1000;
                double width1 = Double.parseDouble((values[2]))/1000;
                double height1 = Double.parseDouble((values[3]))/1000;
                double length = Double.parseDouble((values[4]))/1000;
                double length1 = Double.parseDouble((values[5]))/1000;
                double quantity = Double.parseDouble((values[6]));

                double tee_output_rectangular_rectangular_square = ( (width + height)*2 * length + (width1 + height1)*2 * length1 - width1 * height1 );
                double result = tee_output_rectangular_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TeePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button2.addActionListener(e -> {
            //обработка значений тройник круг/круг
            try {
                String[] values = getRRValues();
                double diameter = Double.parseDouble((values[0]))/1000;
                double diameter1 = Double.parseDouble((values[1]))/1000;
                double length = Double.parseDouble((values[2]))/1000;
                double length1 = Double.parseDouble((values[3]))/1000;
                double quantity = Double.parseDouble((values[4]));

                double tee_output_round_round_square = ( Math.PI * diameter * length + Math.PI * diameter1 * length1 );
                double result = tee_output_round_round_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TeePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button3.addActionListener(e -> {
            //обработка значений тройник транзит круг, прямоугольная врезка
            try {
                String[] values = getSqRValues();
                double diameter = Double.parseDouble((values[0]))/1000;
                double width = Double.parseDouble((values[1]))/1000;
                double height = Double.parseDouble((values[2]))/1000;
                double length = Double.parseDouble((values[3]))/1000;
                double length1 = Double.parseDouble((values[4]))/1000;
                double quantity = Double.parseDouble((values[5]));

                double tee_incut_square = (Math.PI * diameter * length + (width + height) *2 * length1);
                double result = tee_incut_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField3.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TeePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        Button4.addActionListener(e -> {
            //обработка значений тройник транзит прямоугольный, врезка круг
            try {
                String[] values = getRSqValues();
                double width = Double.parseDouble((values[0]))/1000;
                double height = Double.parseDouble((values[1]))/1000;
                double diameter = Double.parseDouble((values[2]))/1000;
                double length = Double.parseDouble((values[3]))/1000;
                double length1 = Double.parseDouble((values[4]))/1000;
                double quantity = Double.parseDouble((values[5]));

                double tee_output_rectangular_round_square = ( (width + height) * 2 * length + Math.PI * diameter * length1 - Math.PI * diameter * diameter / 4 );
                double result = tee_output_rectangular_round_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField4.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TeePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //Сбор значений тройника прямоугольный/прямоугольный
    public String[] getSqSqValues() {
        String[] values = new String[7];
        values[0] = teeSqSqA.getText();
        values[1] = teeSqSqB.getText();
        values[2] = teeSqSqA1.getText();
        values[3] = teeSqSqB1.getText();
        values[4] = teeSqSqL.getText();
        values[5] = teeSqSqL1.getText();
        values[6] = teeSqSqQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений тройника круг/круг
    public String[] getRRValues() {
        String[] values = new String[5];
        values[0] = teeRRD.getText();
        values[1] = teeRRD1.getText();
        values[2] = teeRRL.getText();
        values[3] = teeRRL1.getText();
        values[4] = teeRRQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений тройника прямоугольник/круг
    public String[] getSqRValues() {
        String[] values = new String[6];
        values[0] = teeSqRdD.getText();
        values[1] = teeSqRA.getText();
        values[2] = teeSqRB.getText();
        values[3] = teeSqRL.getText();
        values[4] = teeSqRL1.getText();
        values[5] = teeSqRQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений тройника круг/прямоугольник
    public String[] getRSqValues() {
        String[] values = new String[6];
        values[0] = teeRSqA.getText();
        values[1] = teeRSqB.getText();
        values[2] = teeRSqD.getText();
        values[3] = teeRSqL.getText();
        values[4] = teeRSqL1.getText();
        values[5] = teeRSqQuantity.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }
}

