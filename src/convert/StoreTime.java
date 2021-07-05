package convert;

import java.awt.image.BufferedImage;

public abstract class StoreTime implements DisplayElapsedTime {
    //all the classes that extends this abstract class will
    //have a flag that will tell us if the processing ran
    //with success or not. We will need this flag in our MainClass
    //to know if we should continue or not
    private boolean processSuccess;

    //for each processing we will want to know
    //when the processing started and when it ended
    //so we can calculate the elapsed time
    private long startingTime;
    private long endingTime;

    //get the starting time
    @Override
    public long getStarting() {
        return startingTime;
    }
    //set the starting time
    @Override
    public void setStarting(long starting) {
        this.startingTime = starting;
    }

    //get the ending time
    @Override
    public long getEnding() {
        return endingTime;
    }

    //set the ending time
    @Override
    public void setEnding(long ending) {
        this.endingTime = ending;
    }

    //get the success flag
    @Override
    public boolean getSuccess() {
        return processSuccess;
    }

    //set the success flag
    @Override
    public void setSuccess(boolean processSuccess) {
        this.processSuccess = processSuccess;
    }

    //we will need to read, convert and write the image
    //and the best approach is to create two abstract methods
    //one that handles the processing part
    //and another one that can fetch our processed image
    public abstract void process();
    public abstract BufferedImage returnImage();
}
