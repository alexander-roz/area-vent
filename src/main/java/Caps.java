import javax.swing.*;
import java.util.stream.IntStream;

    public class Caps extends JDialog {
        private JPanel contentPane;
        private JPanel CapsPanel;
        private JTextField capIslandA;
        private JTextField capIslandB;
        private JTextField capIslandA1;
        private JTextField capIslandQuantity;
        private JTextField capWallA;
        private JTextField capWallC;
        private JTextField capWallQuantity;
        private JTextField capIslandB1;
        private JTextField capIslandH;
        private JTextField capWallB;
        private JTextField capWallH;
        private JButton Button1;
        private JTextField textField1;
        private JButton Button2;
        private JTextField textField2;
        private JScrollPane CapsScrollPane;

        public Caps() {
            try {
                setContentPane(contentPane);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Button1.addActionListener(e -> {
                //Обработка значений островного зонта
                try {
                    String[] values = getIslandValues();
                    double width = (Double.parseDouble((values[0])))/1000;
                    double height = (Double.parseDouble((values[1])))/1000;
                    double width1 = (Double.parseDouble((values[2])))/1000;
                    double height1 = (Double.parseDouble((values[3])))/1000;
                    double shift = (Double.parseDouble((values[4])))/1000;
                    double quantity = Double.parseDouble((values[5]));

                    double SA = Math.sqrt( ( ( height - height1) / 2 ) * ( ( height - height1 ) / 2 ) + ( shift * shift ) );
                    double SB = Math.sqrt( ( width - width1 ) / 2 * ( width - width1 ) / 2 + ( shift * shift ) );
                    double canopy_sharp_square = (2 * ( width + width1 ) / 2 * SA + 2 * ( height + height1 ) / 2 * SB + width1 * height1);
                    double result = canopy_sharp_square * quantity;

                    String formatresult = String.format("%.2f", result);
                    textField1.setText(String.valueOf(formatresult));
                }
                catch (NumberFormatException exception)
                {
                    String message = Message.getMessage();
                    JOptionPane.showMessageDialog(CapsPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            });

            Button2.addActionListener(e -> {
                //Обработка значений пристенного зонта
                try {
                    String[] values = getWallValues();
                    double width = (Double.parseDouble((values[0])))/1000;
                    double height = (Double.parseDouble((values[1])))/1000;
                    double board = (Double.parseDouble((values[2])))/1000;
                    double length = (Double.parseDouble((values[3])))/1000;
                    double quantity = Double.parseDouble((values[4]));

                    double canopy_blunt_square = ( height * ( width + board ) + length * Math.sqrt( ( width - board) * ( width - board) + ( height * height ) ) + length * height + length * board );
                    double result = canopy_blunt_square * quantity;

                    String formatresult = String.format("%.2f", result);
                    textField2.setText(String.valueOf(formatresult));
                }
                catch (NumberFormatException exception)
                {
                    String message = Message.getMessage();
                    JOptionPane.showMessageDialog(CapsPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        //Сбор значений островного зонта
        public String[] getIslandValues() {
            String[] values = new String[6];
            values[0] = capIslandA.getText();
            values[1] = capIslandB.getText();
            values[2] = capIslandA1.getText();
            values[3] = capIslandB1.getText();
            values[4] = capIslandH.getText();
            values[5] = capIslandQuantity.getText();
            //фильтр вводимых значений для парсинга в double
            String[] correctedvalues = values.clone();
            IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
                String somestring = correctedvalues[i];
                correctedvalues[i] = somestring.replace(',', '.');
            });
            return correctedvalues;
        }

        //Сбор значений пристенного зонта
        public String[] getWallValues() {
            String[] values = new String[5];
            values[0] = capWallA.getText();
            values[1] = capWallB.getText();
            values[2] = capWallH.getText();
            values[3] = capWallC.getText();
            values[4] = capWallQuantity.getText();
            //фильтр вводимых значений для парсинга в double
            String[] correctedvalues = values.clone();
            IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
                String somestring = correctedvalues[i];
                correctedvalues[i] = somestring.replace(',', '.');
            });
            return correctedvalues;
        }
    }

