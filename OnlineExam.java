import java.util.Scanner;

class User {
    private String username;
    private String password;
    private String name;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public boolean checkCredentials(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void updateProfile(String newPassword, String newName) {
        this.password = newPassword;
        this.name = newName;
    }

    public String getName() {
        return name;
    }
}

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrectAnswer(int userAnswer) {
        return userAnswer == correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }
}

public class OnlineExam{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("user123", "pass123", "User");
        Question[] questions = {
                new Question("What is the capital of France?", new String[]{"London", "Berlin", "Paris", "Madrid"}, 2),
                new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Venus", "Jupiter"}, 1),
                new Question("What is 7 * 8?", new String[]{"15", "56", "64", "77"}, 2)
        };
        int totalTime = 5; // Total time for the exam in seconds

        System.out.println("Welcome to the Online Examination System");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (user.checkCredentials(username, password)) {
            System.out.println("Login successful, " + user.getName() + "!");

            System.out.println("Updating profile and password...");
            System.out.print("New Password: ");
            String newPassword = scanner.nextLine();
            System.out.print("New Name: ");
            String newName = scanner.nextLine();
            user.updateProfile(newPassword, newName);
            System.out.println("Profile updated successfully.");

            System.out.println("\nStarting the exam. You have " + totalTime + " seconds to complete each Question.");
            long startTime = System.currentTimeMillis();

            int score = 0;
            for (int i = 0; i < questions.length; i++) {
                Question currentQuestion = questions[i];
                System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getQuestionText());
                String[] options = currentQuestion.getOptions();
                for (int j = 0; j < options.length; j++) {
                    System.out.println((j + 1) + ". " + options[j]);
                }
                System.out.print("Select your answer (1-" + options.length + "): ");
                int userAnswer = scanner.nextInt();

                long endTime = System.currentTimeMillis();
                long elapsedTime = (endTime - startTime) / 1000; // Convert to seconds

                if (currentQuestion.isCorrectAnswer(userAnswer)) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect.");
                }

                if (elapsedTime >= totalTime) {
                    System.out.println("\nTime's up! Your answers have been auto-submitted.");
                    break;
                } else {
                    System.out.println("\nQuestion completed in " + elapsedTime + " seconds. Your score: " + score + " out of " + questions.length);
                }
            }

/*
            long endTime = System.currentTimeMillis();
            long elapsedTime = (endTime - startTime) / 1000; // Convert to seconds

            if (elapsedTime >= totalTime) {
                System.out.println("\nTime's up! Your answers have been auto-submitted.");
            } else {
                System.out.println("\nExam completed in " + elapsedTime + " seconds. Your score: " + score + " out of " + questions.length);
            }
*/

            System.out.println("Closing session and logging out.");
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }

        scanner.close();
    }
}
