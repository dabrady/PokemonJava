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
    private HomePanel home;

    //---- Constructors ----\\

    private PFrame ( String title ) {
        super( title );

        // Initialize screens.
        home = new HomePanel();
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
        // Reactivate home screen.
        home.activate();


        // General setup.
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setResizable( false );
        setContentPane( home );
        pack();
        setLocationRelativeTo( null );
        setVisible( true );
    }

    //---- Getters & Setters ----\\


}
