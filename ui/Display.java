import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

/**
 * Displays an interface for customizing cat memes.
 * 
 * @author Mason Marker, Devon Boldt, Marko Hine, Luke Hartley, Harris Chaudhry
 * @version 1.0
 */
public class Display implements ActionListener, MouseListener {

  public JFrame mainframe;

  public JFrame loadingframe;
  public JPanel loadingpanel;
  public JLabel loadingpicture;
  public ImageIcon loadingimage;

  // ----- CAT MEMES VARIABLES-----
  public JPanel catmemespanel;

  public BufferedImage[] catpics;
  public JButton[] imagebuttons;
  public ImageIcon tabicon;

  public JButton refresh;
  public JPanel scrollerimages;
  public JButton uploadbutton;

  public BufferedImage currentImage;
  public BufferedImage imageToSave;

  public JPanel editpanel;
  public JPanel editoptionpanel;
  public JList<String> fontcolor;
  public JTextArea toptext;
  public JTextArea bottomtext;
  public JButton applytext;
  public JButton savebutton;
  public JLabel editedimage;
  public boolean hasBeenEdited;
  // ------------------------------

  // ----- CAT FACTS VARIABLES ----- @Harris
  public JScrollPane catfactspanel;
  // -------------------------------

  // ----- CAT GAMES VARIABLES ----- @Devon
  public JPanel catgamepanel;

  public JPanel catinformationpanel;
  public JPanel statpanel;
  public JLabel catinfolabel;
  public JButton play;
  public JButton calico;
  public JButton sph;
  public JButton tabby;
  public JTextArea namearea;

  public JButton submitname;

  public String custombreed;
  public String name;
  public String status;
  public JLabel statuslabel;

  public JPanel questionpanel;
  public JPanel foodpanel;
  public JPanel waterpanel;
  public JPanel playpanel;
  public JLabel playlabel;

  public JLabel[] foodpixels;
  public JLabel[] waterpixels;
  public JLabel[] playpixels;

  public JButton feedbutton;
  public JButton waterbutton;
  public JButton playbutton;

  public SwingWorker<Void, Void> feedworker;
  public SwingWorker<Void, Void> waterworker;
  public SwingWorker<Void, Void> playworker;

  public JButton resetbutton;
  // -------------------------------

  // ----- CAT GAME 2 VARIABLES ----- @Mason

  public JPanel panelph1;
  public JPanel panelph2;

  public JPanel unscramblepanel;
  public JLabel pointslabel;
  public ArrayList<String> unscramblebreeds;

  public JButton getscrambledbreed;
  public String scrambledbreed;
  public String actualbreed;
  public JLabel scrambledbreedlabel;
  public JButton solutionbutton;
  public JButton guessbutton;
  public JTextArea guessarea;
  public int points;

  public SwingWorker<Void, Void> scrambleworker;
  // --------------------------------

  // ----- CAT GAME 3 VARIABLES -----
  public JPanel truthpanel;

  // --------------------------------


  // ----- CAT BREED VARIABLES ----- @Luke
  public JPanel catbreedpanel;
  // -------------------------------

  // ------- ABOUT VARIABLES -------
  public JPanel aboutpanel;
  public JButton patchnotesbutton;
  public JLabel datelabel;
  public JLabel patchnotes;
  // -------------------------------

