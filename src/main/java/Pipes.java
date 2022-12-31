import javax.swing.*;
import java.util.stream.IntStream;

public class Pipes extends JFrame {

    @Override
    public JPanel getContentPane() {
        return contentPane;
    }

    private JPanel contentPane;
    private JPanel PipePanel;
    private JTextField pipeSqWidth;
    private JTextField pipeSqHeight;
    private JTextField pipeSqLength;
    private JTextField pipeSqQuantity;
    private JTextField pipeRSize;
    private JTextField pipeRLength;
    private JTextField pipeRQuantity;
    private JTextField textField1;
    private JTextField textField2;
    private JButton Button1;
    private JButton Button2;
    private JScrollPane PipeScrollPane;

    public Pipes() {
        try {
            setContentPane(contentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Button1.addActionListener(e -> {
            //Расчет площади прямоугольных воздуховодов
            try {

                String[] values = getSQValues();
                double width = Double.parseDouble((values[0]));
                double height = Double.parseDouble((values[1]));
                double length = Double.parseDouble((values[2]));
                double quantity = Double.parseDouble((values[3]));
                double result = (((width + height + width + height) / 1000)) * length * quantity;
                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(PipePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button2.addActionListener(e -> {
            //Расчет площади круглых воздуховодов
            try {
                String[] values = getRValues();
                double diameter = Double.parseDouble((values[0]));
                double length = Double.parseDouble(values[1]);
                double quantity = Double.parseDouble((values[2]));
                double result = Math.PI * ((diameter / 1000)) * length * quantity;
                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            }
            catch (NumberFormatException exception)
            {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(PipePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //Сбор значений прямоугольного сечения
    public String[] getSQValues() throws NumberFormatException{
        String[] values = new String[4];
        values[0] = pipeSqWidth.getText();
        values[1] = pipeSqHeight.getText();
        values[2] = pipeSqLength.getText();
        values[3] = pipeSqQuantity.getText();
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

        String[] values = new String[3];
        values[0]=pipeRSize.getText();
        values[1] = pipeRLength.getText();
        values[2] = pipeRQuantity.getText();

        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }
}


