import javax.swing.*;

public class Container extends JDialog {
    private JPanel contentPane;
    private JPanel mainframe;
    private JPanel buttons;
    private JButton pipes;
    private JButton taps;
    private JButton tee;
    private JButton reducer;
    private JButton closer;
    private JButton caps;
    private JButton duck;
    private JButton inset;
    private JPanel picture;


    public Container() {
        try {
            setContentPane(contentPane);
            setModal(true);

            pipes.addActionListener(e -> goPipes());
            taps.addActionListener(e -> goTaps());
            reducer.addActionListener(e -> goTransitions());
            tee.addActionListener(e -> goTee());
            closer.addActionListener(e -> goClosers());
            caps.addActionListener(e -> goCaps());
            duck.addActionListener(e -> goDucks());
            inset.addActionListener(e -> goInsets());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void goPipes(){
        Pipes pipes = new Pipes();
        pipes.pack();
        pipes.setLocationRelativeTo(null);
        pipes.setVisible(true);
    }

    private void goTaps(){
        Taps taps = new Taps();
        taps.pack();
        taps.setLocationRelativeTo(null);
        taps.setVisible(true);
    }

    private void goTransitions(){
        Transitions transitions = new Transitions();
        transitions.pack();
        transitions.setLocationRelativeTo(null);
        transitions.setVisible(true);
    }

    private void goTee(){
        Tee tee =  new Tee();
        tee.pack();
        tee.setLocationRelativeTo(null);
        tee.setVisible(true);
    }

    private void goClosers(){
        Closers closers = new Closers();
        closers.pack();
        closers.setLocationRelativeTo(null);
        closers.setVisible(true);
    }

    private void goCaps(){
        Caps caps = new Caps();
        caps.pack();
        caps.setLocationRelativeTo(null);
        caps.setVisible(true);
    }

    private void goDucks(){
        Ducks ducks = new Ducks();
        ducks.pack();
        ducks.setLocationRelativeTo(null);
        ducks.setVisible(true);
    }

    private void goInsets(){
        Insets insets = new Insets();
        insets.pack();
        insets.setLocationRelativeTo(null);
        insets.setVisible(true);
    }
}

