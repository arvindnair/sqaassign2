package edu.iupui.cs.cs450;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Time
{
  /// Number of seconds in the time.
  private int second_ = 0;
  
  /// Number of nanoseconds in the time.
  private int nanosec_ = 0;
  
  /// Zero time.
  public static final Time ZERO = new Time (0, 0);
  
  /// Invalid time.
  public static final Time INVALID = new Time (-1, 0xffffffff);
  
  /// Infinite time representation.
  public static final Time INFINITE = new Time (0x7fffffff, 0xffffffff);
  
  /// Number of milliseconds in a second.
  public static final int MILLIS_PER_SECOND = 1000;
  
  /// Number of nanoseconds in a second.
  public static final int NANOSECS_PER_SECOND = 1000000000;
  
  /**
   * Get the current time.
   * 
   * @return
   */
  public static Time getCurrentTime ()
  {
    return new Time (System.currentTimeMillis ());
  }
  
  /**
   * Default constructor. Initializes the time to {0, 0}.
   */
  public Time ()
  {
    
  }
  
  /**
   * Initializing constructor.
   * 
   * @param millis
   */
  public Time (long millis)
  {
    this.second_ = (int) (millis / MILLIS_PER_SECOND);
    this.nanosec_ = (int) ((millis - (this.second_ * 1000)) * 1000 * 1000);    
  }
  
  /**
   * Initializing constructor.
   * 
   * @param second
   * @param nanosec
   */
  public Time (int second, int nanosec)
  {
    this.second_ = second;
    this.nanosec_ = nanosec;
  }
  
  /**
   * Get the number of seconds.
   * 
   * @return
   */
  public int getSeconds ()
  {
    return this.second_;
  }
  
  /**
   * Set the number of seconds.
   * 
   * @param sec
   * @return
   */
  public void setSeconds (int sec)
  {
    this.second_ = sec;
  }
  
  /**
   * Get the number of nanoseconds.
   * 
   * @return
   */
  public int getNanosecs ()
  {
    return this.nanosec_;
  }
  
  /**
   * Set the number of nanoseconds.
   * 
   * @param nanosec
   */
  public void setNanosecs (int nanosec)
  {
    this.nanosec_ = nanosec;
  }
  
  /**
   * Convert the time to the specified TimeUnit.
   * 
   * @param dstTimeUnit
   * @return
   */
  public long convert (TimeUnit dstTimeUnit)
  {
    return 
        dstTimeUnit.convert (this.second_, TimeUnit.SECONDS) +
        dstTimeUnit.convert (this.nanosec_, TimeUnit.NANOSECONDS);
  }
  
  /**
   * Test if this time is after the time parameter.
   * 
   * @param time
   * @return
   */
  public boolean isAfter (Time time)
  {
    return this.second_ > time.second_ || (this.second_ == time.second_ && this.nanosec_ > time.nanosec_);
  }
  
  /**
   * Test if this time is after or equal to the time parameter.
   * 
   * @param time
   * @return
   */
  public boolean isAfterOrEqual (Time time)
  {
    return this.second_ > time.second_ || (this.second_ == time.second_ && this.nanosec_ >= time.nanosec_);
  }
  
  /**
   * Test if this time is before the time parameter.
   * @param time
   * @return
   */
  public boolean isBefore (Time time)
  {
    return this.second_ < time.second_ || (this.second_ == time.second_ && this.nanosec_ < time.nanosec_);
  }
  
  /**
   * Test if this time is before or equal to the time parameter.
   * 
   * @param time
   * @return
   */
  public boolean isBeforeOrEqual (Time time)
  {
    return this.second_ < time.second_ || (this.second_ == time.second_ && this.nanosec_ <= time.nanosec_);
  }
  
  /**
   * Test if the time is equal to the time parameter.
   * 
   * @param time
   * @return
   */
  public boolean isEqual (Time time)
  {
    return this.second_ == time.second_ && this.nanosec_ == time.nanosec_;
  }
  
  /**
   * Convert the Time object to a Date object.
   * 
   * @return
   */
  public Date toDate ()
  {
    return new Date (this.convert (TimeUnit.MILLISECONDS));
  }
  
  /*
   * (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals (Object obj)
  {
    if (!(obj instanceof Time))
      return false;
    
    Time time = (Time)obj;
    
    return time == this ? true : this.isEqual (time);
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString ()
  {
    DateFormat df = DateFormat.getTimeInstance ();
    return df.format (this.toDate ());
  }
}
