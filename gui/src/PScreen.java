import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 13 July 2014 at 10:06 PM.
 *
 * This class models a screen in Pokemon Java. Screens have a background image and clickable JLabels with custom art,
 * serving as buttons. Subclasses should usually only ever override the following methods:
 *     #resetImageURLs
 *     #resetHoverImageURLs
 *     #resetLabelBehaviors
 *     #repositionLabels
 * A generic PScreen displays an 'under construction' screen, with a 'back' button that signals the assumed PFrame parent
 * to switch back to the home screen.
 */

public class PScreen extends ImagePanel {

    //---- Class & Instance variables ----\\

    protected static String DEFAULT_BG = PFrame.getJpegPath() + "pj_underConstruction.jpg"; // default background is the "under construction" screen

    protected final int   NUM_BUTTONS;
    protected JLabel[]    labels;
    protected ImageIcon[] labelImages;
    protected String[]    labelImageURLs;
    protected String[]    labelHoverImageURLs;
    protected ImageIcon[] labelHoverImages;

    protected MouseAdapter[] labelBehaviors;  // An array of  MouseAdapters to be added to the labels, making them clickable.

    //---- Constructors ----\\

    public PScreen () {
        this( DEFAULT_BG, 1 );
    }

    public PScreen ( String bgUrl, int numButtons ) {
        // Set up ImagePanel things.
        super( bgUrl );

        // Set up PScreen things.
        NUM_BUTTONS      = numButtons;
        labels           = new JLabel[NUM_BUTTONS];
        labelImages      = new ImageIcon[NUM_BUTTONS];
        labelHoverImages = new ImageIcon[NUM_BUTTONS];

        resetImageURLs(); // Set image URLs to defaults.
        resetHoverImageURLs(); // Set hover image URLs to defaults.
        resetLabelBehaviors(); // Set label behaviors to defaults.

        // General settings.
        setLayout( null );  // use absolute positioning
        // Set preferred size to the dimensions of the background image.
        setPreferredSize( new Dimension( getBackgroundImg().getWidth(), getBackgroundImg().getHeight() ) );
    }

    //---- Utilities ----\\    

    /**
     * Completely reactivates the screen: reloads label images, recreates the labels, repositions the labels, adds them
     * to the screen, and reactivates their behaviors.
     */
    public void activate () {
        reloadImages();
        recreateLabels();
        repositionLabels();
        addLabels();
        reactivateLabels();
    }

    /**
     * Resets the image URLs back to their defaults.
     */
    public void resetImageURLs () {
        labelImageURLs     = new String[NUM_BUTTONS];
        labelImageURLs[0]  = PFrame.getGifPath() + "pj_arrowButtonRed.gif";
    }

    /**
     * Resets the hover image URLs back to their defaults.
     */
    public void resetHoverImageURLs () {
        labelHoverImageURLs    = new String[NUM_BUTTONS];
        labelHoverImageURLs[0] = PFrame.getGifPath() + "pj_arrowButtonBlack.gif";
    }

    /**
     * Resets the label behaviors back to their defaults.
     */
    public void resetLabelBehaviors () {
        labelBehaviors    = new MouseAdapter[NUM_BUTTONS];
        // Set up behavior for the back button.
        labelBehaviors[0] = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                // Tell the PFrame to switch back to the home screen.
                PFrame.getInstance().switchScreenTo( PFrame.getHome() );
                setAllToStandard();
            }

            //---- Hover image capabilities ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                // Switch image to hover.
                setHover( 0 );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                // Switch image to standard.
                setHover( 0, false );
            }
        };
    }

    /**
     * Positions the labels on the screen.
     */
    public void repositionLabels () {
        labels[0].setBounds(19, 11, labelImages[0].getIconWidth(), labelImages[0].getIconHeight());
    }

    /**
     * Loads the images given by the label image URLs.
     */
    public void reloadImages () {
        for ( int i = 0; i < NUM_BUTTONS; i++ ) {
            // Load standard images.
            labelImages[i]      = new ImageIcon( labelImageURLs[i] );
            // Load hover images.
            labelHoverImages[i] = new ImageIcon( labelHoverImageURLs[i] );
        }
    }

    /**
     * Creates the labels from the label images.
     */
    public void recreateLabels () {
        for ( int i = 0; i < NUM_BUTTONS; i++ ) {
            labels[i] = new JLabel( labelImages[i] );
        }
    }

    /**
     * Add behaviors (MouseAdapters) to the labels, making them clickable.
     */
    public void reactivateLabels () {
        for ( int i = 0; i < NUM_BUTTONS ; i++ ) {
            JLabel label = labels[i];
            if ( label != null ) {
                // Add associated behavior to the label.
                MouseAdapter behavior = labelBehaviors[i];
                // We can't know which type of listener the behavior is, so just treat for everything.
                label.addMouseListener      ( behavior );
                label.addMouseMotionListener( behavior );
                label.addMouseWheelListener ( behavior );
                // Make the cursor the pointy hand one, so they appear clickable.
                label.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
            }
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

    /**
     * Changes the label image of the specified label to its hover icon.
     * @param labelIndex the position of the label in the array returned by #getLabels
     */
    public void setHover ( int labelIndex ) {
        setHover( labelIndex, true );
    }

    /**
     * Changes the label image of the specified label based on @param toHover: if true, changes to its hover icon,
     * otherwise changes to its standard icon.
     * @param labelIndex the position of the label in the array returned by #getLabels
     * @param toHover whether or not to set the hover icon
     */
    public void setHover ( int labelIndex, boolean toHover ) {
        ImageIcon icon = (toHover) ? labelHoverImages[labelIndex] : labelImages[labelIndex];
        labels[labelIndex].setIcon( icon );
    }

    /**
     * Sets all label images to their standard labels, removing any highlighting.
     */
    public void setAllToStandard () {
        for ( int i = 0; i < NUM_BUTTONS; i++ ) {
            setHover( i, false );
        }
    }

    public JLabel[] getLabels () {
        return labels;
    }

    public int getLabelCount () {
        return NUM_BUTTONS;
    }

}
