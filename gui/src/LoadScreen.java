import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 13 July 2014 at 9:03 PM.
 *
 * This class models a game-loading screen in Pokemon Java. It provides the user with the ability to load and play saved
 * games.
 */

public class LoadScreen extends PScreen {

    //---- Class & Instance variables ----\\

    private static final String saveFileExtension = "ser"; // The file extension for a serialized Java object.
    private static int BACK = 0, OPEN = 1, START = 2;

    private JFileChooser chooser = new JFileChooser(); // A file chooser, for selecting game save files.

    //---- Constructors ----\\

    public LoadScreen () {
        // Set up general PScreen things.
        this( "gui/images/pj_loadScreen.jpg" );
    }

    public LoadScreen ( String bgURL ) {
        super( bgURL, 3 );
        // General settings.
        setLayout( null );  // use absolute positioning
        // Set preferred size to the dimensions of the background image.
        setPreferredSize( new Dimension( getBackgroundImg().getWidth(), getBackgroundImg().getHeight() ) );
        // Set image URLs to defaults.
        resetImageURLs();

        // Set up the file chooser.
        //chooser.setFileFilter( new FileNameExtensionFilter( "PJ Save File", saveFileExtension ) );
    }

    //---- Utilities ----\\

    @Override
    public void resetImageURLs () {
        // Standard images.
        labelImageURLs        = new String[NUM_BUTTONS];
        labelImageURLs[BACK]  = "gui/images/pj_arrowButton.jpg";
        labelImageURLs[OPEN]  = "gui/images/pj_ellipsisButtonRedWhite.jpg";
        labelImageURLs[START] = "gui/images/pj_startButtonRedTan.jpg";
    }

    @Override
    public void resetHoverImageURLs () {
        // On-hover images.
        labelHoverImageURLs        = new String[NUM_BUTTONS];
        labelHoverImageURLs[BACK]  = "";
        labelHoverImageURLs[OPEN]  = "gui/images/pj_ellipsisButtonBW.jpg";
        labelHoverImageURLs[START] = "";
    }

    @Override
    public void resetLabelBehaviors () {
        // Reset the behavior array.
        labelBehaviors       = new MouseAdapter[NUM_BUTTONS];
        // Set up behavior for the back button.
        labelBehaviors[BACK] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Tell the PFrame to switch back to the home screen.
                PFrame.getInstance().switchScreenTo( PFrame.HOME );
            }
        };
        // Set up the behavior for the file-open button.
        labelBehaviors[OPEN] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Open the file chooser dialog.
                int returnVal = chooser.showOpenDialog( LoadScreen.this );

                if ( returnVal == JFileChooser.APPROVE_OPTION ) {
                    // Get the selected file.
                    File save = chooser.getSelectedFile();
                    // Load it into the GUI.
                    // ...
                    System.out.println( save.getName() );
                }
            }
            //---- Hover image capability ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                labels[OPEN].setIcon( labelHoverImages[OPEN] );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                labels[OPEN].setIcon( labelImages[OPEN] );
            }
        };
        // Set up the behavior for the start button.
        labelBehaviors[START] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Start the saved game.
                System.out.println( "Let the game begin!" );
            }
        };
    }

    @Override
    public void repositionLabels () {
        labels[BACK].setBounds (  20,  10, labelImages[BACK].getIconWidth(),  labelImages[BACK].getIconHeight() );
        labels[OPEN].setBounds ( 433, 122, labelImages[OPEN].getIconWidth(),  labelImages[OPEN].getIconHeight()  );
        labels[START].setBounds( 274, 288, labelImages[START].getIconWidth(), labelImages[START].getIconHeight() );
    }

    //---- Getters & Setters ----\\


}
