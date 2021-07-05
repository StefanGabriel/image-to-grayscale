package MainPackage;

public class MainClass implements Runnable, InterfaceClass {
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
    @Override
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
        ReadClass readImage;
        WriteClass writeImage;
        ProcessClass processImage;

        //verify if the path is null
        if(imagePath != null) {
            //start reading the image from the specified path
            readImage = new ReadClass(imagePath);
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
            processImage = new ProcessClass(readImage.returnImage());
            if(!processImage.getSuccess()) {
                System.out.println("Processing failed!");
                return;
            } else {
                //if the processing was done with success,
                //write the image in the same folder
                //the filename will have added "_grayscale"
                writeImage = new WriteClass(processImage.returnImage(), imagePath.replaceFirst("[.][^.]+$", ""));
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
