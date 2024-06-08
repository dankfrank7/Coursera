public class Date implements Comparable<Date> 
{
    private final int month, day, year;

    public Date(int m, int d, int y) 
    {
        month = m;
        day = d;
        year = y;
    }

    public int compareTo(Date that)
    {
        if (this.year < that.year ) return -1;
        if (this.year > that.year ) return +1;
        if (this.month < that.month) return -1;
        if (this.month > that.month) return +1;
        if (this.day < that.day ) return -1;
        if (this.day > that.day ) return +1;
        return 0;
    } 

    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }

    public static void main(String[] args) {
        Date date1 = new Date(6, 15, 1992);
        Date date2 = new Date(12, 31, 1990);
        Date date3 = new Date(6, 15, 1992);

        System.out.println("Comparing " + date1 + " and " + date2 + ": " + date1.compareTo(date2));
        System.out.println("Comparing " + date2 + " and " + date1 + ": " + date2.compareTo(date1));
        System.out.println("Comparing " + date1 + " and " + date3 + ": " + date1.compareTo(date3));

        Date date4 = new Date(1, 1, 1992);
        Date date5 = new Date(1, 2, 1992);
        Date date6 = new Date(2, 1, 1992);

        System.out.println("Comparing " + date4 + " and " + date5 + ": " + date4.compareTo(date5));
        System.out.println("Comparing " + date5 + " and " + date6 + ": " + date5.compareTo(date6));
        System.out.println("Comparing " + date6 + " and " + date4 + ": " + date6.compareTo(date4));
    }
}
