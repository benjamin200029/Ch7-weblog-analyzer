import java.io.*;
import java.util.*;

/**
 * A class for creating log files of random data.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version    2016.02.29
 * 
 * edited by @author Benjamin Adelson
 * version 2019.10.20
 * 
 */
public class LogfileCreator
{
    private Random rand;

    /**
     * Create log files.
     */
    public LogfileCreator()
    {
        rand = new Random();
    }
    
    /**
     * Create a file of random log entries.
     * @param filename The file to write.
     * @param numEntries How many entries.
     * @return true if successful, false otherwise.
     */
    public boolean createFile(String filename, int numEntries)
    {
        boolean success = false;
        
        if(numEntries > 0) {
            try (FileWriter writer = new FileWriter(filename)) {
                LogEntry[] entries = new LogEntry[numEntries];
                for(int i = 0; i < numEntries; i++) {
                    entries[i] = createEntry();
                }
                Arrays.sort(entries);
                for(int i = 0; i < numEntries; i++) {
                    writer.write(entries[i].toString());
                    writer.write('\n');
                }
                
                success = true;
            }
            catch(IOException e) {
                System.err.println("There was a problem writing to " + filename);
            }
                
        }
        return success;
    }
    
    /**
     * Create a single (random) entry for a log file.
     * @return A log entry containing random data.
     */
    public LogEntry createEntry()
    {  
        // int [] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        // int day = 1 + rand.nextInt(daysInMonth[month-1]);
        // everything else was untounched
        
        // was 2016
        int year = 2010;
        int month = 1;
        // + rand.nextInt(12)
        // Avoid the complexities of days-per-month.
        int day = 1;
        //rand.nextInt(28)
        int hour = 0;
        //rand.nextInt(24)
        int minute = rand.nextInt(60);
        // added a for statement
        while(year<2016){
            minute = minute + rand.nextInt(60);
            if(minute>60){
                minute = minute - 60;
                hour = hour + 1;
                if(hour > 24){
                    hour = hour - 24;
                    day = day + 1;
                    if(day > 28){
                        day = day - 28;
                        month = month + 1;
                        if(month > 12){
                            month = month - 12;
                            year = year + 1;
                        }
                    }
                }
            } 
        }
        return new LogEntry(year, month, day, hour, minute);

}

}
