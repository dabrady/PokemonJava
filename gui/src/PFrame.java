import javax.swing.*;

/**
 * Created by Daniel Brady as part of the Pokemon Java project.
 * Last modified on 12 July 2014 at 9:14 PM.
 *
 * This class models the Pokemon Java application window.
 */

public class PFrame extends JFrame {

    //---- Class & Instance variables ----\\

    private static PFrame SINGLETON;
    public static final int HOME = 0, LOAD = 1, CONFIG = 2, SECRET = 3;

    private HomeScreen homeScreen;
    private LoadScreen loadScreen;
//    private ConfigScreen configScreen;
    private PScreen[] screens = new PScreen[4];

    //---- Constructors ----\\

    private PFrame ( String title ) {
        super( title );

        // Initialize screens.
        homeScreen   = new HomeScreen();
        loadScreen   = new LoadScreen();
//        configScreen = new ConfigScreen();
        screens[HOME]   = homeScreen;
        screens[LOAD]   = loadScreen;
        screens[CONFIG] = new PScreen();
        screens[SECRET] = new PScreen();
    }

    //---- Utilities ----\\    

    /**
     * Provides a way to access the one and only instance of PFrame that can exist.
     * @return The singleton PFrame.
     */
    public static PFrame getInstance () {
        if ( SINGLETON == null )
            SINGLETON = new PFrame( "Pokemon Java" );

        return SINGLETON;
    }

    /**
     * Completely destroys the application window.
     */
    public void destroy () {
        dispose();
        // Irrevocably lose pointer to old PFrame, so a new one can be created.
        SINGLETON = null;
    }

    /**
     * (Re)sets up event listeners and (re)configures the frame.
     */
    public void activate () {
        // (Re)activate screens.
        for ( PScreen screen : screens ) if ( screen != null ) screen.activate();

        // General setup.
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );
        setContentPane( homeScreen );
        pack();
        setLocationRelativeTo( null );
        setVisible( true );
    }

    /**
     * Reactivates all listeners in the PFrame.
     */
    public void reactivateListeners () {
        // Reactivate screen listeners.
        for ( PScreen screen : screens ) {
            screen.reactivateLabels();
        }
    }

    /**
     * Switches out the content pane for a different screen.
     * @param screen must be one of PFrame.HOME, PFrame.LOAD, PFrame.CONFIG, or PFrame.SECRET
     */
    public void switchScreenTo ( int screen ) {
        System.out.println( "Switching screens..." );
        // Switch to the proper screen.
        setContentPane( screens[screen] );
        // Refresh the components of the PFrame.
        validate();
    }

    //---- Getters & Setters ----\\


}
