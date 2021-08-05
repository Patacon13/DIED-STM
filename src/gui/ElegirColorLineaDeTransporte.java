package gui;
// class using ChangeListener
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
  
public class ElegirColorLineaDeTransporte extends JPanel
  
    implements ChangeListener {
  
    protected JColorChooser Jcc;
    protected JLabel label;
  
    public ElegirColorLineaDeTransporte()
    {
        super(new BorderLayout());
  
        // Set up the Label at the top of the window
        label = new JLabel("Welcome to GeeksforGeeks",
                                       JLabel.CENTER);
  
        // set the foreground color of the text
        label.setForeground(Color.green);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setFont(new Font("SansSerif", Font.BOLD, 30)); // set size of the label
        label.setPreferredSize(new Dimension(10, 5));
  
        // create a Panel and set its layout
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.setBorder(BorderFactory.createTitledBorder("Label"));
  
        // Set up color chooser for setting text color
        Jcc = new JColorChooser(label.getForeground());
        Jcc.getSelectionModel().addChangeListener(this);
        Jcc.setBorder(BorderFactory.createTitledBorder(
            "Choose Text Color"));
        add(bannerPanel, BorderLayout.CENTER);

    }
  
    public void stateChanged(ChangeEvent e)
    {
        Color newColor = Jcc.getColor();
        label.setForeground(newColor);
    }
  
    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("ColorChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new ElegirColorLineaDeTransporte();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

}