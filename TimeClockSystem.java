import java.util.*;

import org.jcp.xml.dsig.internal.dom.Utils;

public class TimeClockSystem {

    static class User {
        String id;
        String name;
        String role;
        boolean isClockedIn;
        boolean isOnBreak;
        List<String> timeRecords;
        double pay;

        public User(String id, String name, String role) {
            this.id = id;
            this.name = name;
            this.role = role;
            this.timeRecords = new ArrayList<>();
            this.pay = 0.0;
        }
    }

    public static class Utils {
            public static void sleep(long milliseconds) {
                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    System.out.println("Sleep interrupted!");
                }
            }
        }

    static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        // Example data
        users.put("E001", new User("E001", "John Doe", "Employee"));
        users.put("M001", new User("M001", "Jane Smith", "Manager"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Time Clock System!");
        Utils.sleep(2000); // Wait for 2 seconds
        while (true) {
            System.out.print("\nEnter your ID: ");
            String id = scanner.nextLine().trim();
            Utils.sleep(1000); // Wait for 1 second

            if (users.containsKey(id)) {
                User user = users.get(id);
                System.out.println("\nWelcome, " + user.name + "! You are logged in as a " + user.role + ".");
                if (user.role.equals("Employee")) {
                    employeeMenu(user, scanner);
                } else if (user.role.equals("Manager")) {
                    managerMenu(user, scanner);
                }
            } else {
                System.out.println("Invalid ID. Please try again.");
            }
        }
        
    }

    static void employeeMenu(User user, Scanner scanner) {
        Utils.sleep(1000); // Wait for 1 seconds
        while (true) {
            Utils.sleep(1000); // Wait for 1 second 
            System.out.println("\nEmployee Menu:");
            Utils.sleep(250); // Wait for .25 second
            System.out.println("1. Clock In");
            Utils.sleep(250); // Wait for .25 second
            System.out.println("2. Clock Out");
            Utils.sleep(250); // Wait for .25 second
            System.out.println("3. Start Break");
            Utils.sleep(250); // Wait for .25 second
            System.out.println("4. End Break");
            Utils.sleep(250); // Wait for .25 second
            System.out.println("5. View Time/Pay");
            Utils.sleep(250); // Wait for .25 second
            System.out.println("6. Log Out");
            Utils.sleep(250); // Wait for .25 second
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    clockIn(user);
                    break;
                case "2":
                    clockOut(user);
                    break;
                case "3":
                    startBreak(user);
                    break;
                case "4":
                    endBreak(user);
                    break;
                case "5":
                    viewTimeAndPay(user);
                    break;
                case "6":
                Utils.sleep(1000); // Wait for 1 second
                    System.out.println("Logging out...");
                    return;
                default:
                Utils.sleep(1000); // Wait for 1 second
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void managerMenu(User user, Scanner scanner) {
        while (true) {
            Utils.sleep(1000); // Wait for 1 second
            System.out.println("\nManager Menu:");
            Utils.sleep(250); // Wait for .25 second
            System.out.println("1. View Employee Records");
            Utils.sleep(250); // Wait for .25 second
            System.out.println("2. Log Out");
            Utils.sleep(250); // Wait for .25 second
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewEmployeeRecords();
                    break;
                case "2":
                Utils.sleep(1000); // Wait for 1 second
                    System.out.println("Logging out...");
                    return;
                default:
                Utils.sleep(1000); // Wait for 1 second
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void clockIn(User user) {
        if (user.isClockedIn) {
            Utils.sleep(1000); // Wait for 1 second
            System.out.println("Error: You are already clocked in.");
        } else {
            user.isClockedIn = true;
            String timestamp = new Date().toString();
            Utils.sleep(1000); // Wait for 1 second
            user.timeRecords.add("Clock In at " + timestamp);
            System.out.println("Clocked in at " + timestamp);
            Utils.sleep(1000); // Wait for 1 second 
        }
    }

    static void clockOut(User user) {
        if (!user.isClockedIn) {
            Utils.sleep(1000); // Wait for 1 second 
            System.out.println("Error: You must be clocked in to clock out.");
        } else if (user.isOnBreak) {
            Utils.sleep(1000); // Wait for 1 second 
            System.out.println("Error: You cannot clock out while on a break.");
        } else {
            user.isClockedIn = false;
            String timestamp = new Date().toString();
            Utils.sleep(1000); // Wait for 1 second 
            user.timeRecords.add("Clock Out at " + timestamp);
            System.out.println("Clocked out at " + timestamp);
            Utils.sleep(1000); // Wait for 1 second 
            calculatePay(user);
            Utils.sleep(1000); // Wait for 1 second 
        }
    }
    static void startBreak(User user) {
        if (!user.isClockedIn) {
            Utils.sleep(1000); // Wait for 1 second 
            System.out.println("Error: You must be clocked in to start a break.");
        } else if (user.isOnBreak) {
            Utils.sleep(1000); // Wait for 1 second 
            System.out.println("Error: You are already on a break.");
        } else {
            user.isOnBreak = true;
            Utils.sleep(1000); // Wait for 1 second 
            String timestamp = new Date().toString();
            user.timeRecords.add("Start Break at " + timestamp);
            Utils.sleep(1000); // Wait for 1 second 
            System.out.println("Started break at " + timestamp);
        }
    }

    static void endBreak(User user) {
        if (!user.isOnBreak) {
            Utils.sleep(1000); // Wait for 1 second 
            System.out.println("Error: You are not currently on a break.");
        } else {
            user.isOnBreak = false;
            Utils.sleep(1000); // Wait for 1 second 
            String timestamp = new Date().toString();
            Utils.sleep(1000); // Wait for 1 second 
            user.timeRecords.add("End Break at " + timestamp);
            System.out.println("Ended break at " + timestamp);
            Utils.sleep(1000); // Wait for 1 second 
        }
    }

    static void viewTimeAndPay(User user) {
        Utils.sleep(1000); // Wait for 1 second
        System.out.println("\nTime Records:");
        for (String record : user.timeRecords) {
            System.out.println("- " + record);
        }
        Utils.sleep(1000); // Wait for 1 second
        System.out.println("\nTotal Pay: $" + user.pay);
        Utils.sleep(1000); // Wait for 1 second
    }

    static void viewEmployeeRecords() {
        Utils.sleep(1000); // Wait for 1 second
        System.out.println("\nEmployee Records:");
        for (User user : users.values()) {
            if (user.role.equals("Employee")) {
                Utils.sleep(1000); // Wait for 1 second
                System.out.println("\nEmployee: " + user.name);
                for (String record : user.timeRecords) {
                    Utils.sleep(1000); // Wait for 1 second
                    System.out.println("  - " + record);
                }
                Utils.sleep(1000); // Wait for 1 second
                System.out.println("  Total Pay: $" + user.pay);
            }
        }
        Utils.sleep(1000); // Wait for 1 second
    }

    static void calculatePay(User user) {
        // For simplicity, assume $20/hour
        double hourlyRate = 20.0;
        user.pay += hourlyRate;  // Example static pay calculation
        Utils.sleep(1000); // Wait for 1 second
        System.out.println("Pay updated. Total Pay: $" + user.pay);
    }
}