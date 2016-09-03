package edu.iupui.cs.cs450;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.Date;

public class TimeTest
{
  /**
   * Tests the getCurrentTime static method.
   *
   */
  @Test
  public void testGetCurrentTime ()
  {
    Time t1 = Time.getCurrentTime();
    long t_long = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

    Assert.assertEquals(t_long,t1.getSeconds());
  }

  /**
   * Tests the Default constructor which Initializes the time to {0, 0}.
   */
  @Test
  public void testDefaultConstructor ()
  {
    Time t = new Time ();

    Assert.assertEquals (0, t.getSeconds ());
    Assert.assertEquals (0, t.getNanosecs ());
  }

  /**
   * Tests the Initializing constructor which takes in parameter as milliseconds.
   *
   */
  @Test
  public void testInitializingConstructorMillis ()
  {
    Time t1 = new Time (10000);

    Assert.assertEquals (10, t1.getSeconds());
    Assert.assertEquals (0, t1.getNanosecs());
  }

  /**
   * Tests the Initializing constructor which takes in parameters as seconds
   * and nanoseconds.
   */
  @Test
  public void testInitializingConstructorSecondNanosec ()
  {
    Time t2 = new Time (100, 10);

    Assert.assertEquals (100, t2.getSeconds());
    Assert.assertEquals (10, t2.getNanosecs());
  }

  /**
   * Tests the setSeconds method which sets the number of seconds.
   *
   */
  @Test
  public void testSetSeconds ()
  {
    Time t = new Time (20,200);

    Assert.assertEquals (20, t.getSeconds());
    Assert.assertEquals (200, t.getNanosecs());

    t.setSeconds (30);

    Assert.assertEquals (30, t.getSeconds());
    Assert.assertEquals (200, t.getNanosecs());
  }

  /**
   * Tests the setNanosecs method which sets the number of nanoseconds.
   *
   */
  @Test
  public void testSetNanosecs ()
  {
    Time t = new Time (20,200);

    Assert.assertEquals (20, t.getSeconds());
    Assert.assertEquals (200, t.getNanosecs());

    t.setNanosecs(300);

    Assert.assertEquals (20, t.getSeconds());
    Assert.assertEquals (300, t.getNanosecs());
  }

  /**
   * Tests the convert method which converts the time to the specified TimeUnit.
   *
   *
   */
  @Test
  public void testConvert ()
  {
    TimeUnit tu = TimeUnit.MINUTES;
    Time t = new Time (120, 0);
    Assert.assertEquals (2, t.convert(tu));

    Time t1 = new Time (180000);
    Assert.assertEquals (3, t1.convert(tu));
  }

  /**
   * Tests the isAfter method which checks if this time is after the time parameter.
   *
   */
  @Test
  public void testIsAfter ()
  {
    Time t1 = new Time (100, 1000);
    Time t2 = new Time (200, 2000);

    Assert.assertEquals (true, t2.isAfter(t1));
    Assert.assertEquals (false, t1.isAfter(t2));

    t2.setSeconds (100);

    Assert.assertEquals (true, t2.isAfter(t1));
    Assert.assertEquals (false, t1.isAfter(t2));

    t2.setNanosecs (1000);

    Assert.assertEquals (false, t1.isAfter(t2));
    Assert.assertEquals (false, t1.isAfter(t1));
  }

  /**
   * Tests the isAfterOrEqual method which checks if this time is after or
   * equal to the time parameter.
   *
   */
  @Test
  public void testIsAfterOrEqual ()
  {
    Time t1 = new Time (100, 1000);
    Time t2 = new Time (200, 2000);

    Assert.assertEquals (true, t2.isAfterOrEqual(t1));
    Assert.assertEquals (false, t1.isAfterOrEqual(t2));

    t2.setSeconds (100);

    Assert.assertEquals (true, t2.isAfterOrEqual(t1));
    Assert.assertEquals (false, t1.isAfterOrEqual(t2));

    t2.setNanosecs (1000);

    Assert.assertEquals (true, t1.isAfterOrEqual(t2));
    Assert.assertEquals (true, t1.isAfterOrEqual(t1));
  }

  /**
   * Tests the isBefore method which checks if this time is before the time parameter.
   *
   */
  @Test
  public void testIsBefore ()
  {
    Time t1 = new Time (100, 1000);
    Time t2 = new Time (200, 2000);

    Assert.assertEquals (false, t2.isBefore(t1));
    Assert.assertEquals (true, t1.isBefore(t2));

    t2.setSeconds (100);

    Assert.assertEquals (false, t2.isBefore(t1));
    Assert.assertEquals (true, t1.isBefore(t2));

    t2.setNanosecs (1000);

    Assert.assertEquals (false, t1.isBefore(t2));
    Assert.assertEquals (false, t1.isBefore(t1));
  }

  /**
   * Tests the isBeforeOrEqual method which checks if this time is before or
   * equal to the time parameter.
   *
   */
  @Test
  public void testIsBeforeOrEqual ()
  {
    Time t1 = new Time (100, 1000);
    Time t2 = new Time (200, 2000);

    Assert.assertEquals (false, t2.isBeforeOrEqual(t1));
    Assert.assertEquals (true, t1.isBeforeOrEqual(t2));

    t2.setSeconds (100);

    Assert.assertEquals (false, t2.isBeforeOrEqual(t1));
    Assert.assertEquals (true, t1.isBeforeOrEqual(t2));

    t2.setNanosecs (1000);

    Assert.assertEquals (true, t1.isBeforeOrEqual(t2));
    Assert.assertEquals (true, t1.isBeforeOrEqual(t1));
  }

  /**
   * Tests the isEqual method which checks if the time is equal to the time parameter.
   *
   */
  @Test
  public void testIsEqual ()
  {
    Time t1 = new Time (100, 1000);
    Time t2 = new Time (200, 2000);

    Assert.assertEquals (false, t2.isEqual(t1));
    Assert.assertEquals (false, t1.isEqual(t2));

    t2.setSeconds (100);

    Assert.assertEquals (false, t2.isEqual(t1));
    Assert.assertEquals (false, t1.isEqual(t2));

    t2.setNanosecs (1000);

    Assert.assertEquals (true, t1.isEqual(t2));
    Assert.assertEquals (true, t1.isEqual(t1));
  }

  /**
   * Tests the toDate method which converts the Time object to a Date object.
   *
   */
  @Test
  public void testToDate ()
  {
    Time t = new Time (60000);
    Date d = new Date (60000);

    Assert.assertEquals (d, t.toDate());
  }

  /**
   * Tests the equals method which checks if the Object passed is same as
   * the Time Object.
   *
   */
  @Test
  public void testEquals ()
  {
    Date d = new Date (60000);
    Time t1 = new Time (100, 10);
    Time t2 = new Time (200, 20);

    Assert.assertEquals (false, t1.equals(d));
    Assert.assertEquals (true, t1.equals(t1));
    Assert.assertEquals (false, t1.equals(t2));

    t2.setSeconds (100);
    t2.setNanosecs (10);

    Assert.assertEquals (true, t1.equals(t2));
  }

  /**
   * Tests the toString method which given back the correct string
   * representation of the Time Object.
   *
   */
  @Test
  public void testToString ()
  {
    Time t1 = new Time (120000);
    Assert.assertEquals ("7:02:00 PM", t1.toString());

    Time t2 = new Time (600, 0);
    Assert.assertEquals ("7:10:00 PM", t2.toString());
  }
}
