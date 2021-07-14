import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Truth extends JPanel implements ActionListener {

  private JPanel leftpanel;


  private JPanel middlepanel;
  private JButton startbutton;
  private JLabel findtruthlabel;
  private JLabel pointslabel;
  private int points;
  private JLabel attemptlabel;
  private int attempts;
  private JLabel scorelabel;
  private String currentlie;
  private JLabel mythlabel;

  private JLabel ph1;
  private JLabel ph2;

  private JButton liebutton;
  private JButton truthbutton1;
  private JButton truthbutton2;

  private JPanel rightpanel;

  public Truth() {

    points = 0;
    attempts = 0;
    currentlie = "";

    setBackground(Color.DARK_GRAY);
    setLayout(new GridLayout(1, 1));

    leftpanel = new JPanel();
    leftpanel.setBackground(Color.DARK_GRAY);
    add(leftpanel);

    middlepanel = new JPanel();
    middlepanel.setBackground(Color.DARK_GRAY);
    middlepanel.setLayout(new BoxLayout(middlepanel, 1));
    add(middlepanel);

    ph1 = new JLabel(" ");
    ph1.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph1.setFont(new Font("Impact", Font.BOLD, 350));
    middlepanel.add(ph1);
    ph2 = new JLabel(" ");
    ph2.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph2.setFont(new Font("Impact", Font.BOLD, 50));
    startbutton = new JButton("Start");
    startbutton.setBackground(Color.CYAN);
    startbutton.addActionListener(this);
    startbutton.setFont(new Font("Impact", Font.PLAIN, 40));
    startbutton.setForeground(Color.DARK_GRAY);
    startbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
    middlepanel.add(startbutton);

    findtruthlabel = new JLabel("Find the lie among the truths:");
    findtruthlabel.setForeground(Color.LIGHT_GRAY);
    findtruthlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    findtruthlabel.setFont(new Font("Impact", Font.PLAIN, 50));

    liebutton = new JButton("");
    liebutton.setBackground(Color.CYAN);
    liebutton.addActionListener(this);
    liebutton.setFont(new Font("Impact", Font.PLAIN, 25));
    liebutton.setForeground(Color.DARK_GRAY);
    liebutton.setAlignmentX(Component.CENTER_ALIGNMENT);

    truthbutton1 = new JButton("");
    truthbutton1.setBackground(Color.CYAN);
    truthbutton1.addActionListener(this);
    truthbutton1.setFont(new Font("Impact", Font.PLAIN, 25));
    truthbutton1.setForeground(Color.DARK_GRAY);
    truthbutton1.setAlignmentX(Component.CENTER_ALIGNMENT);

    truthbutton2 = new JButton("");
    truthbutton2.setBackground(Color.CYAN);
    truthbutton2.addActionListener(this);
    truthbutton2.setFont(new Font("Impact", Font.PLAIN, 25));
    truthbutton2.setForeground(Color.DARK_GRAY);
    truthbutton2.setAlignmentX(Component.CENTER_ALIGNMENT);

    pointslabel = new JLabel("Points: " + points);
    pointslabel.setForeground(Color.LIGHT_GRAY);
    pointslabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    pointslabel.setFont(new Font("Impact", Font.PLAIN, 15));

    attemptlabel = new JLabel("Attempts: " + attempts);
    attemptlabel.setForeground(Color.LIGHT_GRAY);
    attemptlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    attemptlabel.setFont(new Font("Impact", Font.PLAIN, 15));

    scorelabel = new JLabel("Score: ");
    scorelabel.setForeground(Color.LIGHT_GRAY);
    scorelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    scorelabel.setFont(new Font("Impact", Font.PLAIN, 15));

    mythlabel = new JLabel("");
    mythlabel.setForeground(Color.LIGHT_GRAY);
    mythlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    mythlabel.setFont(new Font("Impact", Font.PLAIN, 15));

    rightpanel = new JPanel();
    rightpanel.setBackground(Color.DARK_GRAY);
    add(rightpanel);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton j = (JButton) e.getSource();

    if (j.getText().equals("Start")) {
      setLayout(new BoxLayout(this, 1));
      middlepanel.remove(ph1);
      ph2.setFont(new Font("Impact", Font.PLAIN, 50));
      middlepanel.add(findtruthlabel);
      middlepanel.add(scorelabel);
      middlepanel.add(attemptlabel);
      middlepanel.add(pointslabel);
      middlepanel.add(ph2);
      startbutton.setVisible(false);
      randomelyAddButtons();
      middlepanel.add(mythlabel);
    } else if (e.getSource().equals(liebutton)) {
      increaseAttempts();
      increasePoints();
      displayMyth();
      randomelyAddButtons();
      calculateScore();
    } else {
      increaseAttempts();
      displayMyth();
      randomelyAddButtons();
      calculateScore();
    }
  }

  public void randomelyAddButtons() {
    middlepanel.remove(liebutton);
    middlepanel.remove(truthbutton1);
    middlepanel.remove(truthbutton2);

    int r = TwoTruthsOneLie.randomInt(0, 4);
    if (r == 0) {
      middlepanel.add(truthbutton1);
      middlepanel.add(truthbutton2);
      middlepanel.add(liebutton);
    } else if (r == 1) {
      middlepanel.add(truthbutton2);
      middlepanel.add(truthbutton1);
      middlepanel.add(liebutton);
    } else if (r == 2) {
      middlepanel.add(truthbutton2);
      middlepanel.add(liebutton);
      middlepanel.add(truthbutton1);
    } else {
      middlepanel.add(liebutton);
      middlepanel.add(truthbutton2);
      middlepanel.add(truthbutton1);
    }

    String[] lietruths = TwoTruthsOneLie.catGameThree();
    currentlie = lietruths[0];
    liebutton.setText(lietruths[0]);
    truthbutton1.setText(lietruths[1]);
    truthbutton2.setText(lietruths[2]);
  }


  public void increasePoints() {
    points++;
    pointslabel.setText("Points: " + points);
  }

  public void increaseAttempts() {
    attempts++;
    attemptlabel.setText("Attempts: " + attempts);
  }

  public void calculateScore() {
    scorelabel.setText("Score: " + String.format("%.1f", (points * 1.0 / attempts) * 100) + "%");
  }

  public void displayMyth() {
    mythlabel.setText("MYTH: " + currentlie);
  }

}