  @SuppressWarnings("static-access")
  public Display() {

    points = 0;
    hasBeenEdited = false;
    currentImage = null;
    imageToSave = null;

    // loading and displaying introduction image
    loadingframe = new JFrame("Loading Cats V1");
    loadingframe.setUndecorated(true);
    loadingframe.setLayout(new GridLayout(1, 1));
    loadingframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    loadingpanel = new JPanel();
    loadingframe.add(loadingpanel);
    loadingpicture = new JLabel("");
    loadingpanel.add(loadingpicture);
    try {
      loadingimage = new ImageIcon(ImageIO.read(new URL(
          "https://s2.best-wallpaper.net/wallpaper/3840x2160/1806/Space-suit-cat-astronaut-creative-picture_3840x2160.jpg"))
          .getScaledInstance(960, 540, BufferedImage.SCALE_FAST));

      loadingpicture.setIcon(loadingimage);
    } catch (IOException e2) {
      System.out.println("[-] error loading introduction image");
    }
    loadingframe.setSize(new Dimension(loadingimage.getIconWidth(), loadingimage.getIconHeight()));
    loadingpanel.setSize(new Dimension(loadingframe.getWidth(), loadingframe.getHeight() + 10));
    loadingpanel.setBackground(new Color(12, 73, 113).darker());
    loadingframe.setLocationRelativeTo(null);
    loadingframe.pack();
    loadingframe.setVisible(true);
    SwingWorker<Void, Void> loadworker = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        BufferedImage image = toBuffered(loadingimage.getImage());
        BufferedImage copy;
        Font gFont = new Font("Impact", Font.PLAIN, 30);
        GradientPaint paint =
            new GradientPaint(20f, 20f, Color.BLUE.darker(), 380f, 280f, Color.WHITE);
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f);
        Graphics2D g;
        while (true) {

          copy = copyOf(image);
          g = copy.createGraphics();
          g.setFont(gFont);
          g.setPaint(paint);
          g.setComposite(alcom);
          g.drawString("launching", 62, 345);
          loadingpicture.setIcon(new ImageIcon(copy));
          Thread.sleep(400);

          copy = copyOf(image);
          g = copy.createGraphics();
          g.setFont(gFont);
          g.setPaint(paint);
          g.setComposite(alcom);
          g.drawString("launching.", 62, 345);
          loadingpicture.setIcon(new ImageIcon(copy));
          Thread.sleep(400);

          copy = copyOf(image);
          g = copy.createGraphics();
          g.setFont(gFont);
          g.setPaint(paint);
          g.setComposite(alcom);
          g.drawString("launching..", 62, 345);
          loadingpicture.setIcon(new ImageIcon(copy));
          Thread.sleep(400);

          copy = copyOf(image);
          g = copy.createGraphics();
          g.setFont(gFont);
          g.setPaint(paint);
          g.setComposite(alcom);
          g.drawString("launching...", 62, 345);
          loadingpicture.setIcon(new ImageIcon(copy));
          Thread.sleep(400);
        }
      }

    };
    loadworker.execute();

    // main frame
    mainframe = new JFrame("Loading Cats V1");
    mainframe.getContentPane().setBackground(Color.DARK_GRAY);
    mainframe.setLayout(new FlowLayout());
    mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainframe.setSize(1470, 1045);

    tabicon = null;
    try {
      tabicon = new ImageIcon(ImageIO.read(new URL(
          "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Black_Cat_Vector.svg/1200px-Black_Cat_Vector.svg.png"))
          .getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    } catch (IOException e) {
      System.out.println("[-] error loading tab icons");
    }

    JTabbedPane tabbedpanel = new JTabbedPane();
    UIManager.put("TabbedPane.selected", Color.ORANGE);
    tabbedpanel.setBackground(Color.LIGHT_GRAY);
    tabbedpanel.setPreferredSize(new Dimension(mainframe.getWidth() - 9, mainframe.getHeight()));
    mainframe.add(tabbedpanel);

    JTabbedPane catgametabs = new JTabbedPane();
    catgametabs.setBackground(Color.LIGHT_GRAY);

    catmemespanel = new JPanel();
    catmemespanel.setLayout(new FlowLayout());
    tabbedpanel.addTab("Cat Memes", tabicon, catmemespanel);

    catgamepanel = new JPanel();
    GridLayout catgamepanellayout = new GridLayout(1, 3);
    catgamepanel.setLayout(catgamepanellayout);
    catgamepanel.setBackground(Color.DARK_GRAY);
    tabbedpanel.addTab("Cat Games", tabicon, catgametabs);
    catgametabs.addTab("Save the Cat!", tabicon, catgamepanel);

    unscramblepanel = new JPanel();
    unscramblepanel.setLayout(new GridLayout(1, 3));
    unscramblepanel.setBackground(Color.DARK_GRAY);
    catgametabs.addTab("Unscramble That Breed!", tabicon, unscramblepanel);

    truthpanel = new Truth();
    catgametabs.addTab("2 Truths and a Lie", tabicon, truthpanel);


    panelph1 = new JPanel();
    panelph1.setBackground(Color.DARK_GRAY);
    unscramblepanel.add(panelph1);

    JPanel middlepanel = new JPanel();
    middlepanel.setLayout(new BoxLayout(middlepanel, 1));
    middlepanel.setBackground(Color.DARK_GRAY);
    unscramblepanel.add(middlepanel);

    panelph2 = new JPanel();
    panelph2.setBackground(Color.DARK_GRAY);
    unscramblepanel.add(panelph2);

    JLabel ph6 = new JLabel("    ");
    ph6.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph6.setFont(new Font("Impact", Font.BOLD, 100));
    middlepanel.add(ph6);
    JLabel unscrambletitle = new JLabel("Unscramble the letters to find a cat breed!");
    unscrambletitle.setForeground(Color.LIGHT_GRAY);
    unscrambletitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    unscrambletitle.setFont(new Font("Impact", Font.PLAIN, 25));
    middlepanel.add(unscrambletitle);

    pointslabel = new JLabel("Points: " + points);
    pointslabel.setForeground(Color.LIGHT_GRAY);
    pointslabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    pointslabel.setFont(new Font("Impact", Font.PLAIN, 20));
    middlepanel.add(pointslabel);

    JLabel ph7 = new JLabel(" ");
    ph7.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph7.setFont(new Font("Impact", Font.BOLD, 100));
    middlepanel.add(ph7);

    unscramblebreeds = ApiReader.getBreedNames();

    getscrambledbreed = new JButton("Get Scrambled Breed");
    getscrambledbreed.setAlignmentX(Component.CENTER_ALIGNMENT);
    getscrambledbreed.setBackground(Color.CYAN);
    getscrambledbreed.setForeground(Color.DARK_GRAY);
    getscrambledbreed.setFont(new Font("Impact", Font.PLAIN, 40));
    getscrambledbreed.addActionListener(this);
    getscrambledbreed.addMouseListener(this);
    middlepanel.add(getscrambledbreed);

    solutionbutton = new JButton("Show Solution");
    solutionbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
    solutionbutton.setBackground(Color.CYAN);
    solutionbutton.setForeground(Color.DARK_GRAY);
    solutionbutton.setFont(new Font("Impact", Font.PLAIN, 15));
    solutionbutton.addActionListener(this);
    solutionbutton.addMouseListener(this);
    solutionbutton.setVisible(false);
    middlepanel.add(solutionbutton);

    JLabel ph9 = new JLabel(" ");
    ph9.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph9.setFont(new Font("Impact", Font.BOLD, 100));
    middlepanel.add(ph9);

    scrambledbreedlabel = new JLabel(" ");
    scrambledbreedlabel.setForeground(Color.LIGHT_GRAY);
    scrambledbreedlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    scrambledbreedlabel.setFont(new Font("Impact", Font.PLAIN, 30));
    middlepanel.add(scrambledbreedlabel);

    JLabel ph8 = new JLabel(" ");
    ph8.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph8.setFont(new Font("Impact", Font.BOLD, 100));
    middlepanel.add(ph8);

    guessbutton = new JButton("Guess");
    guessbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
    guessbutton.setBackground(Color.CYAN);
    guessbutton.setForeground(Color.DARK_GRAY);
    guessbutton.setFont(new Font("Impact", Font.PLAIN, 40));
    guessbutton.addActionListener(this);
    guessbutton.addMouseListener(this);
    middlepanel.add(guessbutton);

    guessarea = new JTextArea("Type your guess here");
    guessarea.setBackground(Color.DARK_GRAY.brighter());
    guessarea.setForeground(Color.WHITE);
    guessarea.setWrapStyleWord(true);
    guessarea.setLineWrap(true);
    guessarea.setFont(new Font("Impact", Font.PLAIN, 30));
    middlepanel.add(guessarea);

    catinformationpanel = new JPanel();
    catinformationpanel.setLayout(new BoxLayout(catinformationpanel, 1));
    catinformationpanel.setBackground(Color.DARK_GRAY);
    catgamepanel.add(catinformationpanel);

    JLabel ph5 = new JLabel("    ");
    ph5.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph5.setFont(new Font("Impact", Font.BOLD, 275));
    catinformationpanel.add(ph5);
    catinfolabel = new JLabel("Customize your cat");
    catinfolabel.setForeground(Color.LIGHT_GRAY);
    catinfolabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    catinfolabel.setFont(new Font("Impact", Font.PLAIN, 40));
    catinfolabel.setVisible(false);
    catinformationpanel.add(catinfolabel);

    questionpanel = new JPanel();
    questionpanel.setBackground(Color.DARK_GRAY);
    BoxLayout questionpanellayout = new BoxLayout(questionpanel, 1);
    questionpanel.setLayout(questionpanellayout);
    catgamepanel.add(questionpanel);

    JLabel ph1 = new JLabel("    ");
    ph1.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph1.setFont(new Font("Impact", Font.BOLD, 300));
    questionpanel.add(ph1);

    playlabel = new JLabel("Ready to play?");
    playlabel.setForeground(Color.LIGHT_GRAY);
    playlabel.setFont(new Font("Impact", Font.PLAIN, 50));
    playlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    questionpanel.add(playlabel);

    play = new JButton("Play");
    play.setAlignmentX(Component.CENTER_ALIGNMENT);
    play.setBackground(Color.CYAN);
    play.setForeground(Color.DARK_GRAY);
    play.setFont(new Font("Impact", Font.PLAIN, 40));
    play.addActionListener(this);
    play.addMouseListener(this);
    questionpanel.add(play);

    statpanel = new JPanel();
    GridLayout statpanellayout = new GridLayout(1, 3);
    statpanel.setLayout(statpanellayout);
    statpanel.setBackground(Color.DARK_GRAY);
    catgamepanel.add(statpanel);

    foodpanel = new JPanel();
    foodpanel.setLayout(new BoxLayout(foodpanel, 1));
    foodpanel.setBackground(Color.DARK_GRAY);
    statpanel.add(foodpanel);
    foodpanel.setVisible(false);
    JLabel ph2 = new JLabel(" ");
    ph2.setBackground(Color.DARK_GRAY);
    ph2.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph2.setFont(new Font("Impact", Font.BOLD, 275));
    foodpanel.add(ph2);
    foodpixels = new JLabel[10];
    for (int i = 0; i < foodpixels.length; i++) {
      foodpixels[i] = new JLabel("       ");
      foodpixels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      foodpixels[i].setOpaque(true);
      foodpixels[i].setBackground(Color.WHITE);
      foodpanel.add(foodpixels[i]);
    }
    JLabel foodlabel = new JLabel("Food");
    foodlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    foodlabel.setForeground(new Color(210, 105, 30));
    foodlabel.setFont(new Font("Impact", Font.BOLD, 40));
    foodpanel.add(foodlabel);

    waterpanel = new JPanel();
    waterpanel.setLayout(new BoxLayout(waterpanel, 1));
    waterpanel.setBackground(Color.DARK_GRAY);
    statpanel.add(waterpanel);
    waterpanel.setVisible(false);
    JLabel ph3 = new JLabel(" ");
    ph3.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph3.setFont(new Font("Impact", Font.BOLD, 275));
    waterpanel.add(ph3);
    waterpixels = new JLabel[10];
    for (int i = 0; i < waterpixels.length; i++) {
      waterpixels[i] = new JLabel("       ");
      waterpixels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      waterpixels[i].setOpaque(true);
      waterpixels[i].setBackground(Color.WHITE);
      waterpanel.add(waterpixels[i]);
    }
    JLabel waterlabel = new JLabel("Water");
    waterlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    waterlabel.setForeground(Color.CYAN);
    waterlabel.setFont(new Font("Impact", Font.BOLD, 40));
    waterpanel.add(waterlabel);

    playpanel = new JPanel();
    playpanel.setLayout(new BoxLayout(playpanel, 1));
    playpanel.setBackground(Color.DARK_GRAY);
    statpanel.add(playpanel);
    playpanel.setVisible(false);
    JLabel ph4 = new JLabel(" ");
    ph4.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph4.setFont(new Font("Impact", Font.BOLD, 275));
    playpanel.add(ph4);
    playpixels = new JLabel[10];
    for (int i = 0; i < playpixels.length; i++) {
      playpixels[i] = new JLabel("       ");
      playpixels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
      playpixels[i].setOpaque(true);
      playpixels[i].setBackground(Color.WHITE);
      playpanel.add(playpixels[i]);
    }
    JLabel plabel = new JLabel("Play");
    plabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    plabel.setForeground(Color.GREEN);
    plabel.setFont(new Font("Impact", Font.BOLD, 40));
    playpanel.add(plabel);

    Facts fax = null;
    try {
      fax = new Facts();
    } catch (IOException e) {
      e.printStackTrace();
    }
    catfactspanel = fax.getScrollPane();
    tabbedpanel.addTab("Cat Facts", tabicon, catfactspanel);

    catbreedpanel = new JPanel();
    catbreedpanel.setLayout(new BoxLayout(catbreedpanel, 1));
    catbreedpanel.setBackground(Color.DARK_GRAY);
    JLabel ph10 = new JLabel("    ");
    ph10.setAlignmentX(Component.CENTER_ALIGNMENT);
    ph10.setFont(new Font("Impact", Font.BOLD, 275));
    catbreedpanel.add(ph10);
    JLabel breedsloading = new JLabel("Preparing Breeds");
    breedsloading.setAlignmentX(Component.CENTER_ALIGNMENT);
    breedsloading.setFont(new Font("Impact", Font.PLAIN, 50));
    breedsloading.setForeground(Color.LIGHT_GRAY);
    catbreedpanel.add(breedsloading);
    JLabel breedimagesloaded = new JLabel("Please wait");
    breedimagesloaded.setAlignmentX(Component.CENTER_ALIGNMENT);
    breedimagesloaded.setFont(new Font("Impact", Font.PLAIN, 15));
    breedimagesloaded.setForeground(Color.LIGHT_GRAY);
    catbreedpanel.add(breedimagesloaded);
    JLabel breedgif = new JLabel(new ImageIcon("./ui/infinityloader3.gif"));
    breedgif.setPreferredSize(new Dimension(breedgif.getIcon().getIconWidth() / 2,
        breedgif.getIcon().getIconHeight() / 2));
    breedgif.setAlignmentX(Component.CENTER_ALIGNMENT);
    catbreedpanel.add(breedgif);

    tabbedpanel.addTab("Cat Breeds", tabicon, catbreedpanel);

    SwingWorker<Void, Void> breedworker = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        catbreedpanel = new Breeds();
        tabbedpanel.removeTabAt(3);
        tabbedpanel.addTab("Cat Breeds", tabicon, catbreedpanel);
        tabbedpanel.addTab("About", tabicon, aboutpanel);
        return null;
      }
    };
    breedworker.execute();
    tabbedpanel.addTab("Cat Breeds", tabicon, catbreedpanel);

    // about panel
    aboutpanel = new JPanel();
    aboutpanel.setLayout(new GridLayout(1, 2));
    aboutpanel.setBackground(Color.DARK_GRAY);
    tabbedpanel.add("About", aboutpanel);

    JPanel leftpanel = new JPanel();
    leftpanel.setLayout(new BoxLayout(leftpanel, 1));
    leftpanel.setBackground(Color.DARK_GRAY);

    JPanel rightpanel = new JPanel();
    rightpanel.setLayout(new BoxLayout(rightpanel, 1));
    rightpanel.setBackground(Color.DARK_GRAY);

    aboutpanel.add(leftpanel);
    aboutpanel.add(rightpanel);

    JLabel placeholder3 = new JLabel(" ");
    placeholder3.setAlignmentX(Component.CENTER_ALIGNMENT);
    placeholder3.setAlignmentY(Component.CENTER_ALIGNMENT);
    placeholder3.setFont(new Font("Impact", Font.PLAIN, 100));
    placeholder3.setForeground(Color.WHITE);
    leftpanel.add(placeholder3);
    JLabel version = new JLabel("Version 1.0");
    version.setAlignmentX(Component.CENTER_ALIGNMENT);
    version.setAlignmentY(Component.CENTER_ALIGNMENT);
    version.setFont(new Font("Impact", Font.PLAIN, 50));
    version.setForeground(Color.WHITE);
    leftpanel.add(version);

    patchnotesbutton = new JButton("Show Patch Notes");
    patchnotesbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
    patchnotesbutton.setBackground(Color.CYAN);
    patchnotesbutton.setForeground(Color.DARK_GRAY);
    patchnotesbutton.setFont(new Font("Impact", Font.PLAIN, 20));
    patchnotesbutton.addActionListener(this);
    patchnotesbutton.addMouseListener(this);
    leftpanel.add(patchnotesbutton);

    JLabel placeholder7 = new JLabel(" ");
    placeholder7.setAlignmentX(Component.CENTER_ALIGNMENT);
    placeholder7.setAlignmentY(Component.CENTER_ALIGNMENT);
    placeholder7.setFont(new Font("Impact", Font.PLAIN, 100));
    placeholder7.setForeground(Color.WHITE);
    leftpanel.add(placeholder7);

    datelabel = new JLabel("4/25/2021");
    datelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    datelabel.setAlignmentY(Component.CENTER_ALIGNMENT);
    datelabel.setFont(new Font("Impact", Font.PLAIN, 30));
    datelabel.setForeground(Color.WHITE);
    datelabel.setVisible(false);
    leftpanel.add(datelabel);

    patchnotes = new JLabel("- Added 2 new games and made GUI enhancements");
    patchnotes.setAlignmentX(Component.CENTER_ALIGNMENT);
    patchnotes.setAlignmentY(Component.CENTER_ALIGNMENT);
    patchnotes.setFont(new Font("Impact", Font.PLAIN, 30));
    patchnotes.setForeground(Color.WHITE);
    patchnotes.setVisible(false);
    leftpanel.add(patchnotes);

    JLabel placeholder5 = new JLabel(" ");
    placeholder5.setAlignmentX(Component.CENTER_ALIGNMENT);
    placeholder5.setAlignmentY(Component.CENTER_ALIGNMENT);
    placeholder5.setFont(new Font("Impact", Font.PLAIN, 100));
    placeholder5.setForeground(Color.WHITE);
    rightpanel.add(placeholder5);

    JLabel designers = new JLabel("Creators");
    designers.setAlignmentX(Component.CENTER_ALIGNMENT);
    designers.setAlignmentY(Component.CENTER_ALIGNMENT);
    designers.setFont(new Font("Impact", Font.PLAIN, 50));
    designers.setForeground(Color.WHITE);
    rightpanel.add(designers);

    JLabel placeholder = new JLabel(" ");
    placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
    placeholder.setAlignmentY(Component.CENTER_ALIGNMENT);
    placeholder.setFont(new Font("Impact", Font.PLAIN, 50));
    placeholder.setForeground(Color.WHITE);
    rightpanel.add(placeholder);

    // about information
    JLabel devon = new JLabel("DEVON BOLDT");
    devon.setAlignmentX(Component.CENTER_ALIGNMENT);
    devon.setAlignmentY(Component.CENTER_ALIGNMENT);
    devon.setFont(new Font("Impact", Font.PLAIN, 40));
    devon.setForeground(Color.WHITE);
    rightpanel.add(devon);
    JLabel harris = new JLabel("HARRIS CHAUDHRY");
    harris.setAlignmentX(Component.CENTER_ALIGNMENT);
    harris.setAlignmentY(Component.CENTER_ALIGNMENT);
    harris.setFont(new Font("Impact", Font.PLAIN, 40));
    harris.setForeground(Color.WHITE);
    rightpanel.add(harris);
    JLabel luke = new JLabel("LUKE HARTLEY");
    luke.setAlignmentX(Component.CENTER_ALIGNMENT);
    luke.setAlignmentY(Component.CENTER_ALIGNMENT);
    luke.setFont(new Font("Impact", Font.PLAIN, 40));
    luke.setForeground(Color.WHITE);
    rightpanel.add(luke);
    JLabel marko = new JLabel("MARKO HINE");
    marko.setAlignmentX(Component.CENTER_ALIGNMENT);
    marko.setAlignmentY(Component.CENTER_ALIGNMENT);
    marko.setFont(new Font("Impact", Font.PLAIN, 40));
    marko.setForeground(Color.WHITE);
    rightpanel.add(marko);
    JLabel mason = new JLabel("MASON MARKER");
    mason.setAlignmentX(Component.CENTER_ALIGNMENT);
    mason.setAlignmentY(Component.CENTER_ALIGNMENT);
    mason.setFont(new Font("Impact", Font.PLAIN, 40));
    mason.setForeground(Color.WHITE);
    rightpanel.add(mason);

    JLabel placeholder2 = new JLabel(" ");
    placeholder2.setAlignmentX(Component.CENTER_ALIGNMENT);
    placeholder2.setAlignmentY(Component.CENTER_ALIGNMENT);
    placeholder2.setFont(new Font("Impact", Font.PLAIN, 50));
    placeholder2.setForeground(Color.WHITE);
    rightpanel.add(placeholder2);

    JLabel jmu = new JLabel("JMU");
    jmu.setAlignmentX(Component.CENTER_ALIGNMENT);
    jmu.setAlignmentY(Component.CENTER_ALIGNMENT);
    jmu.setFont(new Font("Impact", Font.PLAIN, 25));
    jmu.setForeground(Color.WHITE);
    rightpanel.add(jmu);
    JLabel prof = new JLabel("PROF. RILEY");
    prof.setAlignmentX(Component.CENTER_ALIGNMENT);
    prof.setAlignmentY(Component.CENTER_ALIGNMENT);
    prof.setFont(new Font("Impact", Font.PLAIN, 25));
    prof.setForeground(Color.WHITE);
    rightpanel.add(prof);
    JLabel class1 = new JLabel("CS 345");
    class1.setAlignmentX(Component.CENTER_ALIGNMENT);
    class1.setAlignmentY(Component.CENTER_ALIGNMENT);
    class1.setFont(new Font("Impact", Font.PLAIN, 25));
    class1.setForeground(Color.WHITE);
    rightpanel.add(class1);

    // scroller panel with cat images
    scrollerimages = new JPanel();
    scrollerimages.setLayout(new BoxLayout(scrollerimages, 1));
    scrollerimages.setBackground(Color.DARK_GRAY);
    JScrollPane scrollerpanel = new JScrollPane(scrollerimages);
    scrollerpanel.getVerticalScrollBar().setUnitIncrement(12);
    scrollerpanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollerpanel.setPreferredSize(new Dimension(325, mainframe.getHeight() - 50));

    // panel containing all editing features
    editpanel = new JPanel();
    editpanel.setBackground(Color.DARK_GRAY);
    editpanel.setPreferredSize(new Dimension(1200, mainframe.getHeight() - 300));
    editpanel.setLayout(new BoxLayout(editpanel, 1));

    editoptionpanel = new JPanel();
    editoptionpanel.setPreferredSize(new Dimension(500, 500));
    editoptionpanel.setBackground(Color.DARK_GRAY);
    GridLayout editoptionlayout = new GridLayout(3, 3);
    editoptionlayout.setVgap(10);
    editoptionlayout.setHgap(100);
    editoptionpanel.setLayout(editoptionlayout);
    editoptionpanel.setVisible(false);

    // main split panel
    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollerpanel, editpanel);
    split.setDividerLocation(375);
    catmemespanel.add(split);

    // obtains and stores all cat pictures
    populatecatpics();

    editedimage = new JLabel("Click an image to begin");
    editedimage.setForeground(Color.WHITE);
    editedimage.setAlignmentX(Component.CENTER_ALIGNMENT);
    editedimage.setAlignmentY(Component.CENTER_ALIGNMENT);
    editedimage.setFont(new Font("Impact", Font.PLAIN, 30));
    editpanel.add(editedimage);
    editpanel.add(editoptionpanel);

    toptext = new JTextArea("Top Text", 3, 2);
    toptext.setBackground(Color.LIGHT_GRAY.darker());
    toptext.setForeground(Color.WHITE);
    toptext.setPreferredSize(new Dimension(100, 20));
    toptext.setWrapStyleWord(true);
    toptext.setLineWrap(true);
    toptext.setFont(new Font("Impact", Font.PLAIN, 30));
    editoptionpanel.add(toptext);

    bottomtext = new JTextArea("Bottom Text", 3, 2);
    bottomtext.setBackground(Color.LIGHT_GRAY.darker());
    bottomtext.setForeground(Color.WHITE);
    bottomtext.setPreferredSize(new Dimension(100, 20));
    bottomtext.setWrapStyleWord(true);
    bottomtext.setLineWrap(true);
    bottomtext.setFont(new Font("Impact", Font.PLAIN, 30));
    editoptionpanel.add(bottomtext);

    JLabel fontcolorlabel = new JLabel("Choose a Font Color Below");
    fontcolorlabel.setFont(new Font("Impact", Font.PLAIN, 25));
    fontcolorlabel.setForeground(Color.WHITE);
    editoptionpanel.add(fontcolorlabel);

    applytext = new JButton("Apply Text");
    applytext.setBackground(Color.CYAN);
    applytext.setForeground(Color.DARK_GRAY);
    applytext.setFont(new Font("Impact", Font.PLAIN, 40));
    applytext.addActionListener(this);
    applytext.addMouseListener(this);
    editoptionpanel.add(applytext);

    // available font colors
    DefaultListModel<String> availablefontcolors = new DefaultListModel<String>();
    availablefontcolors.addElement("      White");
    availablefontcolors.addElement("      Black");
    availablefontcolors.addElement("      Blue");
    availablefontcolors.addElement("      Green");
    availablefontcolors.addElement("      Magenta");

    fontcolor = new JList<>(availablefontcolors);
    fontcolor.setFont(new Font("Impact", Font.PLAIN, 18));
    fontcolor.setForeground(Color.WHITE);
    fontcolor.setBackground(Color.DARK_GRAY);
    fontcolor.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    fontcolor.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    editoptionpanel.add(fontcolor);

    savebutton = new JButton("Save This Meme");
    savebutton.setBackground(Color.CYAN);
    savebutton.setForeground(Color.DARK_GRAY);
    savebutton.setFont(new Font("Impact", Font.PLAIN, 40));
    savebutton.addActionListener(this);
    savebutton.addMouseListener(this);
    editoptionpanel.add(savebutton);

    uploadbutton = new JButton("   Upload Image   ");
    uploadbutton.setFont(new Font("Impact", Font.PLAIN, 43));
    uploadbutton.setAlignmentX(Component.RIGHT_ALIGNMENT);
    uploadbutton.addActionListener(this);
    uploadbutton.addMouseListener(this);
    uploadbutton.setForeground(Color.DARK_GRAY);
    uploadbutton.setBackground(Color.CYAN);
    scrollerimages.add(uploadbutton);

    refresh = new JButton("Refresh Pictures");
    refresh.setFont(new Font("Impact", Font.PLAIN, 43));
    refresh.setAlignmentX(Component.RIGHT_ALIGNMENT);
    refresh.addActionListener(this);
    refresh.addMouseListener(this);
    refresh.setForeground(Color.DARK_GRAY);
    refresh.setBackground(Color.CYAN);
    scrollerimages.add(refresh);

    imagebuttons = new JButton[catpics.length];
    for (int i = 0; i < catpics.length; i++) {
      try {
        imagebuttons[i] = new JButton(
            new ImageIcon(catpics[i].getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING)));
        imagebuttons[i].setBackground(Color.white);
        imagebuttons[i].setBorderPainted(false);
        imagebuttons[i].setContentAreaFilled(false);
        imagebuttons[i].addActionListener(this);
        imagebuttons[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
        imagebuttons[i].setName(String.valueOf(i));
        scrollerimages.add(imagebuttons[i]);
      } catch (NullPointerException e) {
      }
    }
    loadworker.cancel(true);
    mainframe.revalidate();
    mainframe.repaint();
    mainframe.pack();
    loadingframe.setVisible(false);
    mainframe.setVisible(true);
  }

  @SuppressWarnings("static-access")
  public void populatecatpics() {
    mainframe.setTitle("Loading Cat Pictures... 0.0%");
    long startpopulation = System.nanoTime();
    ApiReader api = new ApiReader();
    ArrayList<BufferedImage> images = new ArrayList<>();
    try {
      images = api.getRandomImages(10, mainframe);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    catpics = images.toArray(new BufferedImage[images.size()]);
    long endpopulation = System.nanoTime();
    System.out.println("[*] time to load and resize " + catpics.length + " images: "
        + TimeUnit.NANOSECONDS.toMillis(endpopulation - startpopulation) + "ms");
    mainframe.setTitle("Cats V1");
  }

  public void showSolution() {
    SwingWorker<Void, Void> s = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        hideScrambleButtons();
        panelph1.setBackground(Color.DARK_GRAY);
        panelph2.setBackground(Color.DARK_GRAY);
        scrambledbreedlabel.setText("Solution: " + actualbreed);
        System.out.println(actualbreed);
        Thread.sleep(3000);
        String current = unscramblebreeds.get(randomInt(0, unscramblebreeds.size()));
        while (current.contains(" ") || current.contains("-")) {
          current = unscramblebreeds.get(randomInt(0, unscramblebreeds.size()));
        }
        actualbreed = current.toLowerCase();
        char[] chars = actualbreed.toCharArray();
        ArrayList<Character> toShuffle = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
          toShuffle.add(chars[i]);
        }
        Collections.shuffle(toShuffle);
        String shuffled = "";
        for (char c : toShuffle) {
          shuffled += c;
        }
        scrambledbreedlabel.setText(shuffled);
        scrambledbreed = shuffled;
        showScrambleButtons();
        return null;
      }
    };
    s.execute();
  }

  public void hideScrambleButtons() {
    solutionbutton.setVisible(false);
    guessbutton.setVisible(false);
    getscrambledbreed.setVisible(false);
  }

  public void showScrambleButtons() {
    solutionbutton.setVisible(true);
    guessbutton.setVisible(true);
    getscrambledbreed.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton j = (JButton) e.getSource();

    if (!editoptionpanel.isVisible()) {
      editoptionpanel.setVisible(true);
    }

    if (j.getText().equals("Save This Meme")) {
      hasBeenEdited = false;
      ImageUtil.saveImage(editedimage.getIcon());
    } else if (j.getText().equals("Apply Text")) {
      if (!toptext.getText().equals("Top Text") || !bottomtext.getText().equals("Bottom Text")) {
        hasBeenEdited = true;

        editedimage.setIcon(new ImageIcon(currentImage));

        BufferedImage toSet = copyOf(currentImage);
        Graphics2D g = toSet.createGraphics();
        g.setFont(new Font("Impact", Font.BOLD, 40));

        Color fontc = Color.WHITE;
        try {
          if (fontcolor.getSelectedValue().contains("Blue")) {
            fontc = Color.BLUE;
          } else if (fontcolor.getSelectedValue().contains("White")) {
            fontc = Color.WHITE;
          } else if (fontcolor.getSelectedValue().contains("Black")) {
            fontc = Color.BLACK;
          } else if (fontcolor.getSelectedValue().contains("Green")) {
            fontc = Color.GREEN;
          } else if (fontcolor.getSelectedValue().contains("Magenta")) {
            fontc = Color.MAGENTA;
          }
        } catch (NullPointerException e1) {
        }
        g.setColor(fontc);

        drawCenteredString(g, toptext.getText().toUpperCase(), 0,
            -(editedimage.getHeight() / 2) + 40, editedimage.getWidth(), editedimage.getHeight());
        drawCenteredString(g, bottomtext.getText().toUpperCase(), 0,
            (editedimage.getHeight() / 2) - 50, editedimage.getWidth(), editedimage.getHeight());

        imageToSave = copyOf(toSet);
        editedimage.setIcon(new ImageIcon(toSet));
      }
    } else if (j.getText().equals("Refresh Pictures")) {
      refresh();
    } else if (j.getText().equals("   Upload Image   ")) {
      ImageIcon loadedImage = ImageUtil.loadImageButton();
      currentImage = toBuffered(loadedImage.getImage());
      editedimage.setIcon(loadedImage);
      editedimage.setText("");
    } else if (j.getText().equals("Play")) {
      playlabel.setText("Choose your breed!");
      play.setVisible(false);

      calico = new JButton("Calico");
      calico.setAlignmentX(Component.CENTER_ALIGNMENT);
      calico.setBackground(Color.CYAN);
      calico.setForeground(Color.DARK_GRAY);
      calico.setFont(new Font("Impact", Font.PLAIN, 40));
      calico.addActionListener(this);
      calico.addMouseListener(this);
      questionpanel.add(calico);

      sph = new JButton("Sphynx");
      sph.setAlignmentX(Component.CENTER_ALIGNMENT);
      sph.setBackground(Color.CYAN);
      sph.setForeground(Color.DARK_GRAY);
      sph.setFont(new Font("Impact", Font.PLAIN, 40));
      sph.addActionListener(this);
      sph.addMouseListener(this);
      questionpanel.add(sph);

      tabby = new JButton("Tabby");
      tabby.setAlignmentX(Component.CENTER_ALIGNMENT);
      tabby.setBackground(Color.CYAN);
      tabby.setForeground(Color.DARK_GRAY);
      tabby.setFont(new Font("Impact", Font.PLAIN, 40));
      tabby.addActionListener(this);
      tabby.addMouseListener(this);
      questionpanel.add(tabby);

      catinfolabel.setVisible(true);
      showstats();
    } else if (j.getText().equals("Calico") || j.getText().equals("Sphynx")
        || j.getText().equals("Tabby")) {
      custombreed = j.getText();
      playlabel.setText("Name your cat!");
      calico.setVisible(false);
      sph.setVisible(false);
      tabby.setVisible(false);

      questionpanel.setLayout(new FlowLayout());
      namearea = new JTextArea("Name", 1, 1);
      namearea.setBackground(Color.DARK_GRAY);
      namearea.setForeground(Color.LIGHT_GRAY);
      namearea.setSize(300, 50);
      namearea.setWrapStyleWord(false);
      namearea.setLineWrap(true);
      namearea.setFont(new Font("Impact", Font.PLAIN, 30));
      questionpanel.add(namearea);

      submitname = new JButton("Submit");
      submitname.setAlignmentX(Component.CENTER_ALIGNMENT);
      submitname.setBackground(Color.CYAN);
      submitname.setForeground(Color.DARK_GRAY);
      submitname.setFont(new Font("Impact", Font.PLAIN, 40));
      submitname.addActionListener(this);
      submitname.addMouseListener(this);
      questionpanel.add(submitname);

    } else if (j.getText().equals("Submit")) {
      name = namearea.getText();
      namearea.setVisible(false);
      submitname.setVisible(false);
      questionpanel.setLayout(new BoxLayout(questionpanel, 1));

      catinfolabel.setText("Your cat's information");
      catinfolabel.setForeground(Color.CYAN);

      JLabel ph4 = new JLabel(" ");
      ph4.setAlignmentX(Component.CENTER_ALIGNMENT);
      ph4.setFont(new Font("Impact", Font.BOLD, 40));
      catinformationpanel.add(ph4);

      JLabel namelabel = new JLabel("Name: " + name);
      namelabel.setForeground(Color.LIGHT_GRAY);
      namelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      namelabel.setFont(new Font("Impact", Font.PLAIN, 30));
      catinformationpanel.add(namelabel);

      JLabel breedlabel = new JLabel("Breed: " + custombreed);
      breedlabel.setForeground(Color.LIGHT_GRAY);
      breedlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      breedlabel.setFont(new Font("Impact", Font.PLAIN, 30));
      catinformationpanel.add(breedlabel);

      statuslabel = new JLabel("Status: " + getHealth());
      statuslabel.setForeground(Color.LIGHT_GRAY);
      statuslabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      statuslabel.setFont(new Font("Impact", Font.PLAIN, 30));
      catinformationpanel.add(statuslabel);

      playlabel.setText("What would you like to do with your cat?");
      playlabel.setFont(new Font("Impact", Font.PLAIN, 25));

      feedbutton = new JButton("Feed");
      feedbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
      feedbutton.setBackground(Color.CYAN);
      feedbutton.setForeground(Color.DARK_GRAY);
      feedbutton.setFont(new Font("Impact", Font.PLAIN, 40));
      feedbutton.addActionListener(this);
      feedbutton.addMouseListener(this);
      questionpanel.add(feedbutton);

      waterbutton = new JButton("Give Water");
      waterbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
      waterbutton.setBackground(Color.CYAN);
      waterbutton.setForeground(Color.DARK_GRAY);
      waterbutton.setFont(new Font("Impact", Font.PLAIN, 40));
      waterbutton.addActionListener(this);
      waterbutton.addMouseListener(this);
      questionpanel.add(waterbutton);

      playbutton = new JButton("Play With");
      playbutton.setAlignmentX(Component.CENTER_ALIGNMENT);
      playbutton.setBackground(Color.CYAN);
      playbutton.setForeground(Color.DARK_GRAY);
      playbutton.setFont(new Font("Impact", Font.PLAIN, 40));
      playbutton.addActionListener(this);
      playbutton.addMouseListener(this);
      questionpanel.add(playbutton);

    } else if (j.getText().equals("Feed")) {
      foodButtonPressed();
    } else if (j.getText().equals("Give Water")) {
      waterButtonPressed();
    } else if (j.getText().equals("Play With")) {
      playButtonPressed();
    } else if (j.getText().equals("Get Scrambled Breed")) {

      panelph1.setBackground(Color.DARK_GRAY);
      panelph2.setBackground(Color.DARK_GRAY);

      if (!solutionbutton.isVisible()) {
        solutionbutton.setVisible(true);
      }
      String current = unscramblebreeds.get(randomInt(0, unscramblebreeds.size()));
      while (current.contains(" ") || current.contains("-")) {
        current = unscramblebreeds.get(randomInt(0, unscramblebreeds.size()));
      }
      actualbreed = current.toLowerCase();
      char[] chars = actualbreed.toCharArray();
      ArrayList<Character> toShuffle = new ArrayList<>();
      for (int i = 0; i < chars.length; i++) {
        toShuffle.add(chars[i]);
      }
      Collections.shuffle(toShuffle);
      String shuffled = "";
      for (char c : toShuffle) {
        shuffled += c;
      }

      scrambledbreedlabel.setText(shuffled);
      scrambledbreed = shuffled;

    } else if (j.getText().equals("Guess")) {
      if (guessarea.getText().equalsIgnoreCase(actualbreed)) {
        increasePoints();

        String current = unscramblebreeds.get(randomInt(0, unscramblebreeds.size()));
        while (current.contains(" ") || current.contains("-")) {
          current = unscramblebreeds.get(randomInt(0, unscramblebreeds.size()));
        }
        actualbreed = current.toLowerCase();
        char[] chars = actualbreed.toCharArray();
        ArrayList<Character> toShuffle = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
          toShuffle.add(chars[i]);
        }
        Collections.shuffle(toShuffle);
        String shuffled = "";
        for (char c : toShuffle) {
          shuffled += c;
        }

        scrambledbreedlabel.setText(shuffled);
        scrambledbreed = shuffled;
        panelph1.setBackground(Color.GREEN);
        panelph2.setBackground(Color.GREEN);
      } else {
        panelph1.setBackground(Color.RED.darker());
        panelph2.setBackground(Color.RED.darker());
      }
    } else if (j.getText().equals("Show Solution")) {
      panelph1.setBackground(Color.DARK_GRAY);
      panelph2.setBackground(Color.DARK_GRAY);
      showSolution();
      try {
        scrambleworker.cancel(true);
      } catch (NullPointerException e1) {
      }
    } else if (j.getText().equals("Show Patch Notes")) {
      patchnotes.setVisible(true);
      datelabel.setVisible(true);
      j.setText("Hide Patch Notes");
    } else if (j.getText().equals("Hide Patch Notes")) {
      patchnotes.setVisible(false);
      datelabel.setVisible(false);
      j.setText("Show Patch Notes");
    } else {
      currentImage = catpics[Integer.valueOf(j.getName())];
      editedimage.setText("");
      editedimage.setIcon(new ImageIcon(currentImage));
    }

    if (healthValue() == 29) {
      cancelWorkers();
      fillStats();
      hideActions();
      playlabel.setText("Congrats! " + name + " is up to health!");
      JLabel restart = new JLabel("Restart Cats to play again");
      restart.setForeground(Color.LIGHT_GRAY);
      restart.setAlignmentX(Component.CENTER_ALIGNMENT);
      restart.setFont(new Font("Impact", Font.PLAIN, 30));
      questionpanel.add(restart);
      playlabel.setText("Congrats! " + name + " is up to health!");
      statuslabel.setText("Status: Feeling Excellent!");
    }
    mainframe.repaint();
    mainframe.revalidate();
    mainframe.pack();
  }

  public void drawCenteredString(Graphics2D page, String s, int x, int y, int width, int height) {
    java.awt.FontMetrics fm = page.getFontMetrics(page.getFont());
    java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, page);
    int textHeight = (int) (rect.getHeight());
    int textWidth = (int) (rect.getWidth());
    int textX = x + (width - textWidth) / 2;
    int textY = y + (height - textHeight) / 2 + fm.getAscent();
    page.drawString(s, textX, textY);
  }

  /**
   * Safely converts an Image to a BufferedImage.
   * 
   * @param image the image to convert
   * @return a new BufferedImage
   */
  public static BufferedImage toBuffered(Image image) {
    BufferedImage newImage =
        new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = newImage.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return newImage;
  }

  static BufferedImage copyOf(BufferedImage bi) {
    ColorModel cm = bi.getColorModel();
    boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    WritableRaster raster = bi.copyData(null);
    return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
  }

  public void fillStats() {
    for (JLabel j : foodpixels) {
      j.setBackground(new Color(210, 105, 30));
    }
    for (JLabel j : waterpixels) {
      j.setBackground(Color.CYAN);
    }
    for (JLabel j : playpixels) {
      j.setBackground(Color.GREEN);
    }
  }

  public void refresh() {
    scrollerimages.removeAll();
    populatecatpics();
    scrollerimages.add(uploadbutton);
    scrollerimages.add(refresh);
    imagebuttons = new JButton[catpics.length];
    for (int i = 0; i < catpics.length; i++) {
      imagebuttons[i] = new JButton(
          new ImageIcon(catpics[i].getScaledInstance(300, 200, Image.SCALE_AREA_AVERAGING)));
      imagebuttons[i].setBackground(Color.white);
      imagebuttons[i].setBorderPainted(false);
      imagebuttons[i].setContentAreaFilled(false);
      imagebuttons[i].addActionListener(this);
      imagebuttons[i].setAlignmentX(Component.RIGHT_ALIGNMENT);
      imagebuttons[i].setName(String.valueOf(i));
      scrollerimages.add(imagebuttons[i]);
    }
    mainframe.revalidate();
    mainframe.repaint();
  }

  public void increasePoints() {
    points++;
    pointslabel.setText("Points: " + points);
  }

  public int healthValue() {
    return getPlayLevel() + getFoodLevel() + getWaterLevel();
  }

  public void foodButtonPressed() {
    SwingWorker<Void, Void> s = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        hideActions();
        playlabel.setText("Feeding " + name);
        for (int i = 0; i < 4; i++) {
          Thread.sleep(400);
          playlabel.setText(playlabel.getText() + ".");
        }
        playlabel.setText("What would you like to do with your cat?");
        increaseFood();
        updateStatus();
        showActions();
        return null;
      }
    };
    this.feedworker = s;
    s.execute();
  }

  public static int randomInt(int min, int max) {
    int random = (int) (min + Math.random() * (max - min));
    return random;
  }

  public void waterButtonPressed() {
    SwingWorker<Void, Void> s = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        hideActions();
        playlabel.setText("Filling up " + name + "'s water bowl");
        for (int i = 0; i < 4; i++) {
          Thread.sleep(300);
          playlabel.setText(playlabel.getText() + ".");
        }
        playlabel.setText("What would you like to do with your cat?");
        increaseWater();
        updateStatus();
        showActions();
        return null;
      }
    };
    this.waterworker = s;
    s.execute();
  }

  public void playButtonPressed() {
    SwingWorker<Void, Void> s = new SwingWorker<Void, Void>() {
      @Override
      protected Void doInBackground() throws Exception {
        hideActions();
        playlabel.setText("Playing fetch with " + name);
        for (int i = 0; i < 4; i++) {
          Thread.sleep(200);
          playlabel.setText(playlabel.getText() + ".");
        }
        playlabel.setText("What would you like to do with your cat?");
        increasePlay();
        updateStatus();
        showActions();
        return null;
      }
    };
    this.playworker = s;
    s.execute();
  }

  public void cancelWorkers() {
    feedworker.cancel(true);
    waterworker.cancel(true);
    playworker.cancel(true);
  }

  public void showActions() {
    feedbutton.setVisible(true);
    waterbutton.setVisible(true);
    playbutton.setVisible(true);
  }

  public void hideActions() {
    feedbutton.setVisible(false);
    waterbutton.setVisible(false);
    playbutton.setVisible(false);
  }

  public void increaseFood() {
    for (int i = foodpixels.length - 1; i >= 0; i--) {
      if (foodpixels[i].getBackground().getRed() == 255
          && foodpixels[i].getBackground().getBlue() == 255
          && foodpixels[i].getBackground().getGreen() == 255) {
        foodpixels[i].setBackground(new Color(210, 105, 30));
        break;
      }
    }
  }

  public void increaseWater() {
    for (int i = waterpixels.length - 1; i >= 0; i--) {
      if (waterpixels[i].getBackground().getRed() == 255
          && waterpixels[i].getBackground().getBlue() == 255
          && waterpixels[i].getBackground().getGreen() == 255) {
        waterpixels[i].setBackground(Color.CYAN);
        break;
      }
    }
  }

  public void increasePlay() {
    for (int i = playpixels.length - 1; i >= 0; i--) {
      if (playpixels[i].getBackground().getRed() == 255
          && playpixels[i].getBackground().getBlue() == 255
          && playpixels[i].getBackground().getGreen() == 255) {
        playpixels[i].setBackground(Color.GREEN);
        break;
      }
    }
  }

  public int getFoodLevel() {
    int count = 0;
    for (int i = 0; i < foodpixels.length; i++) {
      if (foodpixels[i].getBackground().getRed() != 255
          || foodpixels[i].getBackground().getBlue() != 255
          || foodpixels[i].getBackground().getGreen() != 255) {
        count++;
      }
    }
    return count;
  }

  public int getWaterLevel() {
    int count = 0;
    for (int i = 0; i < waterpixels.length; i++) {
      if (waterpixels[i].getBackground().getRed() != 255
          || waterpixels[i].getBackground().getBlue() != 255
          || waterpixels[i].getBackground().getGreen() != 255) {
        count++;
      }
    }
    return count;
  }

  public int getPlayLevel() {
    int count = 0;
    for (int i = 0; i < playpixels.length; i++) {
      if (playpixels[i].getBackground().getRed() != 255
          || playpixels[i].getBackground().getBlue() != 255
          || playpixels[i].getBackground().getGreen() != 255) {
        count++;
      }
    }
    return count;
  }

  public void updateStatus() {
    statuslabel.setText("Status: " + getHealth());
  }

  public String getHealth() {
    int healthlevel = getWaterLevel() + getFoodLevel() + getPlayLevel();
    String health = "Very Healthy";
    if (healthlevel < 5) {
      health = "Very Unhealthy";
    } else if (healthlevel < 10) {
      health = "Unhealthy";
    } else if (healthlevel < 15) {
      health = "Under the weather";
    } else if (healthlevel < 20) {
      health = "Feeling better";
    } else if (healthlevel < 25) {
      health = "Healthy";
    } else if (healthlevel < 30) {
      health = "Very Healthy";
    } else if (healthlevel == 29) {
      health = "Feeling Excellent!";
    }
    return health;
  }

  public void showstats() {
    foodpanel.setVisible(true);
    waterpanel.setVisible(true);
    playpanel.setVisible(true);
  }

  public void hidestats() {
    foodpanel.setVisible(false);
    waterpanel.setVisible(false);
    playpanel.setVisible(false);
  }

  public void hideImageButtons() {
    for (JButton j : imagebuttons) {
      j.setVisible(false);
    }
  }

  public void showImageButtons() {
    for (JButton j : imagebuttons) {
      j.setVisible(true);
    }
  }

  public static void main(String[] args) {
    new Display();
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
