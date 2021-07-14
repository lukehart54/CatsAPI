import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Breeds class used to create the Breeds panel and all the components.
 * 
 * @author Luke Hartley
 * @version 0.5
 */
public class Breeds extends JPanel
                implements ListSelectionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private JPanel infoPanel;
    private JPanel starPanel;
    private JPanel tempPanel;

    private JLabel header;
    private JLabel name;
    private JLabel temperament;
    private JLabel lifeSpan;
    private JLabel origin;
    private JLabel altNames;
    private JLabel weightMetric;
    private JLabel allergenic;
    private JLabel image;

    private JLabel starHeader;
    private JLabel affectionStar;
    private JLabel childStar;
    private JLabel energyStar;
    private JLabel dogStar;
    private JLabel shedStar;
    private JLabel socialStar;
    private JLabel healthStar;
    private JLabel intelStar;
    private JLabel vocalStar;
    private JLabel groomStar;
    private JLabel strangeStar;

    private Desktop d;

    // Variables that contain cat breeds info
    ArrayList< Breed > brList;
    private DefaultListModel< Breed > model;
    private JList< Breed > list;
    private JScrollPane listPane;

    /**
     * Breeds constructor.
     */
    public Breeds() {

        setUp();
        createComponents();
        populateList();
        addComponents();
    }

    /**
     * Populates the list of breeds.
     */
    private void populateList() {

        brList = new ArrayList<>();
        brList = ApiReader.getCatBreeds();
        brList.sort( ( b1, b2 ) -> b1.getName().compareTo( b2.getName() ) );
        for ( Breed b : brList ) {
            model.addElement( b );
        }
    }

    /**
     * Sets up breed makes everything visible and size proper.
     */
    private void setUp() {

        setVisible( true );
        setPreferredSize( new Dimension( 1500, 1500 ) );
        setLayout( new GridLayout( 2, 2 ) );
        setBackground( Color.DARK_GRAY );
    }

    /**
     * Creates Components for JPanel Breeds
     */
    private void createComponents() {

        header = new JLabel( "List of Cat Breeds" );
        model = new DefaultListModel< Breed >();
        list = new JList< Breed >( model );
        list.setBackground( Color.DARK_GRAY );
        list.setForeground( Color.LIGHT_GRAY );
        list.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION );
        list.setFont( new Font( "Impact", Font.PLAIN, 30 ) );
        listPane = new JScrollPane( list );

        name = new JLabel( "Name:" );
        lifeSpan = new JLabel( "Life-span:" );
        origin = new JLabel( "Origin:" );
        temperament = new JLabel( "Temperament:" );
        altNames = new JLabel( "Alternate Names:" );
        weightMetric = new JLabel( "Weight:" );
        allergenic = new JLabel( "Hypoallergenic:" );
        image = new JLabel();

        starHeader = new JLabel( "Cat Rating Represented With Stars (1-5)" );
        affectionStar = new JLabel( "Affection Level:" );
        childStar = new JLabel( "Child Friendly:" );
        energyStar = new JLabel( "Energy Level:" );
        dogStar = new JLabel( "Dog Friendly:" );
        shedStar = new JLabel( "Shedding Level:" );
        socialStar = new JLabel( "Social Needs:" );
        healthStar = new JLabel( "Health Issues:" );
        intelStar = new JLabel( "Intelligence:" );
        vocalStar = new JLabel( "Vocalisation:" );
        groomStar = new JLabel( "Grooming:" );
        strangeStar = new JLabel( "Stranger Friendly:" );

        infoPanel = new JPanel();
        infoPanel.setBackground( Color.DARK_GRAY );
        starPanel = new JPanel();
        starPanel.setBackground( Color.DARK_GRAY );
        tempPanel = new JPanel();
        tempPanel.setBackground( Color.DARK_GRAY );

        listPane.setPreferredSize( new Dimension( 200, 200 ) );
        header.setHorizontalAlignment( JLabel.CENTER );
        header.setVerticalAlignment( JLabel.TOP );
        header.setFont( new Font( "Impact", Font.PLAIN, 40 ) );
        header.setForeground( Color.LIGHT_GRAY );
        name.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        name.setForeground( Color.LIGHT_GRAY );
        temperament.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        temperament.setForeground( Color.LIGHT_GRAY );
        lifeSpan.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        lifeSpan.setForeground( Color.LIGHT_GRAY );
        origin.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        origin.setForeground( Color.LIGHT_GRAY );
        starHeader.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        starHeader.setForeground( Color.LIGHT_GRAY );
        affectionStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        affectionStar.setForeground( Color.LIGHT_GRAY );
        childStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        childStar.setForeground( Color.LIGHT_GRAY );
        altNames.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        altNames.setForeground( Color.LIGHT_GRAY );
        weightMetric.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        weightMetric.setForeground( Color.LIGHT_GRAY );
        energyStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        energyStar.setForeground( Color.LIGHT_GRAY );
        dogStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        dogStar.setForeground( Color.LIGHT_GRAY );
        shedStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        shedStar.setForeground( Color.LIGHT_GRAY );
        socialStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        socialStar.setForeground( Color.LIGHT_GRAY );
        healthStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        healthStar.setForeground( Color.LIGHT_GRAY );
        allergenic.setForeground( Color.LIGHT_GRAY );
        allergenic.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        intelStar.setForeground( Color.LIGHT_GRAY );
        intelStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        vocalStar.setForeground( Color.LIGHT_GRAY );
        vocalStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        groomStar.setForeground( Color.LIGHT_GRAY );
        groomStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        strangeStar.setForeground( Color.LIGHT_GRAY );
        strangeStar.setFont( new Font( "Impact", Font.PLAIN, 20 ) );
        infoPanel.setLayout( new BoxLayout( infoPanel, BoxLayout.PAGE_AXIS ) );
        starPanel.setLayout( new BoxLayout( starPanel, BoxLayout.PAGE_AXIS ) );
        list.addListSelectionListener( this );
        image.addMouseListener( this );
        // infoPanel.setBackground(Color.DARK_GRAY);
    }

    /**
     * Adds all components to Breeds Panel.
     */
    private void addComponents() {

        add( listPane );
        add( image );

        infoPanel.add( name );
        infoPanel.add( Box.createRigidArea( new Dimension( 3, 3 ) ) );
        infoPanel.add( temperament );
        infoPanel.add( Box.createRigidArea( new Dimension( 3, 3 ) ) );
        infoPanel.add( lifeSpan );
        infoPanel.add( Box.createRigidArea( new Dimension( 3, 3 ) ) );
        infoPanel.add( origin );
        infoPanel.add( Box.createRigidArea( new Dimension( 3, 3 ) ) );
        infoPanel.add( altNames );
        infoPanel.add( Box.createRigidArea( new Dimension( 3, 3 ) ) );
        infoPanel.add( weightMetric );
        infoPanel.add( Box.createRigidArea( new Dimension( 3, 3 ) ) );
        infoPanel.add( allergenic );
        add( infoPanel );
        starPanel.add( starHeader );
        starPanel.add( affectionStar );
        starPanel.add( childStar );
        starPanel.add( energyStar );
        starPanel.add( dogStar );
        starPanel.add( healthStar );
        starPanel.add( socialStar );
        starPanel.add( shedStar );
        starPanel.add( intelStar );
        starPanel.add( vocalStar );
        starPanel.add( groomStar );
        starPanel.add( strangeStar );
        add( starPanel );

    }

    /**
     * Value changed class to update JLabels
     */
    @Override
    public void valueChanged( ListSelectionEvent e ) {

        String star = "☆ ";
        Breed b = list.getSelectedValue();
        if ( b != null ) {
            name.setText( "Name: " + b.getName() );
            temperament.setText( "Temperament: " + b.getTemperament() );
            lifeSpan.setText( "Life-Span: " + b.getLifeSpan() + " Years" );
            origin.setText( "Origin: " + b.getOrigin() );
            altNames.setText( "Alternate Names: " + b.getAltName() );
            weightMetric.setText( "Weight: " + b.getWeightMetric() + " lbs" );
            allergenic.setText( "Hypoallergenic: " + b.getHypoAllergen() );
            affectionStar.setText( "Affection Level: "
                            + star.repeat( b.getAffectionLevel() ) );
            childStar.setText( "Child Friendly: "
                            + star.repeat( b.getChildFriendly() ) );
            energyStar.setText( "Energy Level: "
                            + star.repeat( b.getEnergyLevel() ) );
            dogStar.setText( "Dog Friendly: "
                            + star.repeat( b.getDogLevel() ) );
            healthStar.setText( "Health Issues: "
                            + star.repeat( b.getHealthIssues() ) );
            socialStar.setText( "Social Needs: "
                            + star.repeat( b.getSocialNeeds() ) );
            shedStar.setText( "Shedding Level: "
                            + star.repeat( b.getShedLevel() ) );
            intelStar.setText( "Intelligence: "
                            + star.repeat( b.getIntelligence() ) );
            vocalStar.setText( "Vocalisation: "
                            + star.repeat( b.getVocalisation() ) );
            groomStar.setText( "Grooming: " + star.repeat( b.getGrooming() ) );
            strangeStar.setText( "Stranger Friendly: "
                            + star.repeat( b.getStrangerFriendly() ) );

            try {
                image.setIcon( new ImageIcon( b.getImage() ) );
            } catch ( NullPointerException e1 ) {
            }
        }
    }

    @Override
    public void mouseClicked( MouseEvent e ) {

        Breed breed = list.getSelectedValue();
        d = Desktop.getDesktop();
        try {
            d.browse( breed.getWikiLink() );
        } catch ( IOException e1 ) {
            System.out.print( "Mouse Clicked Didnt Work" );
        }

    }

    @Override
    public void mousePressed( MouseEvent e ) {

    }

    @Override
    public void mouseReleased( MouseEvent e ) {

    }

    @Override
    public void mouseEntered( MouseEvent e ) {

    }

    @Override
    public void mouseExited( MouseEvent e ) {

    }

}
