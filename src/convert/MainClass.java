package convert;

import java.io.File;

public class MainClass implements Runnable {
    ///we need to print the duration for the processing
    //done in the process method, so we need the starting and
    //the ending time
    private long startingTime;
    private long endingTime;

    //a variable that will store the path for the image
    private String imagePath;

    //store the path
    MainClass(String path) {
        this.imagePath = path;
    }

    //print the execution time
    public void printExecutionTime() {
        System.out.println("Total processing time for image located at " + imagePath + " is: " + (endingTime - startingTime) + " milliseconds.");
    }

    //override the run method from the
    //Runnable class
    @Override
    public void run() {
        //get the current time at starting
        startingTime = System.currentTimeMillis();
        //create objects from the read, convert and write classes
        ReadImage readImage;
        WriteImage writeImage;
        ConvertToGrayscale convertImage;

        //verify if the path is null
        if(imagePath != null) {
            //start reading the image from the specified path
            readImage = new ReadImage(imagePath);
            readImage.process();
        } else {
            readImage = null;
        }

        //if the reading process terminated successfully
        //we will go for the conversion
        if(!readImage.getSuccess()) {
            System.out.println("Image couldn't be read!");
            return;
        } else {
            //convert the image to grayscale
            convertImage = new ConvertToGrayscale(readImage.returnImage());
            convertImage.process();
            if(!convertImage.getSuccess()) {
                System.out.println("Processing failed!");
                return;
            } else {
                //if the processing is done successfully, we will
                //create a folder in the current path, named "Grayscale"
                //where we will store our converted image
                File file = new File(imagePath);
                //get the path without the filename
                String currentPath = file.getParent();
                String cleanPath;
                //if we are in the current folder, getParent()
                //will return null, so we need to check this and make
                //the necessary changes
                if(currentPath == null) {
                    currentPath = "";
                    cleanPath = currentPath;
                }
                else {
                    cleanPath = currentPath + "/";
                    currentPath = currentPath + "\\";
                }

                //we will create a new folder at the specified path
                //if the folder already exists, we will skip this step
                File dir = new File(currentPath + "Grayscale");
                if(!dir.exists()) {
                    if (!dir.mkdir()) {
                        System.out.println("Sorry couldn’t create specified directory");
                    }
                }
                //create the new path for the image alongside with its filename
                String writingPath = cleanPath + "Grayscale/" + file.getName();
                writeImage = new WriteImage(convertImage.returnImage(), writingPath.replaceFirst("[.][^.] + $", ""));
                writeImage.process();

                //if the writing was successfully, go to the next step
                if(!writeImage.getSuccess()){
                    System.out.println("Writing failed!");
                    return;
                }
            }
        }
        //get the current time and print the execution time
        endingTime = System.currentTimeMillis();
        printExecutionTime();
    }
}
