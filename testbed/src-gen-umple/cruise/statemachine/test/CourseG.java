/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.statemachine.test;

public class CourseG
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseG State Machines
  enum Status { On, Off }
  enum StatusOn { Null, Idle, Running }
  private Status status;
  private StatusOn statusOn;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CourseG()
  {
    setStatus(Status.On);
    if (statusOn == null) { setStatusOn(StatusOn.Null); }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    String answer = status.toString();
    if (statusOn != StatusOn.Null) { answer += "." + statusOn.toString(); }
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public StatusOn getStatusOn()
  {
    return statusOn;
  }

  public boolean turnOff()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case On:
        exitStatus();
        setStatus(Status.Off);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean flip()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    StatusOn aStatusOn = statusOn;
    switch (aStatus)
    {
      case Off:
        setStatusOn(StatusOn.Idle);
        wasEventProcessed = true;
        break;
    }

    switch (aStatusOn)
    {
      case Idle:
        setStatusOn(StatusOn.Running);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean enterOn()
  {
    boolean wasEventProcessed = false;
    
    StatusOn aStatusOn = statusOn;
    switch (aStatusOn)
    {
      case Null:
        setStatusOn(StatusOn.Idle);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean exitOn()
  {
    boolean wasEventProcessed = false;
    
    StatusOn aStatusOn = statusOn;
    switch (aStatusOn)
    {
      case Idle:
        setStatusOn(StatusOn.Null);
        wasEventProcessed = true;
        break;
      case Running:
        setStatusOn(StatusOn.Null);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private void exitStatus()
  {
    switch(status)
    {
      case On:
        exitOn();
        break;
    }
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case On:
        if (statusOn == StatusOn.Null) { setStatusOn(StatusOn.Idle); }
        break;
    }
  }

  private void setStatusOn(StatusOn aStatusOn)
  {
    statusOn = aStatusOn;
    if (status != Status.On && aStatusOn != StatusOn.Null) { setStatus(Status.On); }
  }

  public void delete()
  {}

}