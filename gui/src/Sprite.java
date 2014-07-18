import java.awt.image.BufferedImage;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 17 July 2014 at 1:07 PM.
 *
 * This class models a game sprite. Sprites are ImagePanels with a position.
 */

public class Sprite extends ImagePanel {

    //---- Class & Instance variables ----\\

    public static final String DEFAULT_SPRITE_DIRECTORY = "gui/images/gifs/sprites/";
//    public static final int SPRITE_WIDTH = 64, SPRITE_HEIGHT = 64;

    protected Point position;


    /**
     * A class to represent a Point on the screen.
     */
    protected static final class Point {
        private int x;

        private int y;

        public Point ( int x, int y ) {
            this.x = x;
            this.y = y;
        }

        /**
         * Calculates the distance between this point and some other point.
         * @param that the Point to calculate the distance to
         * @return the distance between the two Points
         */
        public double distanceTo ( Point that ) {
            int dx = this.x - that.x;
            int dy = this.y - that.y;
            return Math.sqrt( dx * dx + dy * dy );
        }

        @Override
        public String toString() {
            return "#<Point: x=" + x + ", y=" + y + ">";
        }

        public int getX () {
            return x;
        }
        public int getY () {
            return y;
        }

    }

    //---- Constructors ----\\

    public Sprite () {
        this( null, null );
    }

    public Sprite ( String imageURL ) {
        this( imageURL, null);
    }

    public Sprite ( String imageURL, BufferedImage image) {
        super( imageURL, image );
        // The default position of any sprite is the origin.
        setPosition( new Point( 0, 0 ) );
    }

    //---- Utilities ----\\

    @Override
    public String toString () {
        return "#<" + getClass().getName() + ": imageURL=\"" + imageURL + "\", position=" + position + ">";
    }

    //---- Getters & Setters ----\\

    /**
     * Get the current position of the sprite.
     * @return a Point containing the x-y coordinates of the sprite on-screen
     */
    public Point getPosition () {
        return position;
    }

    /**
     * Set the current position of the sprite.
     * @param x the desired x-coordinate of the sprite on-screen
     * @param y the desired y-coordinate of the sprite on-screen
     */
    public void setPosition ( int x, int y ) {
        setPosition( new Point( x, y ) );
    }

    /**
     * Set the current position of the sprite.
     * @param position a Point containing the desired x-y coordinates of the sprite on-screen
     */
    public void setPosition ( Point position ) {
        this.position = position;
        setBounds( this.position.getX(), this.position.getY(), getWidth(), getHeight() );
    }

}
