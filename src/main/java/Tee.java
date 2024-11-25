import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;
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
                double width = Double.parseDouble((values[0])) / 1000;
                double height = Double.parseDouble((values[1])) / 1000;
                double width1 = Double.parseDouble((values[2])) / 1000;
                double height1 = Double.parseDouble((values[3])) / 1000;
                double length = Double.parseDouble((values[4])) / 1000;
                double length1 = Double.parseDouble((values[5])) / 1000;
                double quantity = Double.parseDouble((values[6]));

                double tee_output_rectangular_rectangular_square = ((width + height) * 2 * length + (width1 + height1) * 2 * length1 - width1 * height1);
                double result = tee_output_rectangular_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TeePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button2.addActionListener(e -> {
            //обработка значений тройник круг/круг
            try {
                String[] values = getRRValues();
                double diameter = Double.parseDouble((values[0])) / 1000;
                double diameter1 = Double.parseDouble((values[1])) / 1000;
                double length = Double.parseDouble((values[2])) / 1000;
                double length1 = Double.parseDouble((values[3])) / 1000;
                double quantity = Double.parseDouble((values[4]));

                double tee_output_round_round_square = (Math.PI * diameter * length + Math.PI * diameter1 * length1);
                double result = tee_output_round_round_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TeePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button3.addActionListener(e -> {
            //обработка значений тройник транзит круг, прямоугольная врезка
            try {
                String[] values = getSqRValues();
                double diameter = Double.parseDouble((values[0])) / 1000;
                double width = Double.parseDouble((values[1])) / 1000;
                double height = Double.parseDouble((values[2])) / 1000;
                double length = Double.parseDouble((values[3])) / 1000;
                double length1 = Double.parseDouble((values[4])) / 1000;
                double quantity = Double.parseDouble((values[5]));

                double tee_incut_square = (Math.PI * diameter * length + (width + height) * 2 * length1);
                double result = tee_incut_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField3.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TeePanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        Button4.addActionListener(e -> {
            //обработка значений тройник транзит прямоугольный, врезка круг
            try {
                String[] values = getRSqValues();
                double width = Double.parseDouble((values[0])) / 1000;
                double height = Double.parseDouble((values[1])) / 1000;
                double diameter = Double.parseDouble((values[2])) / 1000;
                double length = Double.parseDouble((values[3])) / 1000;
                double length1 = Double.parseDouble((values[4])) / 1000;
                double quantity = Double.parseDouble((values[5]));

                double tee_output_rectangular_round_square = ((width + height) * 2 * length + Math.PI * diameter * length1 - Math.PI * diameter * diameter / 4);
                double result = tee_output_rectangular_round_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField4.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
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
        contentPane.setBorder(BorderFactory.createTitledBorder(null, "Площадь тройников", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, contentPane.getFont()), new Color(-13215616)));
        TeeScrollPane = new JScrollPane();
        contentPane.add(TeeScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        TeePanel = new JPanel();
        TeePanel.setLayout(new GridLayoutManager(28, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        TeeScrollPane.setViewportView(TeePanel);
        teeSqSqA = new JTextField();
        TeePanel.add(teeSqSqA, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeSqSqB = new JTextField();
        TeePanel.add(teeSqSqB, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeSqSqA1 = new JTextField();
        TeePanel.add(teeSqSqA1, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        teeSqSqQuantity = new JTextField();
        TeePanel.add(teeSqSqQuantity, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        teeRRD = new JTextField();
        TeePanel.add(teeRRD, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        teeRRL1 = new JTextField();
        TeePanel.add(teeRRL1, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        teeRRQuantity = new JTextField();
        TeePanel.add(teeRRQuantity, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField5 = new JTextField();
        textField5.setEditable(false);
        Font textField5Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField5.getFont());
        if (textField5Font != null) textField5.setFont(textField5Font);
        textField5.setHorizontalAlignment(0);
        textField5.setText("Высота B, мм ");
        TeePanel.add(textField5, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField6 = new JTextField();
        textField6.setEditable(false);
        Font textField6Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField6.getFont());
        if (textField6Font != null) textField6.setFont(textField6Font);
        textField6.setHorizontalAlignment(0);
        textField6.setText("Ширина А1, мм");
        TeePanel.add(textField6, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField7 = new JTextField();
        textField7.setEditable(false);
        Font textField7Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField7.getFont());
        if (textField7Font != null) textField7.setFont(textField7Font);
        textField7.setHorizontalAlignment(0);
        textField7.setText("Количество");
        TeePanel.add(textField7, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField8 = new JTextField();
        textField8.setEditable(false);
        Font textField8Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField8.getFont());
        if (textField8Font != null) textField8.setFont(textField8Font);
        textField8.setHorizontalAlignment(0);
        textField8.setText("Диаметр D, мм");
        TeePanel.add(textField8, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField9 = new JTextField();
        textField9.setEditable(false);
        Font textField9Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField9.getFont());
        if (textField9Font != null) textField9.setFont(textField9Font);
        textField9.setHorizontalAlignment(0);
        textField9.setText("Длина L1, мм");
        TeePanel.add(textField9, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JTextField textField10 = new JTextField();
        textField10.setEditable(false);
        Font textField10Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField10.getFont());
        if (textField10Font != null) textField10.setFont(textField10Font);
        textField10.setHorizontalAlignment(0);
        textField10.setText("Количество");
        TeePanel.add(textField10, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField11 = new JTextField();
        textField11.setEditable(false);
        Font textField11Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField11.getFont());
        if (textField11Font != null) textField11.setFont(textField11Font);
        textField11.setHorizontalAlignment(0);
        textField11.setText("Диаметр D, мм");
        TeePanel.add(textField11, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        teeSqRdD = new JTextField();
        TeePanel.add(teeSqRdD, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField12 = new JTextField();
        textField12.setEditable(false);
        Font textField12Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField12.getFont());
        if (textField12Font != null) textField12.setFont(textField12Font);
        textField12.setHorizontalAlignment(0);
        textField12.setText("Длина L1, мм");
        TeePanel.add(textField12, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        teeSqRL1 = new JTextField();
        TeePanel.add(teeSqRL1, new GridConstraints(18, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JTextField textField13 = new JTextField();
        textField13.setEditable(false);
        Font textField13Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField13.getFont());
        if (textField13Font != null) textField13.setFont(textField13Font);
        textField13.setHorizontalAlignment(0);
        textField13.setText("Количество");
        TeePanel.add(textField13, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeSqRQuantity = new JTextField();
        TeePanel.add(teeSqRQuantity, new GridConstraints(19, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/img/tee_incut_correction.png")));
        label1.setText("");
        TeePanel.add(label1, new GridConstraints(14, 0, 7, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JTextField textField14 = new JTextField();
        textField14.setEditable(false);
        Font textField14Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField14.getFont());
        if (textField14Font != null) textField14.setFont(textField14Font);
        textField14.setHorizontalAlignment(0);
        textField14.setText("Ширина A, мм");
        TeePanel.add(textField14, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField15 = new JTextField();
        textField15.setEditable(false);
        Font textField15Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField15.getFont());
        if (textField15Font != null) textField15.setFont(textField15Font);
        textField15.setHorizontalAlignment(0);
        textField15.setText("Высота В1, мм");
        TeePanel.add(textField15, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeSqSqB1 = new JTextField();
        TeePanel.add(teeSqSqB1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField16 = new JTextField();
        textField16.setEditable(false);
        Font textField16Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField16.getFont());
        if (textField16Font != null) textField16.setFont(textField16Font);
        textField16.setHorizontalAlignment(0);
        textField16.setText("Длина L, мм");
        TeePanel.add(textField16, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeSqSqL = new JTextField();
        TeePanel.add(teeSqSqL, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField17 = new JTextField();
        textField17.setEditable(false);
        Font textField17Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField17.getFont());
        if (textField17Font != null) textField17.setFont(textField17Font);
        textField17.setHorizontalAlignment(0);
        textField17.setText("Длина L, мм");
        TeePanel.add(textField17, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        teeSqRL = new JTextField();
        teeSqRL.setText("");
        TeePanel.add(teeSqRL, new GridConstraints(17, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField18 = new JTextField();
        textField18.setEditable(false);
        Font textField18Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField18.getFont());
        if (textField18Font != null) textField18.setFont(textField18Font);
        textField18.setHorizontalAlignment(0);
        textField18.setText("Диаметр D1, мм");
        TeePanel.add(textField18, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeRRD1 = new JTextField();
        TeePanel.add(teeRRD1, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField19 = new JTextField();
        textField19.setEditable(false);
        Font textField19Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField19.getFont());
        if (textField19Font != null) textField19.setFont(textField19Font);
        textField19.setHorizontalAlignment(0);
        textField19.setText("Длина L, мм");
        TeePanel.add(textField19, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeRRL = new JTextField();
        TeePanel.add(teeRRL, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField20 = new JTextField();
        textField20.setEditable(false);
        Font textField20Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField20.getFont());
        if (textField20Font != null) textField20.setFont(textField20Font);
        textField20.setHorizontalAlignment(0);
        textField20.setText("Длина L1, мм");
        TeePanel.add(textField20, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeSqSqL1 = new JTextField();
        TeePanel.add(teeSqSqL1, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField21 = new JTextField();
        textField21.setEditable(false);
        Font textField21Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField21.getFont());
        if (textField21Font != null) textField21.setFont(textField21Font);
        textField21.setHorizontalAlignment(0);
        textField21.setText("Ширина A, мм");
        TeePanel.add(textField21, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeSqRA = new JTextField();
        TeePanel.add(teeSqRA, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField22 = new JTextField();
        textField22.setEditable(false);
        Font textField22Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField22.getFont());
        if (textField22Font != null) textField22.setFont(textField22Font);
        textField22.setHorizontalAlignment(0);
        textField22.setText("Высота B, мм ");
        TeePanel.add(textField22, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeSqRB = new JTextField();
        TeePanel.add(teeSqRB, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField23 = new JTextField();
        textField23.setEditable(false);
        Font textField23Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField23.getFont());
        if (textField23Font != null) textField23.setFont(textField23Font);
        textField23.setHorizontalAlignment(0);
        textField23.setText("Количество");
        TeePanel.add(textField23, new GridConstraints(26, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField24 = new JTextField();
        textField24.setEditable(false);
        Font textField24Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField24.getFont());
        if (textField24Font != null) textField24.setFont(textField24Font);
        textField24.setHorizontalAlignment(0);
        textField24.setText("Длина L1, мм");
        TeePanel.add(textField24, new GridConstraints(25, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeRSqL1 = new JTextField();
        TeePanel.add(teeRSqL1, new GridConstraints(25, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField25 = new JTextField();
        textField25.setEditable(false);
        Font textField25Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField25.getFont());
        if (textField25Font != null) textField25.setFont(textField25Font);
        textField25.setHorizontalAlignment(0);
        textField25.setText("Ширина A, мм");
        TeePanel.add(textField25, new GridConstraints(21, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        teeRSqA = new JTextField();
        TeePanel.add(teeRSqA, new GridConstraints(21, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField26 = new JTextField();
        textField26.setEditable(false);
        Font textField26Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField26.getFont());
        if (textField26Font != null) textField26.setFont(textField26Font);
        textField26.setHorizontalAlignment(0);
        textField26.setText("Высота B, мм ");
        TeePanel.add(textField26, new GridConstraints(22, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeRSqB = new JTextField();
        TeePanel.add(teeRSqB, new GridConstraints(22, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField27 = new JTextField();
        textField27.setEditable(false);
        Font textField27Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField27.getFont());
        if (textField27Font != null) textField27.setFont(textField27Font);
        textField27.setHorizontalAlignment(0);
        textField27.setText("Длина L, мм");
        TeePanel.add(textField27, new GridConstraints(24, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeRSqL = new JTextField();
        TeePanel.add(teeRSqL, new GridConstraints(24, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField28 = new JTextField();
        textField28.setEditable(false);
        Font textField28Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField28.getFont());
        if (textField28Font != null) textField28.setFont(textField28Font);
        textField28.setHorizontalAlignment(0);
        textField28.setText("Диаметр D, мм");
        TeePanel.add(textField28, new GridConstraints(23, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeRSqD = new JTextField();
        TeePanel.add(teeRSqD, new GridConstraints(23, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teeRSqQuantity = new JTextField();
        TeePanel.add(teeRSqQuantity, new GridConstraints(26, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setIcon(new ImageIcon(getClass().getResource("/img/tee_output_rectangular_rectangular_correction.png")));
        label2.setText("");
        TeePanel.add(label2, new GridConstraints(0, 0, 8, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Button1 = new JButton();
        Button1.setText("Рассчитать");
        TeePanel.add(Button1, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setEditable(false);
        Font textField1Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        textField1.setHorizontalAlignment(0);
        TeePanel.add(textField1, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button2 = new JButton();
        Button2.setText("Рассчитать");
        TeePanel.add(Button2, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        textField2.setEditable(false);
        Font textField2Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField2.getFont());
        if (textField2Font != null) textField2.setFont(textField2Font);
        textField2.setHorizontalAlignment(0);
        TeePanel.add(textField2, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button3 = new JButton();
        Button3.setText("Рассчитать");
        TeePanel.add(Button3, new GridConstraints(20, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        textField3.setEditable(false);
        Font textField3Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField3.getFont());
        if (textField3Font != null) textField3.setFont(textField3Font);
        textField3.setHorizontalAlignment(0);
        TeePanel.add(textField3, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button4 = new JButton();
        Button4.setText("Рассчитать");
        TeePanel.add(Button4, new GridConstraints(27, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField4 = new JTextField();
        textField4.setEditable(false);
        Font textField4Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField4.getFont());
        if (textField4Font != null) textField4.setFont(textField4Font);
        textField4.setHorizontalAlignment(0);
        textField4.setText("");
        TeePanel.add(textField4, new GridConstraints(27, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setIcon(new ImageIcon(getClass().getResource("/img/tee_output_round_round_correction.png")));
        label3.setText("");
        TeePanel.add(label3, new GridConstraints(8, 0, 6, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setIcon(new ImageIcon(getClass().getResource("/img/tee_output_rectangular_round_correction.png")));
        label4.setText("");
        TeePanel.add(label4, new GridConstraints(21, 0, 7, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label2.setLabelFor(teeRSqQuantity);
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

