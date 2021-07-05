package convert;

import java.awt.image.BufferedImage;

//this is a concrete class that extends the abstract class
//and it handles the conversion of the image
//from rgb to grayscale
public class ConvertToGrayscale extends StoreTime {
    //our image is stored as a BufferedImage
    private BufferedImage bufferedImage;

    //store the image given as a parameter
    ConvertToGrayscale(BufferedImage image) {
        this.bufferedImage = image;
    }

    //we will override the returnImage method from the
    //abstract class
    @Override
    public BufferedImage returnImage() {
        return bufferedImage;
    }

    //override the print class
    @Override
    public void printExecutionTime() {
        System.out.println("Converting the image took: " + (getEnding() - getStarting()) + " milliseconds.");
    }

    //in the next overridden method we will make
    //the conversion from rgb to grayscale
    @Override
    public void process() {
        setStarting(System.currentTimeMillis());

        //we will need the width and height of the image
        //and their product will give us the
        //number of pixels that will need to be converted
        //from rgb to grayscale
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                //get each pixel from image
                int p = bufferedImage.getRGB(x,y);

                //get the value for each color
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                //using the average method we will convert
                //each pixel to grayscale and
                //replace the one in the image
                int avg = (r + g + b)/3;
                p = (avg << 16) | (avg << 8) | avg;
                bufferedImage.setRGB(x, y, p);
            }
        }

        //get the current time, print the total time
        //and set the flag to true, meaning that
        //the processing was done with success
        setEnding(System.currentTimeMillis());
        printExecutionTime();
        setSuccess(true);
    }
}