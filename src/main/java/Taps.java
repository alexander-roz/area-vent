import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;
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
                double width = Double.parseDouble((values[0])) / 1000;
                double height = Double.parseDouble((values[1])) / 1000;
                double angle = Double.parseDouble((values[2]));
                double quantity = Double.parseDouble((values[3]));

                double tap_rectangular_square = ((width + height) * 0.2 + Math.PI * (((0.125 + width) * (0.125 + width)) - (0.125 * 0.125)) * angle * 2 / 360 + Math.PI * 0.125 * angle * height / 180 + Math.PI * (0.125 + width) * angle * height / 180);
                double result = tap_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
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

                double neck = (Math.PI / ratio * diameter / 2) / (elementNumber + 2) + 15;
                double section = (Math.PI / ratio * diameter) / (elementNumber * 2 + 2);

                double tap_round_square1 = Math.PI * diameter / 1000 * 0.1;
                double tap_round_square2 = (Math.PI * diameter * (2 * (neck + section / 2) + elementNumber * (neck + section))) / 1000000;

                double tap_round_square = (tap_round_square1 + tap_round_square2);

                double result = (tap_round_square * quantity);

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 1, new java.awt.Insets(10, 10, 10, 10), -1, -1));
        contentPane.setBorder(BorderFactory.createTitledBorder(null, "Площадь отводов", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, contentPane.getFont()), new Color(-13215616)));
        TapsScrollPane = new JScrollPane();
        contentPane.add(TapsScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        Tapspanel = new JPanel();
        Tapspanel.setLayout(new GridLayoutManager(9, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        TapsScrollPane.setViewportView(Tapspanel);
        tapSqWidth = new JTextField();
        Tapspanel.add(tapSqWidth, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tapSqHeight = new JTextField();
        Tapspanel.add(tapSqHeight, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tapSqQuantity = new JTextField();
        Tapspanel.add(tapSqQuantity, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tapRSize = new JTextField();
        Tapspanel.add(tapRSize, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        tapRDegree = new JComboBox();
        Tapspanel.add(tapRDegree, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        tapRQuantity = new JTextField();
        Tapspanel.add(tapRQuantity, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/img/tap_round_correction.png")));
        label1.setText("");
        Tapspanel.add(label1, new GridConstraints(5, 0, 4, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JTextField textField3 = new JTextField();
        textField3.setEditable(false);
        Font textField3Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField3.getFont());
        if (textField3Font != null) textField3.setFont(textField3Font);
        textField3.setHorizontalAlignment(0);
        textField3.setText("Ширина A, мм");
        Tapspanel.add(textField3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField4 = new JTextField();
        textField4.setEditable(false);
        Font textField4Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField4.getFont());
        if (textField4Font != null) textField4.setFont(textField4Font);
        textField4.setHorizontalAlignment(0);
        textField4.setText("Высота B, мм ");
        Tapspanel.add(textField4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField5 = new JTextField();
        textField5.setEditable(false);
        Font textField5Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField5.getFont());
        if (textField5Font != null) textField5.setFont(textField5Font);
        textField5.setHorizontalAlignment(0);
        textField5.setText("Угол");
        Tapspanel.add(textField5, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField6 = new JTextField();
        textField6.setEditable(false);
        Font textField6Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField6.getFont());
        if (textField6Font != null) textField6.setFont(textField6Font);
        textField6.setHorizontalAlignment(0);
        textField6.setText("Количество");
        Tapspanel.add(textField6, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField7 = new JTextField();
        textField7.setEditable(false);
        Font textField7Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField7.getFont());
        if (textField7Font != null) textField7.setFont(textField7Font);
        textField7.setHorizontalAlignment(0);
        textField7.setText("Диаметр D, мм");
        Tapspanel.add(textField7, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField8 = new JTextField();
        textField8.setEditable(false);
        Font textField8Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField8.getFont());
        if (textField8Font != null) textField8.setFont(textField8Font);
        textField8.setHorizontalAlignment(0);
        textField8.setText("Угол");
        Tapspanel.add(textField8, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JTextField textField9 = new JTextField();
        textField9.setEditable(false);
        Font textField9Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField9.getFont());
        if (textField9Font != null) textField9.setFont(textField9Font);
        textField9.setHorizontalAlignment(0);
        textField9.setText("Количество");
        Tapspanel.add(textField9, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setIcon(new ImageIcon(getClass().getResource("/img/tap_rectangular_correction.png")));
        label2.setText("");
        Tapspanel.add(label2, new GridConstraints(0, 0, 5, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Button1 = new JButton();
        Button1.setText("Рассчитать");
        Tapspanel.add(Button1, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setEditable(false);
        Font textField1Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        textField1.setHorizontalAlignment(0);
        Tapspanel.add(textField1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button2 = new JButton();
        Button2.setText("Рассчитать");
        Tapspanel.add(Button2, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        textField2.setEditable(false);
        Font textField2Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField2.getFont());
        if (textField2Font != null) textField2.setFont(textField2Font);
        textField2.setHorizontalAlignment(0);
        Tapspanel.add(textField2, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tapSqDegree = new JComboBox();
        Tapspanel.add(tapSqDegree, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}

