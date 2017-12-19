/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Alberto Pacheco
 * verzsion 2017.12.19
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    // Where to calculate the day access counts.
    private int[] dayCounts;
    // Where to calculate the month access counts
    private int[] monthCounts;
    // Where to calculate the year access counts
    private int[] yearCounts;
    // Use a LogfileReader to access the data.

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[30];
        monthCounts = new int[13];
        yearCounts = new int[3];
        // Create the reader to obtain the data. note you have to but txt in quotes
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay();
            int month = entry.getMonth();
            hourCounts[hour]++;
            dayCounts[day]++;
            monthCounts[month]++;;
        }
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
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * method to find the number of accesses
     * @return total
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int i =0; i < hourCounts.length; i++)
        {
            total += hourCounts[i];
        }
        return total;
    }
    
    /**
     * method to find the busiest hour
     * @return busiesthour
     */
    public int busiestHour()
    {
        int highest = 0;
        int busiestHour = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            // the +1 is the not allow zeroes
            if (highest > hourCounts[hour])
            {
                highest = hourCounts[hour];
                busiestHour= hour;
            }
        }
    
        return busiestHour;
        
    }
    
    /**
     * 
     * method to find the quietest hour
     * @return lowest
     */
    public int quietestHour()
    {
        int lowest = hourCounts[0];
        int quietestHour = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            // the +1 is the not allow zeroes
            if (lowest > hourCounts[hour +1])
            {
                lowest = hourCounts[hour +1];
                quietestHour= hour;
            }
        }
    
        return lowest;
        
    }
    
    /**
     * method to find the buesiest of two hours (midnight -2, 1-3, etc)
     * @return busiestHour
     */
    public int buisestTwoHour()
    {
        int busy = 0;
        int busiestHour = 0;
        
        for(int hour = 0; hour < hourCounts.length ; hour++) {
            //avoid zeroes with hour +1 
            if (busy < (hourCounts[hour] + hourCounts[hour+1]))
            {
                busy = hourCounts[hour] + hourCounts[hour+1];
                busiestHour= hour;
            }
        }
        return busiestHour;
    
    }
    
    /**
     * method to find busiestday
     * @return busiestday
     */
    
    public int buisestday()
    {
        int busiest = 0;
        int busiestday = 1;
        
        for(int day = 1; day < dayCounts.length; day++) {
            if (busiest < dayCounts[day])
            {
                busiest = dayCounts[day];
                busiestday= day;
            }
        }
        return busiestday;
    }
    
    /**
     * method ti find quitetest day
     * @return quietest day
     * 
     */
    
    public int quietestday()
    {
        int quietest = dayCounts[0];
        int quietestday = 0;
        
        for(int day = 1; day < dayCounts.length; day++) {
            if (quietest > dayCounts[day])
            {
                quietest = dayCounts[day];
                quietestday= day;
            }
        }
        return quietestday;
    }
    
    /**
     * method to find totalAcccesses per month
     * @return total
     * 
     */
    public int totalAccessesPerMonth(int selectedMonth){
        int month = selectedMonth;
        int total = 0;
        total = monthCounts[month];
        return total;
        }
    
    /**
     *  method to find busiest month
     *  @return busiiest month
     */
    public int buisestmonth()
    {
        int busiest = 0;
        int busiestmonth = 0;
        
        for(int month = 0; month < monthCounts.length; month++) {
            if (busiest < monthCounts[month])
            {
                busiest = monthCounts[month];
                busiestmonth= month;
            }
        }
        return busiestmonth;
    }
    
    /**
     * method to find quietest month 
     * @return quietestmonth
     */
    public int quietestmonth()
    {
        int quietest = monthCounts[0];
        int quietestmonth = 0;
        
        for(int month = 0; month < monthCounts.length; month++) {
            if (quietest > monthCounts[month])
            {
                quietest = monthCounts[month];
                quietestmonth= month;
            }
        }
        return quietestmonth;
    }
    
    /**
     * method to find averageAccess for month
     * @return average
     */
    public int averageAccessesPerMonth(){
        int total =0;
        int average=0;
         
        for(int month=0; month<monthCounts.length; month++)
            total +=monthCounts[month];
        
        average = total/12;
        return average;
    }
    
    
    
}
