package convert;

//we will create an interface that will help us later
//when creating other classes (inheritance)
public interface DisplayElapsedTime {
    //for all classes that we will create,
    //we will need to display the time from
    //when the processing starts until it ends
    public void printExecutionTime();

    //we have created methods for setting and
    //getting the starting and ending times
    public void setStarting(long starting);
    public long getStarting();
    public void setEnding(long ending);
    public long getEnding();

    //setter and getter for the success state
    //after a processing method will run
    //we need to know if any problem occurred
    //and by setting the success flag to true
    //we know the processing was done without
    //any problems, otherwise the flag is set to false
    public void setSuccess(boolean processSuccess);
    public boolean getSuccess();
}
