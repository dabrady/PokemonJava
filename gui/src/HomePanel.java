import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 12 July 2014 at 9:48 PM.
 *
 * This class models a (hopefully generic) home screen for a Pokemon Java-like application. It contains an array of
 * clickable JLabels that serve as 'buttons' to start a new game, load an old game, or configure the gameplay.
 */

public class HomePanel extends ImagePanel {

    //---- Class & Instance variables ----\\

    private static String DEFAULT_BG = "gui/images/pj_homeScreen.jpg";
    public static int START = 0, LOAD = 1, CONFIG = 2, SECRET = 3;

    private final int NUM_BUTTONS = 4;
    private JLabel[]    labels         = new JLabel[NUM_BUTTONS];
    private ImageIcon[] labelImages    = new ImageIcon[NUM_BUTTONS];
    private String[]    labelImageURLs = new String[NUM_BUTTONS];

    //---- Constructors ----\\

    public HomePanel () {
        // Set up general ImagePanel things.
        super( DEFAULT_BG );
        // General settings.
        setLayout( null );  // use absolute positioning
        // Set preferred size to the dimensions of the background image.
        setPreferredSize( new Dimension( getBackgroundImg().getWidth(), getBackgroundImg().getHeight() ) );
        // Set image URLs to defaults.
        resetImageURLs();
    }

    //---- Utilities ----\\

    public void activate () {
        loadImages();
        createLabels();
        positionLabels();
        addLabels();
        activateListeners();
    }

    private void resetImageURLs () {
        labelImageURLs[START]  = "gui/images/pj_startButton.jpg";
        labelImageURLs[LOAD]   = "gui/images/pj_loadButton.jpg";
        labelImageURLs[CONFIG] = "gui/images/pj_configButton.jpg";
        labelImageURLs[SECRET] = "gui/images/pj_secretButton.gif";
    }

    private void loadImages () {
        labelImages[START]  = new ImageIcon( labelImageURLs[START]  );
        labelImages[LOAD]   = new ImageIcon( labelImageURLs[LOAD]   );
        labelImages[CONFIG] = new ImageIcon( labelImageURLs[CONFIG] );
        labelImages[SECRET] = new ImageIcon( labelImageURLs[SECRET] );
    }

    private void createLabels () {
        labels[START]  = new JLabel( labelImages[START]  );
        labels[LOAD]   = new JLabel( labelImages[LOAD]   );
        labels[CONFIG] = new JLabel( labelImages[CONFIG] );
        labels[SECRET] = new JLabel( labelImages[SECRET] );
    }

    private void positionLabels () {
        labels[START].setBounds (  58, 264, labelImages[START].getIconWidth(),  labelImages[START].getIconHeight()  );
        labels[LOAD].setBounds  ( 517,  26, labelImages[LOAD].getIconWidth(),   labelImages[LOAD].getIconHeight()   );
        labels[CONFIG].setBounds( 502, 111, labelImages[CONFIG].getIconWidth(), labelImages[CONFIG].getIconHeight() );
        labels[SECRET].setBounds( 550, 393, labelImages[SECRET].getIconWidth(), labelImages[SECRET].getIconHeight() );
    }

    private void addLabels () {
        add( labels[START] );
        add( labels[LOAD] );
        add( labels[CONFIG] );
        add( labels[SECRET] );
    }

    public void activateListeners () {
        // Make each label clickable.
        for ( JLabel label : labels ) {
            if ( label != null ) {
                label.addMouseListener( new MouseAdapter() {
                    @Override
                    public void mouseClicked ( MouseEvent e ) {
                        System.out.println( "Clicked!" );
                    }
                } );
                // Make the cursor the pointy hand one, so they appear clickable.
                label.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
            }
        }
    }

    //---- Getters & Setters ----\\


}
