/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 * 
 * edited by author Ben Adelson
 * version 2019.10.17
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Where to calculate the day of the week.
    private int[] dayCounts;
    // Where to calculate the month of the year.
   // private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[32];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /*
     * Return the number of acceses recorded in the log file
     */
    public int numberOfAccesses()
    {
        int total = 0;
        // Add the value in each element of hourCounts to total
        for(int a = 1; a <= hourCounts.length;a++){
            total = total + hourCounts.length;
        }
        return total;
    }
    public int numberOfAccesses2()
    {
        int total2 = 0;
        // Add the value in each element of hourCounts to total
        for(int a = 1; a <= dayCounts.length;a++){
            total2 = total2 + dayCounts.length;
        }
        return total2;
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
     * Analyze the day access data from the log file.
     */
    public void analyzeDayData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /*
     * returns the number of the busiest hours
     * test returns 18 as the busiest Hour
     */
    public int busiestHour()
    {
       int busiestHour = 0;
       for(int hour = 1; hour < hourCounts.length; hour++){
           if(hourCounts[hour]> hourCounts[busiestHour]){
               busiestHour = hour;
            }
        }
        return busiestHour;
    }
    
    /*
     * returns the number of the least busy hour
     * test returns 9 as the quietest Hour
     */
    public int quietestHour()
    {
       int quietestHour = 0;
       for(int hour = 1; hour < hourCounts.length; hour++){
           if(hourCounts[hour]< hourCounts[quietestHour]){
               quietestHour = hour;
            }
        }
        return quietestHour;
    }
    
    /*
     * returns the number of the least busy hour
     * test returns 17 as the busiestTwoHourPeriod
     */
    public int busiestTwoHourPeriod()
    {
       int busiestTwoHourPeriod = 0;
       int busiestTwoHourPeriodCount = 0;

       for(int hour = 1; hour < hourCounts.length-1; hour++){
           int periodCount = hourCounts[hour]+hourCounts[hour+1];
           if(periodCount > busiestTwoHourPeriodCount){
               busiestTwoHourPeriod = hour;
               busiestTwoHourPeriodCount = periodCount;
            }
        }
        return busiestTwoHourPeriod;
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printDayCounts()
    {
        System.out.println("Day: Count");
        for(int day = 0; day < dayCounts.length; day++) {
            System.out.println(day + ": " + dayCounts[day]);
        }
    }
    
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
