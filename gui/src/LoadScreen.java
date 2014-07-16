import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

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
    private JLabel chosenOne, saveInfo;

    //---- Constructors ----\\

    public LoadScreen () {
        // Set up general PScreen things.
        this( PFrame.getJpegPath() + "pj_loadScreen.jpg" );
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
        chooser.setFileFilter( new FileNameExtensionFilter( "PJ Save File (.ser)", saveFileExtension ) );
        // Set up the label next to the file chooser.
        chosenOne = new JLabel( "<None selected>" );
        saveInfo  = new JLabel();
        chosenOne.setFont( new Font( "Monospaced", Font.BOLD, 20 ) );
        saveInfo.setFont ( new Font( "Monospaced", Font.BOLD, 36 ) );
    }

    //---- Utilities ----\\

    @Override
    public void resetImageURLs () {
        // Standard images.
        labelImageURLs        = new String[NUM_BUTTONS];
        labelImageURLs[BACK]  = PFrame.getGifPath() + "pj_arrowButtonRed.gif";
        labelImageURLs[OPEN]  = PFrame.getGifPath() + "pj_ellipsisButtonRed.gif";
        labelImageURLs[START] = PFrame.getGifPath() + "pj_startButtonRed.gif";
    }

    @Override
    public void resetHoverImageURLs () {
        // On-hover images.
        labelHoverImageURLs        = new String[NUM_BUTTONS];
        labelHoverImageURLs[BACK]  = PFrame.getGifPath() + "pj_arrowButtonBlack.gif";
        labelHoverImageURLs[OPEN]  = PFrame.getGifPath() + "pj_ellipsisButtonBlack.gif";
        labelHoverImageURLs[START] = PFrame.getGifPath() + "pj_startButtonBlack.gif";
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
                PFrame.getInstance().switchScreenTo( PFrame.getHome() );
                setAllToStandard(); // remove any highlighting from screen labels
            }

            //---- Hover image capability ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                setHover( BACK );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                setHover( BACK, false );
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
                    System.out.println( save.getName() );
                    // Display the file name.
                    chosenOne.setText( save.getName() );
                    // Read the info from the save file to display the name of the player.
                    saveInfo.setText( generateRandomName() );
                    repositionSaveInfo();
                    // Load the save file into PJ.
                    // ...
                    repaint();
                }
            }
            //---- Hover image capability ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                setHover( OPEN );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                setHover( OPEN, false );
            }
        };
        // Set up the behavior for the start button.
        labelBehaviors[START] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Start the saved game.
                System.out.println( "Let the game begin!" );
            }

            //---- Hover image capability ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                labels[START].setIcon( labelHoverImages[START] );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                labels[START].setIcon( labelImages[START] );
            }
        };
    }

    @Override
    public void repositionLabels () {
        labels[BACK].setBounds (  20,  10,  labelImages[BACK].getIconWidth(),  labelImages[BACK].getIconHeight() );
        labels[OPEN].setBounds ( 433, 122,  labelImages[OPEN].getIconWidth(),  labelImages[OPEN].getIconHeight() );
        labels[START].setBounds( 274, 288, labelImages[START].getIconWidth(), labelImages[START].getIconHeight() );
        // Magic numbers here for width and height are measured based on the space available in the default background image.
        chosenOne.setBounds    ( 242, 128,                               180,                                 30 );
        repositionSaveInfo();
    }

    @Override
    public void addLabels () {
        super.addLabels();
        add( chosenOne );
        add( saveInfo );
    }

    /**
     * Magically determines the proper x-coordinate of the saveInfo label, based on the length of the text and the size
     * of the screen
     */
    private int getSaveInfoX () {
        int screenWidth = getBackgroundImg().getWidth();
        // Lots of magic numbers derived by trial and error. No idea how it works, but it works great :)
        int offset = 10;
        double magicNumber = 0.1 * saveInfo.getText().length();
        return (int) (screenWidth / (2 + magicNumber)) + offset;
    }

    private void repositionSaveInfo () {
        saveInfo.setBounds( getSaveInfoX(), 225, 180, 46 );
    }

    private String generateRandomName () {
        String[] names = { "J", "Yo", "Sam", "Andy", "Mikey", "Redhat", "Desmond" };
        return names[new Random().nextInt(names.length)];
    }

    //---- Getters & Setters ----\\


}
