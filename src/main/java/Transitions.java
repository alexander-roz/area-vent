import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;
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
                double width = Double.parseDouble((values[0])) / 1000;
                double height = Double.parseDouble((values[1])) / 1000;
                double width1 = Double.parseDouble((values[2])) / 1000;
                double height1 = Double.parseDouble((values[3])) / 1000;
                double length = Double.parseDouble((values[4])) / 1000;
                double quantity = Double.parseDouble((values[5]));

                double ratioWidth = Math.sqrt(((height - height1) / 2) * ((height - height1) / 2) + (length * length));
                double ratioHeight = Math.sqrt(((width - width1) / 2) * ((width - width1) / 2) + (length * length));
                double transition_rectangular_rectangular_square = (2 * (width + width1) / 2 * ratioWidth + 2 * (height + height1) / 2 * ratioHeight + (2 * width1 + 2 * height1 + 2 * width + 2 * height) * 0.05);

                double result = transition_rectangular_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TransitionPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button2.addActionListener(e -> {
            //обработка значений перехода прямоугольный/круглый
            try {
                String[] values = getSqRValues();
                double diameter = Double.parseDouble((values[0])) / 1000;
                double width = Double.parseDouble((values[1])) / 1000;
                double height = Double.parseDouble((values[2])) / 1000;
                double length = Double.parseDouble((values[3])) / 1000;
                double quantity = Double.parseDouble((values[4]));

                double transition_round_rectangular_square = (((width + height) * 2.8 + Math.PI * diameter) / 2 * length);
                double result = transition_round_rectangular_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(TransitionPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        Button3.addActionListener(e -> {
            //обработка значений перехода круглый/круглый
            try {
                String[] values = getRRValues();
                double diameter = Double.parseDouble((values[0])) / 1000;
                double diameter1 = Double.parseDouble((values[1])) / 1000;
                double length = Double.parseDouble((values[2])) / 1000;
                double quantity = Double.parseDouble((values[3]));

                double transition_round_round_square = (Math.PI * Math.sqrt(length * length + ((diameter - diameter1) / 2) * ((diameter - diameter1) / 2)) * (diameter / 2 + diameter1 / 2) + Math.PI * diameter * 0.05 + Math.PI * diameter1 * 0.05);
                double result = transition_round_round_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField3.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
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
        TransitionScrollPane = new JScrollPane();
        contentPane.add(TransitionScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        TransitionPanel = new JPanel();
        TransitionPanel.setLayout(new GridLayoutManager(18, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        TransitionScrollPane.setViewportView(TransitionPanel);
        TransitionPanel.setBorder(BorderFactory.createTitledBorder(null, "Площадь переходов", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, TransitionPanel.getFont()), new Color(-13215616)));
        redSqSqA = new JTextField();
        TransitionPanel.add(redSqSqA, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redSqSqB = new JTextField();
        TransitionPanel.add(redSqSqB, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redSqSqA1 = new JTextField();
        TransitionPanel.add(redSqSqA1, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redSqSqQuantity = new JTextField();
        TransitionPanel.add(redSqSqQuantity, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redSqRD = new JTextField();
        TransitionPanel.add(redSqRD, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        redSqRL = new JTextField();
        TransitionPanel.add(redSqRL, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        redSqRQuantity = new JTextField();
        TransitionPanel.add(redSqRQuantity, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/img/transition_round_rectangular_correction.png")));
        label1.setText("");
        TransitionPanel.add(label1, new GridConstraints(7, 0, 6, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JTextField textField4 = new JTextField();
        textField4.setEditable(false);
        Font textField4Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField4.getFont());
        if (textField4Font != null) textField4.setFont(textField4Font);
        textField4.setHorizontalAlignment(0);
        textField4.setText("Высота B, мм ");
        TransitionPanel.add(textField4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField5 = new JTextField();
        textField5.setEditable(false);
        Font textField5Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField5.getFont());
        if (textField5Font != null) textField5.setFont(textField5Font);
        textField5.setHorizontalAlignment(0);
        textField5.setText("Ширина А1, мм");
        TransitionPanel.add(textField5, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField6 = new JTextField();
        textField6.setEditable(false);
        Font textField6Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField6.getFont());
        if (textField6Font != null) textField6.setFont(textField6Font);
        textField6.setHorizontalAlignment(0);
        textField6.setText("Количество");
        TransitionPanel.add(textField6, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField7 = new JTextField();
        textField7.setEditable(false);
        Font textField7Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField7.getFont());
        if (textField7Font != null) textField7.setFont(textField7Font);
        textField7.setHorizontalAlignment(0);
        textField7.setText("Диаметр D, мм");
        TransitionPanel.add(textField7, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField8 = new JTextField();
        textField8.setEditable(false);
        Font textField8Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField8.getFont());
        if (textField8Font != null) textField8.setFont(textField8Font);
        textField8.setHorizontalAlignment(0);
        textField8.setText("Длина L, мм");
        TransitionPanel.add(textField8, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JTextField textField9 = new JTextField();
        textField9.setEditable(false);
        Font textField9Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField9.getFont());
        if (textField9Font != null) textField9.setFont(textField9Font);
        textField9.setHorizontalAlignment(0);
        textField9.setText("Количество");
        TransitionPanel.add(textField9, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField10 = new JTextField();
        textField10.setEditable(false);
        Font textField10Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField10.getFont());
        if (textField10Font != null) textField10.setFont(textField10Font);
        textField10.setHorizontalAlignment(0);
        textField10.setText("Диаметр D, мм");
        TransitionPanel.add(textField10, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        redRRD = new JTextField();
        TransitionPanel.add(redRRD, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField11 = new JTextField();
        textField11.setEditable(false);
        Font textField11Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField11.getFont());
        if (textField11Font != null) textField11.setFont(textField11Font);
        textField11.setHorizontalAlignment(0);
        textField11.setText("Длина L, мм");
        TransitionPanel.add(textField11, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        redRRL = new JTextField();
        TransitionPanel.add(redRRL, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JTextField textField12 = new JTextField();
        textField12.setEditable(false);
        Font textField12Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField12.getFont());
        if (textField12Font != null) textField12.setFont(textField12Font);
        textField12.setHorizontalAlignment(0);
        textField12.setText("Количество");
        TransitionPanel.add(textField12, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redRRQuantity = new JTextField();
        TransitionPanel.add(redRRQuantity, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 19), null, 0, false));
        final JTextField textField13 = new JTextField();
        textField13.setEditable(false);
        Font textField13Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField13.getFont());
        if (textField13Font != null) textField13.setFont(textField13Font);
        textField13.setHorizontalAlignment(0);
        textField13.setText("Ширина A, мм");
        TransitionPanel.add(textField13, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField14 = new JTextField();
        textField14.setEditable(false);
        Font textField14Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField14.getFont());
        if (textField14Font != null) textField14.setFont(textField14Font);
        textField14.setHorizontalAlignment(0);
        textField14.setText("Высота В1, мм");
        TransitionPanel.add(textField14, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redSqSqB1 = new JTextField();
        TransitionPanel.add(redSqSqB1, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField15 = new JTextField();
        textField15.setEditable(false);
        Font textField15Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField15.getFont());
        if (textField15Font != null) textField15.setFont(textField15Font);
        textField15.setHorizontalAlignment(0);
        textField15.setText("Длина L, мм");
        TransitionPanel.add(textField15, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redSqSqL = new JTextField();
        TransitionPanel.add(redSqSqL, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField16 = new JTextField();
        textField16.setEditable(false);
        Font textField16Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField16.getFont());
        if (textField16Font != null) textField16.setFont(textField16Font);
        textField16.setHorizontalAlignment(0);
        textField16.setText("Диаметр D1, мм");
        TransitionPanel.add(textField16, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        redRRD1 = new JTextField();
        redRRD1.setText("");
        TransitionPanel.add(redRRD1, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField17 = new JTextField();
        textField17.setEditable(false);
        Font textField17Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField17.getFont());
        if (textField17Font != null) textField17.setFont(textField17Font);
        textField17.setHorizontalAlignment(0);
        textField17.setText("Ширина A, мм");
        TransitionPanel.add(textField17, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redSqRA = new JTextField();
        TransitionPanel.add(redSqRA, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField18 = new JTextField();
        textField18.setEditable(false);
        Font textField18Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField18.getFont());
        if (textField18Font != null) textField18.setFont(textField18Font);
        textField18.setHorizontalAlignment(0);
        textField18.setText("Высота B, мм ");
        TransitionPanel.add(textField18, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        redSqRB = new JTextField();
        TransitionPanel.add(redSqRB, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setIcon(new ImageIcon(getClass().getResource("/img/transition_rectangular_rectangular.png")));
        label2.setText("");
        TransitionPanel.add(label2, new GridConstraints(0, 0, 7, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setIcon(new ImageIcon(getClass().getResource("/img/transition_round_round_correction.png")));
        label3.setText("");
        TransitionPanel.add(label3, new GridConstraints(13, 0, 5, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Button1 = new JButton();
        Button1.setText("Рассчитать");
        TransitionPanel.add(Button1, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setEditable(false);
        Font textField1Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        textField1.setHorizontalAlignment(0);
        TransitionPanel.add(textField1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button2 = new JButton();
        Button2.setText("Рассчитать");
        TransitionPanel.add(Button2, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        textField2.setEditable(false);
        Font textField2Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField2.getFont());
        if (textField2Font != null) textField2.setFont(textField2Font);
        textField2.setHorizontalAlignment(0);
        TransitionPanel.add(textField2, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button3 = new JButton();
        Button3.setText("Рассчитать");
        TransitionPanel.add(Button3, new GridConstraints(17, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        textField3.setEditable(false);
        Font textField3Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField3.getFont());
        if (textField3Font != null) textField3.setFont(textField3Font);
        textField3.setHorizontalAlignment(0);
        TransitionPanel.add(textField3, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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

