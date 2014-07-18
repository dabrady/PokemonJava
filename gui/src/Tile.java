import java.awt.image.BufferedImage;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 17 July 2014 at 2:44 PM.
 *
 * This class represents a tile in a tile-based game. Tiles are glorified sprites, intended to be used as part of the
 * background to a level or map.
 */

public class Tile extends Sprite {

    //---- Class & Instance variables ----\\

    public static final String DEFAULT_TILE_DIRECTORY = "gui/images/jpegs/tiles/";

    //---- Constructors ----\\

    public Tile () {
        super();
    }

    public Tile ( String imageUrl ) {
        super( imageUrl );
    }

    public Tile ( String imageUrl, BufferedImage image ) {
        super( imageUrl, image );
    }

    //---- Utilities ----\\    



    //---- Getters & Setters ----\\


}
