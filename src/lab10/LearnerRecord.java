package lab10;

/**
 * Exercises 10.4 and 10.5 - Learner data carrier with a grade band derived from the mark.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public record LearnerRecord(String id, String name, int mark) {

    /** Band A: 75+, B: 60-74, C: below 60. */
    public String band() {
        if (mark >= 75) return "A";
        if (mark >= 60) return "B";
        return "C";
    }

    /** Parses one CSV line "id,name,mark". */
    public static LearnerRecord fromCsv(String line) {
        String[] p = line.split(",");
        if (p.length != 3) throw new IllegalArgumentException("Bad CSV line: " + line);
        return new LearnerRecord(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim()));
    }
}
