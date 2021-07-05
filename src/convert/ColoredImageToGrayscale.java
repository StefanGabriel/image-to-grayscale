package convert;

import java.io.File;

public class ColoredImageToGrayscale {
    //we will create some private variables to help up store
    //some information about our file/directory
    //in the "path"variable will be stored the path to a file or folder
    static private String path;

    //our path given can be a file or a folder
    //so we need some indicators of what the path
    //will be
    private boolean isFolder = false;
    private boolean isFile = false;

    //we can work with threads if the user
    //specify this in args
    private boolean multithreading = false;

    public static void main(String[] args) {
        //we will want to know how much
        //time was passed from starting the main
        //and until it finished
        long startingTime;
        long endingTime;

        //get the time in milliseconds for when the main
        //started to run
        startingTime = System.currentTimeMillis();

        //create a new object for our ColoredImageToGrayscale class
        ColoredImageToGrayscale coloredImageToGrayscale = new ColoredImageToGrayscale();

        //the method that will decode the arguments will tell us if the
        //decoding is finished with success or not
        //if the method returns false, we will exit our application
        if(!coloredImageToGrayscale.arguments(args)){
            return;
        }

        //now that the arguments are valid, we will start the
        //process for reading the files. As previously specified,
        //we will verify the isFile and isFolder flags
        if(coloredImageToGrayscale.isFile) {
            //if we work with a file, we will create an object
            //and call the run() method where we will process the image
            new MainClass(path).run();
        } else if(coloredImageToGrayscale.isFolder) {
            //if we are working with a folder, we will need all the
            //files filename to parse to our MainClass class that
            //will process the file
            File dir = new File(path);
            String[] files = dir.list();

            //we will verify if in that folder is any file
            if (files.length == 0) {
                System.out.println("The directory is empty!");
                return;
            } else {
                //we will go thru that array of Strings
                //and get each name for the files
                for (String aFile : files) {
                    //if the user specified that he wants to process
                    //all the files in multithreading mode
                    //we will create a new thread for each file.
                    //Modern cpus can handle multiple threads
                    //on my local machine I could reach 110 k threads
                    //(I ran a script that created threads in a loop)
                    if(coloredImageToGrayscale.multithreading) {
                        System.out.println("Processing will run in multithreading!");
                        Thread object = new Thread(new MainClass(path + "/" + aFile));
                        object.start();
                    } else {
                        //otherwise, we will process the files sequentially
                        System.out.println("Processing will run in singlethreading mode!");
                        new MainClass(path + "/" + aFile).run();
                    }
                }
            }
        } else {
            return;
        }
        //will check again the system time and display how much took
        //for the main method to run
        endingTime = System.currentTimeMillis();
        System.out.println("Main ran for " + (endingTime - startingTime) + " milliseconds.");
    }

    //method that will decode our args given
    final public boolean arguments(String[] args) {
        //if the number of arguments is 0,
        //we will print a message and exit the method with false
        //that will stop the application
        if(args.length == 0) {
            System.out.println("The argument list is empty!");
            return false;
        }

        //like any other application, we have help, to tell the user
        //what is the correct syntax that he needs to use
        //for starting the application
        if(args[0].equals("-help")){
            System.out.println("The argument list should look like this:");
            System.out.println();
            System.out.println("pathToFileOrFolder [-m]");
            System.out.println();
            System.out.println("You have to provide the path to a file or folder");
            System.out.println("and if you provide a folder, you can specify if");
            System.out.println("you want to process the files multithreaded -m.");
            return false;
        }

        //if we have one argument, we will check
        //if that argument is a path to a file
        //or a folder, otherwise we will exist with an error
        if(args.length == 1) {
            File buff = new File(args[0]);
            if(buff.exists()){
                if(buff.isDirectory()) {
                    isFolder = true;
                }
                if(buff.isFile()) {
                    isFile = true;
                }
                path = args[0];
                return true;
            }
            else {
                System.out.println("The path is invalid!");
                return false;
            }
        }

        //if we have two arguments, we will check if
        //the first one is a path to a file or a folder
        //the second argument is accepted only if it is "-m"
        //meaning that the user selected a multithreaded processing
        if(args.length == 2) {
            File buff = new File(args[0]);
            if(buff.exists()){
                if(buff.isDirectory()) {
                    isFolder = true;
                }
                if(buff.isFile()) {
                    isFile = true;
                }
                path = args[0];
                if(args[1].equals("-m") && isFolder){
                    multithreading = true;
                }
                else {
                    System.out.println("The argument list is invalid! Run: java MainPackage.ColoredImageToGrayscale -help, to see the syntax.");
                    return false;
                }
                return true;
            }
            else {
                System.out.println("The argument list is invalid! Run: java MainPackage.ColoredImageToGrayscale -help, to see the syntax.");
                return false;
            }
        }

        //if we have more than three arguments, we will print an error
        if(args.length >= 3) {
            System.out.println("The argument list is invalid! Run: java MainPackage.ColoredImageToGrayscale -help, to see the syntax.");
            return false;
        }

        return true;
    }
}
