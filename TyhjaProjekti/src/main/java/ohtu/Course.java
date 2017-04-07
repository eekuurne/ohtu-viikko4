package ohtu;

public class Course {
    
    private String name;
    private String term;
    private String week1, week2, week3, week4, week5, week6;

    public String getWeek(int number) {
        return getWeeks()[number - 1];
    }
    
    public String[] getWeeks() {
        String[] weeks = {week1, week2, week3, week4, week5, week6};
        return weeks;
    }
    
    @Override
    public String toString() {
        return "Kurssi: " + name + ", " + term;
    }
}
