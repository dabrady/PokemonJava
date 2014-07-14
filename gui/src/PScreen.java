import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 13 July 2014 at 10:06 PM.
 *
 * This class models a screen in Pokemon Java. Screens have a background image and clickable JLabels with custom art,
 * serving as buttons. Subclasses should usually only ever override the #resetImageURLs, #resetLabelBehaviors, and
 * #positionLabels methods.
 */

public class PScreen extends ImagePanel {

    //---- Class & Instance variables ----\\

    protected final int NUM_BUTTONS;
    protected JLabel[]    labels;
    protected ImageIcon[] labelImages;
    protected String[]    labelImageURLs;
    protected MouseAdapter[] labelBehaviors;  // An array of MouseAdapters to be added to the labels, making them clickable.

    //---- Constructors ----\\

    public PScreen ( String bgUrl, int numButtons ) {
        // Set up ImagePanel things.
        super( bgUrl );

        // Set up PScreen things.
        NUM_BUTTONS    = numButtons;
        labels         = new JLabel[NUM_BUTTONS];
        labelImages    = new ImageIcon[NUM_BUTTONS];
        labelImageURLs = new String[NUM_BUTTONS];
        labelBehaviors = new MouseAdapter[NUM_BUTTONS];
        resetImageURLs(); // Set image URLs to defaults.
        resetLabelBehaviors(); // Set label behaviors to defaults.

        // General settings.
        setLayout( null );  // use absolute positioning
        // Set preferred size to the dimensions of the background image.
        setPreferredSize( new Dimension( getBackgroundImg().getWidth(), getBackgroundImg().getHeight() ) );
    }

    //---- Utilities ----\\    

    /**
     * Resets the image URLs back to their defaults.
     */
    public void resetImageURLs () {}

    /**
     * Resets the label behaviors back to their defaults.
     */
    public void resetLabelBehaviors () {}

    /**
     * Positions the labels on the screen.
     */
    public void positionLabels () {}

    /**
     * Completely reactivates the screen: reloads label images, recreates the labels, repositions the labels, adds them
     * to the screen, and reactivates their behaviors.
     */
    public void activate () {
        loadImages();
        createLabels();
        positionLabels();
        addLabels();
        activateLabels();
    }

    /**
     * Loads the images given by the label image URLs.
     */
    public void loadImages () {
        for ( int i = 0; i < NUM_BUTTONS; i++ ) {
            labelImages[i] = new ImageIcon( labelImageURLs[i] );
        }
    }

    /**
     * Creates the labels from the label images.
     */
    public void createLabels () {
        for ( int i = 0; i < NUM_BUTTONS; i++ ) {
            labels[i] = new JLabel( labelImages[i] );
        }
    }

    /**
     * Add behaviors (MouseAdapters) to the labels, making them clickable.
     */
    public void activateLabels () {
        for ( int i = 0; i < NUM_BUTTONS ; i++ ) {
            // Make the labels clickable.
            labels[i].addMouseListener( labelBehaviors[i] );
            // Make the cursor the pointy hand one, so they appear clickable.
            labels[i].setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
        }
    }

    /**
     * Add the labels to the Screen.
     */
    public void addLabels () {
        for ( int i = 0; i < NUM_BUTTONS; i++ ) {
            add( labels[i] );
        }
    }

    //---- Getters & Setters ----\\

    public JLabel[] getLabels () {
        return labels;
    }

    public int getLabelCount () {
        return NUM_BUTTONS;
    }

}
