/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 12 July 2014 at 9:48 PM.
 *
 * This class models a (hopefully generic) home screen for a Pokemon Java-like application. It contains an array of
 * clickable JLabels that start a new game, load an old game, and configure the gameplay.
 */

public class HomeScreen extends PScreen {

    //---- Class & Instance variables ----\\

    private static String DEFAULT_BG = "gui/images/pj_homeScreen.jpg";
    public static int START = 0, LOAD = 1, CONFIG = 2, SECRET = 3;

    //---- Constructors ----\\

    public HomeScreen () {
        // Set up a PScreen with the default background and 4 buttons.
        this( DEFAULT_BG );
    }

    public HomeScreen ( String bgURL ) {
        super( bgURL, 4 );
    }

    //---- Utilities ----\\

    @Override
    public void resetImageURLs () {
        labelImageURLs[START]  = "gui/images/pj_startButton.jpg";
        labelImageURLs[LOAD]   = "gui/images/pj_loadButton.jpg";
        labelImageURLs[CONFIG] = "gui/images/pj_configButton.jpg";
        labelImageURLs[SECRET] = "gui/images/pj_secretButton.gif";
    }

    @Override
    public void positionLabels () {
        labels[START].setBounds (  58, 264, labelImages[START].getIconWidth(),  labelImages[START].getIconHeight()  );
        labels[LOAD].setBounds  ( 517,  26, labelImages[LOAD].getIconWidth(),   labelImages[LOAD].getIconHeight()   );
        labels[CONFIG].setBounds( 502, 111, labelImages[CONFIG].getIconWidth(), labelImages[CONFIG].getIconHeight() );
        labels[SECRET].setBounds( 550, 393, labelImages[SECRET].getIconWidth(), labelImages[SECRET].getIconHeight() );
    }

    //---- Getters & Setters ----\\


}
