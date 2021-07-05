package MainPackage;

import java.awt.image.BufferedImage;

abstract class AbstractClass implements InterfaceClass {
    //all the classes that extends this abstract class will
    //have a flag that will tell us if the processing ran
    //with success or not. We will need this flag in our MainClass
    //to know if we should continue or not
    private boolean processSuccess;

    //getter and setter for the flag
    public boolean getSuccess() {
        return processSuccess;
    }

    public void setSuccess(boolean processSuccess) {
        this.processSuccess = processSuccess;
    }

    //we will need to read, convert and write the image
    //and the best approach is to create two abstract classes
    //one that handles the processing part
    //and another one that can fetch our processed image
    public abstract void process();
    public abstract BufferedImage returnImage();
}
