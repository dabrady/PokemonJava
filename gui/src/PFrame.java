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
    private HomeScreen homeScreen;
    private LoadScreen loadScreen;
//    private ConfigScreen configScreen;
    private PScreen[] screens = new PScreen[2];

    //---- Constructors ----\\

    private PFrame ( String title ) {
        super( title );

        // Initialize screens.
        homeScreen   = new HomeScreen();
        loadScreen   = new LoadScreen();
//        configScreen = new ConfigScreen();
        screens[0]   = homeScreen;
        screens[1]   = loadScreen;
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
        for ( PScreen screen : screens ) screen.activate();

        // General setup.
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );
        setContentPane( loadScreen );
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
            screen.activateLabels();
        }
    }

    //---- Getters & Setters ----\\


}
