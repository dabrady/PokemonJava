import javax.swing.*;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 12 July 2014 at 10:17 PM.
 * .
 * This class ....
 */

public class PFrameTest {

    public static void main ( String[] args ) {
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run () {
                PFrame.getInstance().activate();
            }
        } );
    }
}
