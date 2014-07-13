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

    private String backgroundURL;
    private BufferedImage backgroundImg;

    //---- Constructors ----\\
    public ImagePanel () {
        this( "" );
    }

    public ImagePanel ( String url ) {
        // Create an image from the URL.
        if ( !url.equals( "" ) ) {
            try {
                backgroundURL = url;
                backgroundImg = ImageIO.read( new File( backgroundURL ) );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        } else {
            backgroundURL = null;
            backgroundImg = null;
        }
    }

    public ImagePanel ( String url, BufferedImage bg ) {
        backgroundURL = url;
        backgroundImg = bg;
    }

    //---- Utilities ----\\    

    public void paintComponent ( Graphics g ) {
        // Fill panel with default background color, in case the background image doesn't match the dimensions of this
        // panel.
        super.paintComponent( g );
        // Draw the background image. If null, nothing is drawn.
        g.drawImage( backgroundImg, 0, 0, null );
    }

    //---- Getters & Setters ----\\

    public String getBackgroundURL () {
        return backgroundURL;
    }

    public void setBackgroundURL ( String backgroundURL ) {
        this.backgroundURL = backgroundURL;
    }

    public BufferedImage getBackgroundImg () {
        return backgroundImg;
    }

    public void setBackgroundImg ( BufferedImage backgroundImg ) {
        this.backgroundImg = backgroundImg;
    }

}