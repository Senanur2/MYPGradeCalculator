public class MYPSubject {
    String name;
    int[] criteriaGrades = new int[4]; // A, B, C, D grades

    public MYPSubject(String name) {
        this.name = name;
    }

    public int calculateTotal() {
        int total = 0;
        for (int grade : criteriaGrades) {
            total += grade;
        }
        return total;
    }

    public int calculateFinalGrade() {
        int total = calculateTotal();
        if (total >= 28) return 7;
        if (total >= 24) return 6;
        if (total >= 19) return 5;
        if (total >= 15) return 4;
        if (total >= 10) return 3;
        if (total >= 6) return 2;
        return 1;
    }

    public String getDescriptor() {
        int finalGrade = calculateFinalGrade();
        return switch (finalGrade) {
            case 7 -> "Produces high-quality, frequently innovative work...";
            case 6 -> "Produces high-quality, occasionally innovative work...";
            case 5 -> "Produces generally high-quality work...";
            case 4 -> "Produces good-quality work...";
            case 3 -> "Produces work of an acceptable quality...";
            case 2 -> "Produces work of limited quality...";
            default -> "Produces work of very limited quality...";
        };
    }
}