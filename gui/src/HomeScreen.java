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
        labelImageURLs[START]  = "gui/images/pj_startButtonRedGreen.jpg";
        labelImageURLs[LOAD]   = "gui/images/pj_loadButton.jpg";
        labelImageURLs[CONFIG] = "gui/images/pj_configButton.jpg";
        labelImageURLs[SECRET] = "gui/images/pj_secretButton.gif";
    }

    @Override
    public void resetLabelBehaviors () {
        // Set up behavior for the start button.
        labelBehaviors[START]  = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Start a new game.
                System.out.println( "Let the game begin!" );
            }
        };
        // Set up behavior for the load button.
        labelBehaviors[LOAD]   = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Tell the PFrame to switch to the load screen.
                PFrame.getInstance().switchScreenTo( PFrame.LOAD );
            }
        };
        // Set up behavior for the config button.
        labelBehaviors[CONFIG] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Tell the PFrame to switch to the config screen.
                PFrame.getInstance().switchScreenTo( PFrame.CONFIG );
            }
        };
        // Set up behavior for the secret button.
        labelBehaviors[SECRET] = new MouseAdapter() {
            @Override
            public void mouseClicked ( MouseEvent e ) {
                // Tell the PFrame to switch to the secret screen.
                PFrame.getInstance().switchScreenTo( PFrame.SECRET );
            }
        };
    }

    @Override
    public void positionLabels () {
        labels[START].setBounds (  68, 275, labelImages[START].getIconWidth(),  labelImages[START].getIconHeight()  );
        labels[LOAD].setBounds  ( 517,  26, labelImages[LOAD].getIconWidth(),   labelImages[LOAD].getIconHeight()   );
        labels[CONFIG].setBounds( 502, 111, labelImages[CONFIG].getIconWidth(), labelImages[CONFIG].getIconHeight() );
        labels[SECRET].setBounds( 550, 393, labelImages[SECRET].getIconWidth(), labelImages[SECRET].getIconHeight() );
    }

    //---- Getters & Setters ----\\


}
