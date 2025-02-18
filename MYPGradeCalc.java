import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MYPGradeCalc {
    static Scanner scanner = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear the screen
        System.out.flush();
    }

    public static void main(String[] args) {
        ArrayList<MYPSubject> subjects = new ArrayList<>();
        while (true) {
            System.out.println("\n--- IB MYP Grade Calculator ---");
            System.out.println("1. Add a New Subject");
            System.out.println("2. Enter Grades for a Subject");
            System.out.println("3. View Predicted Report Card");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = -1; // Initialize with an invalid value to enter the loop
            while (choice < 1 || choice > 4) { // Ensure the choice is valid
                try {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 4) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number between 1 and 4.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            clearScreen();

            switch (choice) {
                case 1 -> addNewSubject(subjects);
                case 2 -> enterGrades(subjects);
                case 3 -> viewReportCard(subjects);
                case 4 -> {
                    System.out.println("Exiting program. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewSubject(ArrayList<MYPSubject> subjects) {
        System.out.print("Enter subject name: ");
        scanner.nextLine(); // Consume newline
        String subjectName = scanner.nextLine();
        subjects.add(new MYPSubject(subjectName));
        System.out.println("Subject added successfully. Press enter to continue.");
        scanner.nextLine();
        clearScreen();
    }

    private static void enterGrades(ArrayList<MYPSubject> subjects) {
        if (subjects.isEmpty()) {
            System.out.println("No subjects found. Please add a subject first.");
            return;
        }

        MYPSubject subject = selectSubject(subjects);
        if (subject == null) return;

        System.out.println("Enter final grades for each criterion (1-8):");
        subject.criteriaGrades[0] = getValidGrade("Criteria A");
        subject.criteriaGrades[1] = getValidGrade("Criteria B");
        subject.criteriaGrades[2] = getValidGrade("Criteria C");
        subject.criteriaGrades[3] = getValidGrade("Criteria D");

        System.out.println("Grades updated successfully. Press enter to continue.");
        scanner.nextLine();
        scanner.nextLine();
        clearScreen();
    }

    private static int getValidGrade(String criterion) {
        int grade = -1;
        while (grade < 1 || grade > 8) {
            System.out.print(criterion + ": ");
            try {
                grade = scanner.nextInt();
                if (grade < 1 || grade > 8) {
                    System.out.println("Invalid grade. Please enter a grade between 1 and 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid grade between 1 and 8.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        return grade;
    }

    private static void viewReportCard(ArrayList<MYPSubject> subjects) {
        if (subjects.isEmpty()) {
            System.out.println("No subjects found. Please add a subject first.");
            return;
        }

        System.out.println("\n--- Predicted Report Card ---");
        for (MYPSubject subject : subjects) {
            int total = subject.calculateTotal();
            int finalGrade = subject.calculateFinalGrade();
            System.out.println("Subject: " + subject.name + " | " + "Total: " + total + " | " + "Final Grade: " + finalGrade);
            System.out.print("View grade descriptor? (yes/no): ");
            scanner.nextLine();
            String viewDescriptor = scanner.nextLine();
            if (viewDescriptor.equalsIgnoreCase("yes")) {
                System.out.println("Descriptor: " + subject.getDescriptor());
                scanner.nextLine();
            }
        }
    }

    private static MYPSubject selectSubject(ArrayList<MYPSubject> subjects) {
        System.out.println("Select a subject:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ". " + subjects.get(i).name);
        }
        System.out.print("Enter your choice: ");

        int subjectIndex = -1;
        while (subjectIndex < 0 || subjectIndex >= subjects.size()) {
            try {
                subjectIndex = scanner.nextInt() - 1;
                if (subjectIndex < 0 || subjectIndex >= subjects.size()) {
                    System.out.println("Invalid choice. Please select a valid subject.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        clearScreen();
        return subjects.get(subjectIndex);
    }
}