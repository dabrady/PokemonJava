import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 12 July 2014 at 9:48 PM.
 *
 * This class models a (hopefully generic) home screen for a Pokemon Java-like application. It contains an array of
 * clickable JLabels that start a new game, load an old game, and configure the gameplay.
 */

public class HomeScreen extends PScreen {

    //---- Class & Instance variables ----\\

    public static int START = 0, LOAD = 1, CONFIG = 2, SECRET = 3;

    //---- Constructors ----\\

    public HomeScreen () {
        // Set up a PScreen with the default background and 4 buttons.
        this( "gui/images/pj_homeScreen.jpg" );
    }

    public HomeScreen ( String bgURL ) {
        super( bgURL, 4 );
    }

    //---- Utilities ----\\

    @Override
    public void resetImageURLs () {
        // Standard images.
        labelImageURLs         = new String[NUM_BUTTONS]; // reset the URLs array
        labelImageURLs[START]  = "gui/images/pj_startButtonRedGreen.jpg";
        labelImageURLs[LOAD]   = "gui/images/pj_loadButton.jpg";
        labelImageURLs[CONFIG] = "gui/images/pj_configButton.jpg";
        labelImageURLs[SECRET] = "gui/images/pj_secretButton.gif";
    }

    @Override
    public void resetHoverImageURLs () {
        // On-hover images.
        labelHoverImageURLs          = new String[4]; // reset the URLs array
        labelHoverImageURLs[START]   = "gui/images/pj_startButtonBlackGreen.jpg";
        labelHoverImageURLs[LOAD]    = "gui/images/pj_loadButtonBlack.jpg";
        labelHoverImageURLs[CONFIG]  = "gui/images/pj_configButtonBlack.jpg";
        labelHoverImageURLs[SECRET]  = "gui/images/pj_secretButton.gif"; // don't change on hover
    }

    @Override
    public void resetLabelBehaviors () {
        labelBehaviors        = new MouseAdapter[NUM_BUTTONS]; // reset the behavior array
        // Set up behavior for the start button.
        labelBehaviors[START] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Start a new game.
                System.out.println( "Let the game begin!" );
                // ...
                setAllToStandard(); // remove any highlighting from screen labels
            }

            //---- Hover image capability ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                // Switch out the button image.
                setHover( START );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                // Switch out the button image.
                setHover( START, false );
            }
        };
        // Set up behavior for the load button.
        labelBehaviors[LOAD] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Tell the PFrame to switch to the load screen.
                PFrame.getInstance().switchScreenTo( PFrame.LOAD );
                setAllToStandard(); // remove any highlighting from screen labels
            }

            //---- Hover image capability ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                // Switch out the button image.
                setHover( LOAD );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                // Switch out the button image.
                setHover( LOAD, false );
            }
        };
        // Set up behavior for the config button.
        labelBehaviors[CONFIG] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Tell the PFrame to switch to the config screen.
                PFrame.getInstance().switchScreenTo( PFrame.CONFIG );
                setAllToStandard(); // remove any highlighting from screen labels
            }

            //---- Hover image capability ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                // Switch out the button image.
                setHover( CONFIG );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                // Switch out the button image.
                setHover( CONFIG, false );
            }
        };
        // Set up behavior for the secret button.
        labelBehaviors[SECRET] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Tell the PFrame to switch to the secret screen.
                PFrame.getInstance().switchScreenTo( PFrame.SECRET );
                setAllToStandard(); // remove any highlighting from screen labels
            }

            //---- Hover image capability ----\\
            @Override
            public void mouseEntered ( MouseEvent e ) {
                // Switch out the button image.
                setHover( SECRET );
            }

            @Override
            public void mouseExited ( MouseEvent e ) {
                // Switch out the button image.
                setHover( SECRET, false );
            }
        };
    }

    @Override
    public void repositionLabels () {
        labels[START].setBounds (  68, 275, labelImages[START].getIconWidth(),  labelImages[START].getIconHeight()  );
        labels[LOAD].setBounds  ( 517,  26, labelImages[LOAD].getIconWidth(),   labelImages[LOAD].getIconHeight()   );
        labels[CONFIG].setBounds( 502, 111, labelImages[CONFIG].getIconWidth(), labelImages[CONFIG].getIconHeight() );
        labels[SECRET].setBounds( 550, 393, labelImages[SECRET].getIconWidth(), labelImages[SECRET].getIconHeight() );
    }

    //---- Getters & Setters ----\\


}
