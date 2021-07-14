
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Display cat facts.
 * 
 * @author Harris Chaudhry
 * @version 04-21-21
 *
 */
public class Facts implements ActionListener, MouseListener {

  private String[] facts;
  private static JPanel panel1;
  private JPanel buttonpanel;
  private JPanel factpanel;
  private String[] numberOfFacts;
  private JLabel[] labels;
  private JButton copyButton;

  private static JScrollPane scroll;
  private JComboBox<String> factBox;

  /**
   * Constructor.
   * 
   * @throws IOException throws a IOException
   */
  public Facts() throws IOException {

    copyButton = new JButton("Copy to Clipboard");
    copyButton.setBackground(Color.CYAN);
    copyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    copyButton.setAlignmentY(Component.CENTER_ALIGNMENT);
    copyButton.setBounds(100, 160, 200, 40);
    copyButton.setFocusable(false);
    copyButton.addMouseListener(this);
    copyButton.addActionListener(this);
    copyButton.setVisible(false);

    panel1 = new JPanel();
    panel1.setLayout(new GridBagLayout());
    panel1.setPreferredSize(new Dimension(1500, 500));
    scroll = new JScrollPane(panel1);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    panel1.setBackground(Color.DARK_GRAY);
    panel1.add(copyButton);


    buttonpanel = new JPanel();
    buttonpanel.setBackground(Color.DARK_GRAY);
    panel1.add(buttonpanel);

    factpanel = new JPanel();
    factpanel.setBackground(Color.DARK_GRAY);
    factpanel.setLayout(new BoxLayout(factpanel, 1));
    panel1.add(factpanel);

    numberOfFacts = new String[] {"Get 1 Fact", "Get 20 Facts", "Get 50 Facts"};
    factBox = new JComboBox<String>(numberOfFacts);

    factBox.setSelectedIndex(1);
    factBox.addActionListener(this);
    factBox.setBounds(factBox.getX(), factBox.getY(), 50, 50);
    factpanel.add(factBox);

    facts = ApiReader.getFacts(331);
    labels = new JLabel[facts.length];

    for (int i = 0; i < facts.length; i++) {

      labels[i] = new JLabel();
      labels[i].setFont(new Font("Times New Roman", Font.BOLD, 17));
      labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      labels[i].setAlignmentY(Component.CENTER_ALIGNMENT);

      Random rand = new Random();

      float r = rand.nextFloat();
      float g = rand.nextFloat();
      float b = rand.nextFloat();
      Color randomColor = new Color(r, g, b).brighter();


      labels[i].setForeground(randomColor);
      factpanel.add(labels[i]);

    }
  }

  /**
   * Returns the JScrollPane in Constructor.
   * 
   * @return JScrollPane scroll
   */
  public static JScrollPane getScrollPane() {
    return scroll;
  }

  /**
   * This method pulls a specific number of facts based on the number passed inside the parameters.
   * 
   * @param num number of facts
   * @throws IOException throws IOException
   */
  public void getFacts(int num) throws IOException {
    buttonpanel.add(factBox);
    for (JLabel j : labels) {
      j.setText("");
    }
    ArrayList<String> list = new ArrayList<>();

    for (int i = 0; i < num; i++) {
      list.add(facts[randomInt(0, facts.length)]);
    }
    Collections.sort(list);
    for (int j = 0; j < list.size(); j++) {
      if (list.get(j).length() < 170) {
        labels[j].setText(list.get(j));
      }
    }
  }

  /**
   * Returns a random integer from the range of min and max values.
   * 
   * @param min minimum bound
   * @param max maximum bound
   * @return random integer
   */
  public static int randomInt(int min, int max) {
    int random = (int) (min + Math.random() * (max - min));
    return random;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == factBox) {
      @SuppressWarnings("unchecked")
      JComboBox<String> cb = (JComboBox<String>) e.getSource();
      String message = (String) cb.getSelectedItem();
      switch (message) {
        case "Get 1 Fact":
          try {
            getFacts(1);
            copyButton.setVisible(true);
          } catch (IOException e1) {
            System.out.println("IO Exception");
          }
          break;
        case "Get 20 Facts":
          try {
            getFacts(20);
            copyButton.setVisible(false);

          } catch (IOException e1) {
            System.out.println("IO Exception");

          }
          break;
        case "Get 50 Facts":
          try {
            getFacts(50);
            copyButton.setVisible(false);
          } catch (IOException e1) {
            System.out.println("IO Exception");
          }
          break;
        default:
          try {
            getFacts(0);
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
          break;
      }
    } else {
      String myString = labels[0].getText();
      StringSelection stringSelection = new StringSelection(myString);
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(stringSelection, null);
    }
  }

  @Override
  public void mouseClicked(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    JButton j = (JButton) arg0.getSource();
    j.setBackground(j.getBackground().darker());

  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    JButton j = (JButton) arg0.getSource();
    j.setBackground(j.getBackground().brighter());
  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    // TODO Auto-generated method stub

  }
}
