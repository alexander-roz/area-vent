import javax.swing.*;
import static java.awt.Toolkit.getDefaultToolkit;

public class Main extends JFrame {

    public static void main(String[] args) {
        Container container = new Container();
        JFrame frame = new JFrame("Areavent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 550);
        frame.setLocationRelativeTo(null);
        String iconPath = "/img/Areavent_icon.png";
        frame.setIconImage(getDefaultToolkit().getImage(iconPath));
        frame.add(container.getContentPane());
        frame.setVisible(true);
    }
}
