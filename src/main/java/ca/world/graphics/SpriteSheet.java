package ca.world.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpritSheet {
    public String path;
    public int width;
    public int height;

    public int[] pixels;

    public SpritSheet(String path) throws IOException {
        BufferedImage image  = null ;
        image = ImageIO.read(SpritSheet.class.getResourceAsStream(path));
        if(image == null){
            return ;
        }
        this.path = path;
        this.width = image.getWidth();
        this.height = image.getHeight();
        pixels = image.getRGB(0, 0, width, height, null, 0 , width);
        for(int i = 0 ; i < pixels.length ; i++){

        }

    }
}
