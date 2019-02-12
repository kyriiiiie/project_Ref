
public class Day implements Cloneable, Comparable<Day>{
	
	private int year;
	private int month;
	private int day;
	private int compareDay;
	private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
	
	// initialization
	public void set(String sDay) 
	{		
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[0]); 
		this.year = Integer.parseInt(sDayParts[2]); 
		this.month = MonthNames.indexOf(sDayParts[1])/3+1;
		compareDay = (year*10000+month*100+day);
	}
	
	// calculate day
	public boolean IsLeap(int ayear)
	{
	     return   (ayear % 4 ==0 || ayear % 400 ==0) && (ayear % 100 !=0);
	}
	
	public int getDayInYear() {			// calculate the day in that year
		int totalDays = 0;

        switch (month) {
            case 12:
                totalDays += 30;
            case 11:
                totalDays += 31;
            case 10:
                totalDays += 30;
            case 9:
                totalDays += 31;
            case 8:
                totalDays += 31;
            case 7:
                totalDays += 30;
            case 6:
                totalDays += 31;
            case 5:
                totalDays += 30;
            case 4:
                totalDays += 31;
            case 3:
                if (((year / 4 == 0) && (year / 100 != 0)) || (year / 400 == 0)) {
                    totalDays += 29;
                } else {
                    totalDays += 28;
                }
            case 2:
                totalDays += 31;
            case 1:
                totalDays += day;
        }
        return totalDays;
	}
	
	public int calDiff(Day endDay) {		// calculate the difference between two day
		if(year == endDay.year && month == endDay.month)
			return endDay.day - this.day + 1; 
		else if(year == endDay.year) {
			return endDay.getDayInYear() - this.getDayInYear() + 1;
			
		}
		else
		{
			int d1,d2,d3;
			
			if(IsLeap(endDay.year))
				d1 = 366 - endDay.getDayInYear();
			else
				d1 = 365 - endDay.getDayInYear();
			
			d2 = this.getDayInYear();
			
			d3 = 0;
			for(int i = endDay.year+1;i< year;i++) {
				if(IsLeap(i)) 
					d3 += 366;
				else 
					d3 += 365;
			}
			return d1+d2+d3+1;
		}		
	}
	
	// others
	public int getCompareDay() {
		return compareDay;
	}
	
	public Day(String sDay) {set(sDay);} 
	
	@Override
	public String toString() 
	{		
		return day+"-"+ MonthNames.substring((month-1)*3,(month)*3) + "-"+ year; // (month-1)*3,(month)*3
	}
	
	@Override
	public Day clone() 
	{
		Day copy= null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}
	
	@Override
	public int compareTo(Day another) {
		return (this.compareDay-another.compareDay);
	}
}