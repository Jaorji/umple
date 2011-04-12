/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.11.1.3376 modeling language!*/

package cruise.statemachine.test;
import java.util.*;

public class CourseE
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CourseE Attributes
  private List<String> logs;

  //CourseE State Machines
  enum Status { Off, Sleep, On }
  enum StatusOn { Null, Play, Pause }
  private Status status;
  private StatusOn statusOn;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CourseE()
  {
    logs = new ArrayList<String>();
    setStatus(Status.Off);
    if (statusOn == null) { setStatusOn(StatusOn.Null); }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean addLog(String aLog)
  {
    boolean wasAdded = false;
    wasAdded = logs.add(aLog);
    return wasAdded;
  }

  public boolean removeLog(String aLog)
  {
    boolean wasRemoved = false;
    wasRemoved = logs.remove(aLog);
    return wasRemoved;
  }

  public String getLog(int index)
  {
    String aLog = logs.get(index);
    return aLog;
  }

  public String[] getLogs()
  {
    String[] newLogs = logs.toArray(new String[logs.size()]);
    return newLogs;
  }

  public int numberOfLogs()
  {
    int number = logs.size();
    return number;
  }

  public boolean hasLogs()
  {
    boolean has = logs.size() > 0;
    return has;
  }

  public int indexOfLog(String aLog)
  {
    int index = logs.indexOf(aLog);
    return index;
  }

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

  public boolean turnOn()
  {
    boolean wasEventProcessed = false;

    switch (status)
    {
      case Off:
        exitStatus();
        setStatus(Status.On);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean turnSleep()
  {
    boolean wasEventProcessed = false;

    switch (status)
    {
      case Off:
        exitStatus();
        setStatus(Status.Sleep);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean wake()
  {
    boolean wasEventProcessed = false;

    switch (status)
    {
      case Sleep:
        exitStatus();
        setStatusOn(StatusOn.Pause);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean turnOff()
  {
    boolean wasEventProcessed = false;

    switch (status)
    {
      case On:
        exitStatus();
        setStatus(Status.Off);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean enterOn()
  {
    boolean wasEventProcessed = false;

    switch (statusOn)
    {
      case Null:
        setStatusOn(StatusOn.Play);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean exitOn()
  {
    boolean wasEventProcessed = false;

    switch (statusOn)
    {
      case Play:
        exitStatusOn();
        setStatusOn(StatusOn.Null);
        wasEventProcessed = true;
        break;
      case Pause:
        exitStatusOn();
        setStatusOn(StatusOn.Null);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean push()
  {
    boolean wasEventProcessed = false;

    switch (statusOn)
    {
      case Play:
        exitStatusOn();
        setStatusOn(StatusOn.Pause);
        wasEventProcessed = true;
        break;
      case Pause:
        exitStatusOn();
        setStatusOn(StatusOn.Play);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  public boolean standby()
  {
    boolean wasEventProcessed = false;

    switch (statusOn)
    {
      case Pause:
        exitStatusOn();
        setStatus(Status.Sleep);
        wasEventProcessed = true;
        break;
    }

    return wasEventProcessed;
  }

  private void exitStatus()
  {
    switch(status)
    {
      case Off:
        addLog("Exit Off");
        break;
      case Sleep:
        addLog("Exit Sleep");
        break;
      case On:
        exitOn();
        addLog("Exit On");
        break;
    }
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case Off:
        addLog("Enter Off");
        break;
      case Sleep:
        addLog("Enter Sleep");
        break;
      case On:
        addLog("Enter On");
        if (statusOn == StatusOn.Null) { setStatusOn(StatusOn.Play); }
        break;
    }
  }

  private void exitStatusOn()
  {
    switch(statusOn)
    {
      case Play:
        addLog("Exit Play");
        break;
      case Pause:
        addLog("Exit Pause");
        break;
    }
  }

  private void setStatusOn(StatusOn aStatusOn)
  {
    statusOn = aStatusOn;
    if (status != Status.On && aStatusOn != StatusOn.Null) { setStatus(Status.On); }

    // entry actions and do activities
    switch(statusOn)
    {
      case Play:
        addLog("Enter Play");
        break;
      case Pause:
        addLog("Enter Pause");
        break;
    }
  }

  public void delete()
  {}

}