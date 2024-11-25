import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;
import java.util.stream.IntStream;

public class Ducks extends JDialog {
    private JPanel contentPane;
    private JPanel DucksPanel;
    private JTextField ducks1H;
    private JTextField ducks2A;
    private JTextField ducks2Q;
    private JTextField ducks1B;
    private JTextField ducks2B;
    private JTextField ducks2L;
    private JButton Button1;
    private JTextField textField1;
    private JButton Button2;
    private JTextField textField2;
    private JTextField ducks1A;
    private JTextField ducks1L;
    private JTextField ducks1Q;
    private JTextField ducks2H;
    private JTextField ducks2H1;
    private JScrollPane DucksScrollPane;

    public Ducks() {
        try {
            setContentPane(contentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button1.addActionListener(e -> {
            //обработка значений воздуховода со смещением в одной плоскости
            try {
                String[] values = getDuck1Values();
                double width = Double.parseDouble((values[0])) / 1000;
                double height = Double.parseDouble((values[1])) / 1000;
                double length = Double.parseDouble((values[2])) / 1000;
                double shift = Double.parseDouble((values[3])) / 1000;
                double quantity = Double.parseDouble((values[4]));

                double duck_one_square = (2 * (width * Math.sqrt(length * length + shift * shift) + height * length + 0.1 * (width + height)));
                double result = duck_one_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField1.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(DucksPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        Button2.addActionListener(e -> {
            //обработка значений воздуховода со смещением в двух плоскостях
            try {
                String[] values = getDuck2Values();
                double width = Double.parseDouble((values[0])) / 1000;
                double height = Double.parseDouble((values[1])) / 1000;
                double length = Double.parseDouble((values[2])) / 1000;
                double shift = Double.parseDouble((values[3])) / 1000;
                double shift1 = Double.parseDouble((values[4])) / 1000;
                double quantity = Double.parseDouble((values[5]));

                double duck_two_square = (2 * (height * Math.sqrt(length * length + shift1 * shift1) + width * Math.sqrt(length * length + shift * shift) + 0.1 * (width + height)));
                double result = duck_two_square * quantity;

                String formatresult = String.format("%.2f", result);
                textField2.setText(String.valueOf(formatresult));
            } catch (NumberFormatException exception) {
                String message = Message.getMessage();
                JOptionPane.showMessageDialog(DucksPanel, message, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    //Сбор значений утки 1 (смещение в одной плоскости)
    public String[] getDuck1Values() {
        String[] values = new String[5];
        values[0] = ducks1A.getText();
        values[1] = ducks1B.getText();
        values[2] = ducks1L.getText();
        values[3] = ducks1H.getText();
        values[4] = ducks1Q.getText();
        //фильтр вводимых значений для парсинга в double
        String[] correctedvalues = values.clone();
        IntStream.range(0, correctedvalues.length).forEachOrdered(i -> {
            String somestring = correctedvalues[i];
            correctedvalues[i] = somestring.replace(',', '.');
        });
        return correctedvalues;
    }

    //Сбор значений утки 2 (смещением в 2х плоскостях)
    public String[] getDuck2Values() {
        String[] values = new String[6];
        values[0] = ducks2A.getText();
        values[1] = ducks2B.getText();
        values[2] = ducks2L.getText();
        values[3] = ducks2H.getText();
        values[4] = ducks2H1.getText();
        values[5] = ducks2Q.getText();
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
        contentPane.setBorder(BorderFactory.createTitledBorder(null, "Площадь воздуховода со смещением", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, contentPane.getFont()), new Color(-13215616)));
        DucksScrollPane = new JScrollPane();
        contentPane.add(DucksScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        DucksPanel = new JPanel();
        DucksPanel.setLayout(new GridLayoutManager(13, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        DucksScrollPane.setViewportView(DucksPanel);
        ducks1H = new JTextField();
        DucksPanel.add(ducks1H, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ducks2A = new JTextField();
        DucksPanel.add(ducks2A, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        ducks2Q = new JTextField();
        DucksPanel.add(ducks2Q, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("/img/duck_two_correction.png")));
        label1.setText("");
        DucksPanel.add(label1, new GridConstraints(6, 0, 7, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JTextField textField3 = new JTextField();
        textField3.setEditable(false);
        Font textField3Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField3.getFont());
        if (textField3Font != null) textField3.setFont(textField3Font);
        textField3.setHorizontalAlignment(0);
        textField3.setText("Сдвиг H, мм");
        DucksPanel.add(textField3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField4 = new JTextField();
        textField4.setEditable(false);
        Font textField4Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField4.getFont());
        if (textField4Font != null) textField4.setFont(textField4Font);
        textField4.setHorizontalAlignment(0);
        textField4.setText("Ширина A, мм");
        DucksPanel.add(textField4, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 20), null, 0, false));
        final JTextField textField5 = new JTextField();
        textField5.setEditable(false);
        Font textField5Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField5.getFont());
        if (textField5Font != null) textField5.setFont(textField5Font);
        textField5.setHorizontalAlignment(0);
        textField5.setText("Количество");
        DucksPanel.add(textField5, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField6 = new JTextField();
        textField6.setEditable(false);
        Font textField6Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField6.getFont());
        if (textField6Font != null) textField6.setFont(textField6Font);
        textField6.setHorizontalAlignment(0);
        textField6.setText("Ширина В, мм");
        DucksPanel.add(textField6, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        ducks1B = new JTextField();
        ducks1B.setText("");
        DucksPanel.add(ducks1B, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        final JTextField textField7 = new JTextField();
        textField7.setEditable(false);
        Font textField7Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField7.getFont());
        if (textField7Font != null) textField7.setFont(textField7Font);
        textField7.setHorizontalAlignment(0);
        textField7.setText("Высота B, мм ");
        DucksPanel.add(textField7, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ducks2B = new JTextField();
        DucksPanel.add(ducks2B, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField8 = new JTextField();
        textField8.setEditable(false);
        Font textField8Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField8.getFont());
        if (textField8Font != null) textField8.setFont(textField8Font);
        textField8.setHorizontalAlignment(0);
        textField8.setText("Длина L, мм");
        DucksPanel.add(textField8, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ducks2L = new JTextField();
        DucksPanel.add(ducks2L, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button1 = new JButton();
        Button1.setText("Рассчитать");
        DucksPanel.add(Button1, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setEditable(false);
        Font textField1Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        textField1.setHorizontalAlignment(0);
        DucksPanel.add(textField1, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Button2 = new JButton();
        Button2.setText("Рассчитать");
        DucksPanel.add(Button2, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        textField2.setEditable(false);
        Font textField2Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField2.getFont());
        if (textField2Font != null) textField2.setFont(textField2Font);
        textField2.setHorizontalAlignment(0);
        DucksPanel.add(textField2, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField9 = new JTextField();
        textField9.setEditable(false);
        Font textField9Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField9.getFont());
        if (textField9Font != null) textField9.setFont(textField9Font);
        textField9.setHorizontalAlignment(0);
        textField9.setText("Ширина A, мм");
        DucksPanel.add(textField9, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        ducks1A = new JTextField();
        DucksPanel.add(ducks1A, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setIcon(new ImageIcon(getClass().getResource("/img/duck_one_correction.png")));
        label2.setText("");
        DucksPanel.add(label2, new GridConstraints(0, 0, 6, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ducks1L = new JTextField();
        ducks1L.setText("");
        DucksPanel.add(ducks1L, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        ducks1Q = new JTextField();
        ducks1Q.setText("");
        DucksPanel.add(ducks1Q, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        final JTextField textField10 = new JTextField();
        textField10.setEditable(false);
        Font textField10Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField10.getFont());
        if (textField10Font != null) textField10.setFont(textField10Font);
        textField10.setHorizontalAlignment(0);
        textField10.setText("Длина L, мм");
        DucksPanel.add(textField10, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        final JTextField textField11 = new JTextField();
        textField11.setEditable(false);
        Font textField11Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField11.getFont());
        if (textField11Font != null) textField11.setFont(textField11Font);
        textField11.setHorizontalAlignment(0);
        textField11.setText("Количество");
        DucksPanel.add(textField11, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 18), null, 0, false));
        final JTextField textField12 = new JTextField();
        textField12.setEditable(false);
        Font textField12Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField12.getFont());
        if (textField12Font != null) textField12.setFont(textField12Font);
        textField12.setHorizontalAlignment(0);
        textField12.setText("Длина H, мм");
        DucksPanel.add(textField12, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JTextField textField13 = new JTextField();
        textField13.setEditable(false);
        Font textField13Font = this.$$$getFont$$$("Tahoma", Font.BOLD | Font.ITALIC, -1, textField13.getFont());
        if (textField13Font != null) textField13.setFont(textField13Font);
        textField13.setHorizontalAlignment(0);
        textField13.setText("Длина H1, мм");
        DucksPanel.add(textField13, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ducks2H = new JTextField();
        DucksPanel.add(ducks2H, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ducks2H1 = new JTextField();
        DucksPanel.add(ducks2H1, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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

