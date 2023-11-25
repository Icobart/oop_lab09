package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.border.Border;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final static int PROPORTION = 2;

    private final JFrame frame = new JFrame("SimpleGUIWithFileChooser");

    private SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea text = new JTextArea();
        canvas.add(text, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.fetchAndWrite(text.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        final JPanel canvas2 = new JPanel();
        canvas2.setLayout(new BorderLayout());
        final JTextField field = new JTextField(controller.getCurrentStringFile());
        field.setEditable(false);
        canvas2.add(field, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse...");
        canvas2.add(browse, BorderLayout.LINE_END);
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setSelectedFile(controller.getCurrentFile());
                final int saveDial = fc.showSaveDialog(frame);
                switch(saveDial) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setCurrentFile(fc.getSelectedFile());
                        browse.setText(controller.getCurrentStringFile());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, saveDial, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
        canvas.add(canvas2, BorderLayout.NORTH);
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * Launches the application
     * 
     * @param a unused
     * 
     */
    public static void main(final String... a) {
        final SimpleGUIWithFileChooser guifc = new SimpleGUIWithFileChooser(new Controller());
        guifc.display();
    }
}
