import javax.swing.*;
import java.io.IOException;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 17 July 2014 at 1:28 PM.
 * .
 * This class ....
 */

public class SpriteTest {

    public static void main ( String[] args ) throws IOException {
        JFrame frame = new JFrame( "Image Test" );
        JPanel content = new JPanel();
        content.setLayout( null );

        Sprite image = new Sprite( Sprite.DEFAULT_SPRITE_DIRECTORY
                                           + "pj_characterSprite.gif" );
        content.add( image );
        image = new Sprite( Tile.DEFAULT_TILE_DIRECTORY
                                + "tile_dirt.jpg" );
        image.setPosition( 65, 18 );
        content.add( image );
        frame.setContentPane( content );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize( 200, 200 );
        frame.setVisible( true );
    }
}
