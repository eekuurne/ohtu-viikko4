package ohtu;

public class Submission {
    
    private String student_number;
    private String week;
    private int hours;
    private boolean a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, 
            a12, a13, a14, a15, a16, a17, a18, a19, a20, a21;

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public int getHours() {
        return hours;
    }
    
    public int getAmountDone() {
        int amount = 0;
        for (int i = 0; i < exerciseList().length; i++) {
            if (exerciseList()[i]) {
                amount++;
            }
        }
        return amount;
    }
    
    private boolean[] exerciseList() {
        boolean[] exercises = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, 
            a12, a13, a14, a15, a16, a17, a18, a19, a20, a21};
        return exercises;
    }
    
    private String exercisesToString() {
        String exercises = "";
        for (int i = 0; i < exerciseList().length; i++) {
            if (exerciseList()[i]) {
                exercises += (i + 1) + " ";
            }
        }
        return exercises;
    }
    
    public String toStringWithMaxExercises(Course course) {
        return "viikko " + week + ": tehtyjä tehtäviä yhteensä: " + 
                getAmountDone() + " (maksimi " + course.getWeek(Integer.parseInt(week)) + "), aikaa kului " + hours + 
                " tuntia, tehdyt tehtävät: " + exercisesToString();
    }

    @Override
    public String toString() {
        return "viikko " + week + ": tehtyjä tehtäviä yhteensä: " + 
                getAmountDone() + ", aikaa kului " + hours + 
                " tuntia, tehdyt tehtävät: " + exercisesToString();
    }    
}
