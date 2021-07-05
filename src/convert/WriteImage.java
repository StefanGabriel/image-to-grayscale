package convert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//after the image is converted to grayscale
//we need to write it to the disk
public class WriteImage extends StoreTime {
    //store the final version of the image
    private BufferedImage bufferedImage;

    //save in a variable the path to where
    //the image will be saved, alongside the old
    //filename without the extension
    private String path;

    //store the image and the path given locally
    WriteImage(BufferedImage image, String path) {
        this.bufferedImage = image;
        this.path = path;
    }

    //return the read image from the specified path
    @Override
    public BufferedImage returnImage() {
        return bufferedImage;
    }

    //print the writing time
    @Override
    public void printExecutionTime() {
        System.out.println("Writing the image located at " + path + " took: " + (getEnding() - getStarting()) + " milliseconds.");
    }

    //this method will write the image to the disk
    @Override
    public void process() {
        //get the starting time
        setStarting(System.currentTimeMillis());

        try {
            //create a new file
            File file = new File(path + "_grayscale.bmp");
            //write the image to the disk
            ImageIO.write(bufferedImage, "bmp", file);
        } catch (IOException e) {
            System.out.println(e);
            setSuccess(false);
            return;
        }
        setEnding(System.currentTimeMillis());
        printExecutionTime();
        setSuccess(true);
    }
}
