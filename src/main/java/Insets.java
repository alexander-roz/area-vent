import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;
import java.util.stream.IntStream;

public class Insets extends JDialog {
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
                double diameter = Double.parseDouble((values[0])) / 1000;
                double length = Double.parseDouble((values[1])) / 1000;
                double quantity = Double.parseDouble((values[2]));

                double incutStraightRoundSquare = (((diameter + 0.02) * Math.PI * length)); //ОК
                double result = incutStraightRoundSquare * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
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
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(InsetsPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button3.addActionListener(e -> {
            //обработка значений круглой воротниковой врезки.
            try {
                String[] values = getRoundCollarValues();
                double diameter = Double.parseDouble((values[0])) / 1000;
                double diameter1 = Double.parseDouble((values[1])) / 1000;
                double length1 = Double.parseDouble((values[2])) / 1000;
                double quantity = Double.parseDouble((values[3]));

                //принимаем, что отбортовка 20 мм. Частные случаи врезок с широким основанием не учитываются.

                double incutCollarRoundSquare = (Math.PI * diameter1 * length1) + (Math.PI * diameter * 0.02);
                double result = incutCollarRoundSquare * quantity;

                String formatresult = String.format("%.2f", result);
                textField3.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(InsetsPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button4.addActionListener(e -> {
            //обработка значений прямоугольной вороткниковой врезки
            try {
                String[] values = getRectangularCollarValues();
                double width = Double.parseDouble((values[0])) / 1000;
                double height = Double.parseDouble((values[1])) / 1000;
                double diameter = Double.parseDouble((values[2])) / 1000;
                double length = Double.parseDouble((values[3])) / 1000;
                double quantity = Double.parseDouble((values[4]));

                //принимем, что отбортовка 20 мм. Частные случаи врезок с широким основанием не учитываются.
                double incutCollarRectangularSquare = 2 * (width + height) * length + (Math.PI * diameter * 0.02);
                double result = incutCollarRectangularSquare * quantity;

                String formatresult = String.format("%.2f", result);
                textField4.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
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
        contentPane.setBorder(BorderFactory.createTitledBorder(null, "Площадь врезки", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, contentPane.getFont()), new Color(-13215616)));
        InsetsScrollPane = new JScrollPane();
        contentPane.add(InsetsScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        InsetsPanel = new JPanel();
        InsetsPanel.setLayout(new GridLayoutManager(20, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        InsetsScrollPane.setViewportView(InsetsPanel);
        insetRoundStraightQ = new JTextField();
        InsetsPanel.add(insetRoundStraightQ, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRectangularStraightA = new JTextField();
        InsetsPanel.add(insetRectangularStraightA, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        insetRectangularStraightQ = new JTextField();
        InsetsPanel.add(insetRectangularStraightQ, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/img/incut_straight_rectangular_correction.png")));
        label1.setText("");
        InsetsPanel.add(label1, new GridConstraints(4, 0, 5, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JTextField textField5 = new JTextField();
        textField5.setEditable(false);
        Font textField5Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField5.getFont());
        if (textField5Font != null) textField5.setFont(textField5Font);
        textField5.setHorizontalAlignment(0);
        textField5.setText("Количество");
        InsetsPanel.add(textField5, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField6 = new JTextField();
        textField6.setEditable(false);
        Font textField6Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField6.getFont());
        if (textField6Font != null) textField6.setFont(textField6Font);
        textField6.setHorizontalAlignment(0);
        textField6.setText("Ширина A, мм");
        InsetsPanel.add(textField6, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField7 = new JTextField();
        textField7.setEditable(false);
        Font textField7Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField7.getFont());
        if (textField7Font != null) textField7.setFont(textField7Font);
        textField7.setHorizontalAlignment(0);
        textField7.setText("Количество");
        InsetsPanel.add(textField7, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField8 = new JTextField();
        textField8.setEditable(false);
        Font textField8Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField8.getFont());
        if (textField8Font != null) textField8.setFont(textField8Font);
        textField8.setHorizontalAlignment(0);
        textField8.setText("Диаметр D, мм");
        InsetsPanel.add(textField8, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        insetRoundCollarD = new JTextField();
        InsetsPanel.add(insetRoundCollarD, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField9 = new JTextField();
        textField9.setEditable(false);
        Font textField9Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField9.getFont());
        if (textField9Font != null) textField9.setFont(textField9Font);
        textField9.setHorizontalAlignment(0);
        textField9.setText("Длина L1, мм");
        InsetsPanel.add(textField9, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        insetRoundCollarL1 = new JTextField();
        InsetsPanel.add(insetRoundCollarL1, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JTextField textField10 = new JTextField();
        textField10.setEditable(false);
        Font textField10Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField10.getFont());
        if (textField10Font != null) textField10.setFont(textField10Font);
        textField10.setHorizontalAlignment(0);
        textField10.setText("Количество");
        InsetsPanel.add(textField10, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRoundCollarQ = new JTextField();
        InsetsPanel.add(insetRoundCollarQ, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setIcon(new ImageIcon(getClass().getResource("/img/incut_collar_round_correction.png")));
        label2.setText("");
        InsetsPanel.add(label2, new GridConstraints(9, 0, 5, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JTextField textField11 = new JTextField();
        textField11.setEditable(false);
        Font textField11Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField11.getFont());
        if (textField11Font != null) textField11.setFont(textField11Font);
        textField11.setHorizontalAlignment(0);
        textField11.setText("Длина L, мм");
        InsetsPanel.add(textField11, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        insetRoundStraightL = new JTextField();
        insetRoundStraightL.setText("");
        InsetsPanel.add(insetRoundStraightL, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        final JTextField textField12 = new JTextField();
        textField12.setEditable(false);
        Font textField12Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField12.getFont());
        if (textField12Font != null) textField12.setFont(textField12Font);
        textField12.setHorizontalAlignment(0);
        textField12.setText("Высота B, мм ");
        InsetsPanel.add(textField12, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRectangularStraightB = new JTextField();
        InsetsPanel.add(insetRectangularStraightB, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField13 = new JTextField();
        textField13.setEditable(false);
        Font textField13Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField13.getFont());
        if (textField13Font != null) textField13.setFont(textField13Font);
        textField13.setHorizontalAlignment(0);
        textField13.setText("Длина L, мм");
        InsetsPanel.add(textField13, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRectangularStraightL = new JTextField();
        InsetsPanel.add(insetRectangularStraightL, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField14 = new JTextField();
        textField14.setEditable(false);
        Font textField14Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField14.getFont());
        if (textField14Font != null) textField14.setFont(textField14Font);
        textField14.setHorizontalAlignment(0);
        textField14.setText("Диаметр d, мм");
        InsetsPanel.add(textField14, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRoundCollarD1 = new JTextField();
        InsetsPanel.add(insetRoundCollarD1, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField15 = new JTextField();
        textField15.setEditable(false);
        Font textField15Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField15.getFont());
        if (textField15Font != null) textField15.setFont(textField15Font);
        textField15.setHorizontalAlignment(0);
        textField15.setText("Количество");
        InsetsPanel.add(textField15, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setIcon(new ImageIcon(getClass().getResource("/img/incut_collar_rectangular_correction.png")));
        label3.setText("");
        InsetsPanel.add(label3, new GridConstraints(14, 0, 6, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JTextField textField16 = new JTextField();
        textField16.setEditable(false);
        Font textField16Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField16.getFont());
        if (textField16Font != null) textField16.setFont(textField16Font);
        textField16.setHorizontalAlignment(0);
        textField16.setText("Длина L1, мм");
        InsetsPanel.add(textField16, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRectangularCollarL1 = new JTextField();
        InsetsPanel.add(insetRectangularCollarL1, new GridConstraints(17, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField17 = new JTextField();
        textField17.setEditable(false);
        Font textField17Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField17.getFont());
        if (textField17Font != null) textField17.setFont(textField17Font);
        textField17.setHorizontalAlignment(0);
        textField17.setText("Ширина A, мм");
        InsetsPanel.add(textField17, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        insetRectangularCollarA = new JTextField();
        InsetsPanel.add(insetRectangularCollarA, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField18 = new JTextField();
        textField18.setEditable(false);
        Font textField18Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField18.getFont());
        if (textField18Font != null) textField18.setFont(textField18Font);
        textField18.setHorizontalAlignment(0);
        textField18.setText("Высота B, мм ");
        InsetsPanel.add(textField18, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRectangularCollarB = new JTextField();
        InsetsPanel.add(insetRectangularCollarB, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField19 = new JTextField();
        textField19.setEditable(false);
        Font textField19Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField19.getFont());
        if (textField19Font != null) textField19.setFont(textField19Font);
        textField19.setHorizontalAlignment(0);
        textField19.setText("Диаметр D, мм");
        InsetsPanel.add(textField19, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRectangularCollarD = new JTextField();
        InsetsPanel.add(insetRectangularCollarD, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        insetRectangularCollarQ = new JTextField();
        InsetsPanel.add(insetRectangularCollarQ, new GridConstraints(18, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button1 = new JButton();
        Button1.setText("Рассчитать");
        InsetsPanel.add(Button1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setEditable(false);
        Font textField1Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        textField1.setHorizontalAlignment(0);
        InsetsPanel.add(textField1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button2 = new JButton();
        Button2.setText("Рассчитать");
        InsetsPanel.add(Button2, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        textField2.setEditable(false);
        Font textField2Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField2.getFont());
        if (textField2Font != null) textField2.setFont(textField2Font);
        textField2.setHorizontalAlignment(0);
        InsetsPanel.add(textField2, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button3 = new JButton();
        Button3.setText("Рассчитать");
        InsetsPanel.add(Button3, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        textField3.setEditable(false);
        Font textField3Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField3.getFont());
        if (textField3Font != null) textField3.setFont(textField3Font);
        textField3.setHorizontalAlignment(0);
        InsetsPanel.add(textField3, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button4 = new JButton();
        Button4.setText("Рассчитать");
        InsetsPanel.add(Button4, new GridConstraints(19, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField4 = new JTextField();
        textField4.setEditable(false);
        Font textField4Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField4.getFont());
        if (textField4Font != null) textField4.setFont(textField4Font);
        textField4.setHorizontalAlignment(0);
        InsetsPanel.add(textField4, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField20 = new JTextField();
        textField20.setEditable(false);
        Font textField20Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField20.getFont());
        if (textField20Font != null) textField20.setFont(textField20Font);
        textField20.setHorizontalAlignment(0);
        textField20.setText("Диаметр D, мм");
        InsetsPanel.add(textField20, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        insetRoundStraightD = new JTextField();
        InsetsPanel.add(insetRoundStraightD, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setIcon(new ImageIcon(getClass().getResource("/img/incut_straight_round_correction.png")));
        label4.setText("");
        InsetsPanel.add(label4, new GridConstraints(0, 0, 4, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

