import javax.swing.*;

/**
 * Created by Daniel Brady as part of the Pokemon Java project.
 * Last modified on 12 July 2014 at 9:14 PM.
 *
 * This class models the Pokemon Java application window.
 */

public class PFrame extends JFrame {

    //---- Class & Instance variables ----\\

    private static PFrame  SINGLETON;
    private static String  GIF_PATH    = "gui/images/gifs/";
    private static String  JPEG_PATH   = "gui/images/jpegs/";
    private static boolean exitOnClose = false;

    private static final int HOME = 0, LOAD = 1, CONFIG = 2, SECRET = 3;

    private static PScreen[] screens = new PScreen[4];

    //---- Constructors ----\\

    private PFrame ( String title ) {
        super( title );

        // Initialize screens.
        screens[HOME]   = new HomeScreen();
        screens[LOAD]   = new LoadScreen();
        screens[CONFIG] = new PScreen(); // new ConfigScreen();
        screens[SECRET] = new PScreen( JPEG_PATH + "cuteachu.jpg", 1 );
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
        setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
        addWindowListener( new PromptOnCloseListener( PromptOnCloseListener.DEFAULT_PROMPT,
                                                      PromptOnCloseListener.DEFAULT_TITLE,
                                                      true) );  // Prompts before exiting when user attempts to close window
        setResizable( false );
        setContentPane( screens[HOME] );
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
     * @param screenIndex must be one of PFrame.HOME, PFrame.LOAD, PFrame.CONFIG, or PFrame.SECRET
     */
    public void switchScreenTo ( int screenIndex ) {
        // Switch to the proper screen.
        setContentPane( screens[screenIndex] );
        // Refresh the components of the PFrame.
        validate();
    }

    /**
     * Switches out th content pane for a different screen.
     * @param screen the screen to display
     */
    public void switchScreenTo ( PScreen screen ) {
        // Switch to the proper screen.
        setContentPane( screen );
        // Refresh the components of the PFrame.
        validate();
    }

    //---- Getters & Setters ----\\

    public static String getGifPath () {
        return GIF_PATH;
    }

    public static void setGifPath ( String GIF_PATH ) {
        PFrame.GIF_PATH = GIF_PATH;
    }

    public static String getJpegPath () {
        return JPEG_PATH;
    }

    public static void setJPEG_PATH ( String JPEG_PATH ) {
        PFrame.JPEG_PATH = JPEG_PATH;
    }

    public static int getHome () {
        return HOME;
    }

    public static int getLoad () {
        return LOAD;
    }

    public static int getConfig () {
        return CONFIG;
    }

    public static int getSecret () {
        return SECRET;
    }

    public static boolean isExitOnClose() {
        return exitOnClose;
    }

    public static void setExitOnClose( boolean b ) {
        PFrame.exitOnClose = b;
    }
}
