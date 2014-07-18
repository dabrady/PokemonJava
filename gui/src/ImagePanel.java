import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 12 July 2014 at 9:33 PM.
 *
 * This class represents a JPanel that is intended to hold only a single background image.
 */

public class ImagePanel extends JPanel {

    //---- Class & Instance variables ----\\

    protected String        imageURL;
    protected BufferedImage image;

    //---- Constructors ----\\
    public ImagePanel () {
        this( null, null );
    }

    public ImagePanel ( String url ) {
       this( url, null );
    }

    public ImagePanel ( String url, BufferedImage image ) {
        // Store the image URL.
        this.imageURL = url;

        // Read in the image from the URL if necessary.
        if ( image == null ) {
            reload();
        } else {
            this.image = image;
        }
    }

    //---- Utilities ----\\

    /**
     * Handles the drawing of this ImagePanel. Child classes should override this method, and not #paintComponent.
     * @param g the Graphics object obtained from #paintComponent
     */
    protected void draw( Graphics g ) {
        g.drawImage( image, 0, 0, null );
    }

    @Override
    public void paintComponent ( Graphics g ) {
        // Fill panel with default background color, in case the background image doesn't match the dimensions of this
        // panel.
        super.paintComponent( g );
        // Draw the background image. If null, nothing is drawn.
        draw( g );
    }

    /**
     * Reload an image into the sprite by reading the given image URL.
     * @param imageURL the path to the image to load
     * @return true if the load was successful, false otherwise
     */
    public boolean reload ( String imageURL ) {
        setImageURL( imageURL );
        return reload();
    }

    /**
     * Reload an image into the sprite by rereading the saved image URL.
     * @return true if the load was successful, false otherwise
     */
    public boolean reload () {
        if ( !imageURL.equals( "" ) ) {
            try {
                image = ImageIO.read( new File( imageURL ) );
                setSize( new Dimension( image.getWidth(), image.getHeight() ) );
                return true;
            } catch ( IOException e ) {
                return false;
            }
        }
        return false;
    }

    //---- Getters & Setters ----\\

    /**
     * Get the URL associated with this ImagePanel.
     * @return a valid URL
     */
    public String getImageURL () {
        return imageURL;
    }

    /**
     * Set the URL to be associated with this ImagePanel's image. Note that it does not enforce a correlation between the
     * two: it is up to the user to either provide the image associated with this URL or else reload the image using this
     * URL if they do not want a disconnect to exist between the two attributes.
     * @param imageURL a valid URL
     */
    public void setImageURL ( String imageURL ) {
        this.imageURL = imageURL;
    }

    /**
     * Get the BufferedImage associated with this ImagePanel.
     * @return the image stored within this ImagePanel
     */
    public BufferedImage getImage () {
        return image;
    }

    /**
     * Set the image to be associated with this ImagePanel's image URL. Note that it does not enforce a correlation between
     * the two: it is up the user to reset the image URL of this ImagePanel if they do not want a disconnect to exist
     * between the two attributes. Recommend using #reload and providing a valid URL instead of directly calling #setImage.
     * @param image an image to be associated with this ImagePanel
     */
    public void setImage ( BufferedImage image ) {
        this.image = image;
    }
}
