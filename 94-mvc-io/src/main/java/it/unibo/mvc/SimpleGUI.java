package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 2;

    private final JFrame frame = new JFrame();
    private final Controller controller;

    /**
     * builds a new {@Link SimpleGUI}
     * @param controller sets up the controller
     */
    public SimpleGUI(final Controller controller) {
        this.controller = controller;
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextField field = new JTextField();
        canvas.add(field, BorderLayout.NORTH);
        final JTextArea text = new JTextArea();
        text.setEditable(false);
        canvas.add(text, BorderLayout.CENTER);
        final JPanel buttoncanvas = new JPanel();
        buttoncanvas.setLayout(new BoxLayout(buttoncanvas, BoxLayout.LINE_AXIS));
        final JButton print = new JButton("Print");
        final JButton history = new JButton("Show History");
        buttoncanvas.add(print);
        buttoncanvas.add(history);
        canvas.add(buttoncanvas, BorderLayout.SOUTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleGUI.this.controller.setNextString(field.getText());
                SimpleGUI.this.controller.printCurrentString();
            }
            
        });
        history.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final StringBuilder apptext = new StringBuilder();
                for(final String current : SimpleGUI.this.controller.getHistoryStrings()) {
                    apptext.append(current);
                    apptext.append('\n');
                }
                if(!(SimpleGUI.this.controller.getHistoryStrings()).isEmpty()) {
                    apptext.deleteCharAt(apptext.length()-1);
                }
                text.setText(apptext.toString()); 
            }
            
        });
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    /**
     * starts the graphical application
     * @param a unused
     */
    public static void main(final String... a) {
        final SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.display();
    }

}
