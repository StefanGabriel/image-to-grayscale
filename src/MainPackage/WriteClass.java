package MainPackage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//after the image is converted to grayscale
//we need to write it to the disk
public class WriteClass extends AbstractClass{
    //store the final version of the image
    private BufferedImage bufferedImage;

    //like for the other classes, we need to
    //print the duration for the processing
    //done in the process method
    private long startingTime;
    private long endingTime;

    //save in a variable the path to where
    //the image will be saved, alongside the old
    //filename without the extension
    private String path;

    //store the image and the path given locally
    WriteClass(BufferedImage image, String path) {
        this.bufferedImage = image;
        this.path = path;
        //go to writing the image
        process();
    }

    //return the read image from the specified path
    @Override
    public BufferedImage returnImage() {
        return bufferedImage;
    }

    //print the writing time
    @Override
    public void printExecutionTime() {
        System.out.println("Writing the image took: " + (endingTime - startingTime) + " milliseconds.");
    }

    //this method will write the image to the disk
    @Override
    public void process() {
        //get the starting time
        startingTime = System.currentTimeMillis();

        try {
            //create a new file
            File file = new File(path + "_grayscale.bmp");
            //write the image to the disk
            ImageIO.write(bufferedImage, "bmp", file);
        } catch (IOException e) {
            System.out.println(e);
        }
        endingTime = System.currentTimeMillis();
        printExecutionTime();
        setSuccess(true);
    }
}
