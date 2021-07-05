package convert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//this class will handle the reading of the image from the
//path specified and store it as a BufferedImage
public class ReadImage extends StoreTime {
    //the path for the file
    private String path;
    //create a variables for storing th image
    private BufferedImage bufferedImage;

    //set the path
    ReadImage(String path) {
        this.path = path;
    }

    //return the read image from the specified path
    @Override
    public BufferedImage returnImage() {
        return bufferedImage;
    }

    //print the reading time
    @Override
    public void printExecutionTime() {
        System.out.println("Reading the image located at " + path + " took: " + (getEnding() - getStarting()) + " milliseconds.");
    }

    //we can create another method that takes as parameter the time for running
    //and displays it
    public void printExecutionTime(long time) {
        System.out.println("Reading the image located at " + path + " took: " + time + " milliseconds.");
    }

    //here we will read the image
    @Override
    public void process() {
        //store the starting time
        setStarting(System.currentTimeMillis());
        //create a File variable
        File file = null;

        //check if the path is valid
        if(path != null) {
            //get the file from path
            file = new File(path);
        } else {
            System.out.println("The path is invalid!");
            return;
        }

        //verify if the file is .bmp
        if(!(path.endsWith(".bmp") || path.endsWith(".BMP"))) {
            setSuccess(false);
            System.out.println("The extension of the file located at " + path + " is not BMP!");
            return;
        }

        //we need to catch is any IO exception
        //is thrown
        try {
            //read the image
            bufferedImage = ImageIO.read(file);
            //verify if the image has a 24 bit depth
            if(bufferedImage.getColorModel().getPixelSize() != 24) {
                System.out.println("The image located at " + path + " is not 24 bit!");
                setSuccess(false);
                return;
            }
        } catch (IOException e) {
            System.out.println(e);
            setSuccess(false);
            return;
        }
        //get the current time, print the total time
        //and set the success flag to true
        setEnding(System.currentTimeMillis());
        printExecutionTime();
        setSuccess(true);
    }
}