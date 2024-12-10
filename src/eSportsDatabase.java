package esports;

import java.util.*;

public class eSportsDatabase {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, eSportsTeam> teamsByTeamName = new HashMap<>();
    private static final Map<String, List<eSportsTeam>> teamsByGame = new HashMap<>();
    private static final Map<String, List<eSportsTeam>> teamsByConsole = new HashMap<>();

    public static void main(String[] args) {
        FileHandler.loadTeams(teamsByTeamName, teamsByGame, teamsByConsole);

        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> searchByTeamName();
                case 2 -> searchByGame();
                case 3 -> searchByConsole();
                case 4 -> addTeam();
                case 5 -> removeTeam();
                case 6 -> {
                    FileHandler.saveTeams(new ArrayList<>(teamsByTeamName.values()));
                    System.out.println("Teams saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- eSports Database ---");
        System.out.println("1. Search by Team Name");
        System.out.println("2. Search by Game");
        System.out.println("3. Search by Console");
        System.out.println("4. Add a Team");
        System.out.println("5. Remove a Team");
        System.out.println("6. Save and Exit");
    }

    private static void searchByTeamName() {
        String teamName = getStringInput("Enter Team Name: ").toLowerCase();
        eSportsTeam team = teamsByTeamName.get(teamName);
        if (team != null) {
            System.out.println("Found: " + team);
        } else {
            System.out.println("No team found with that name.");
        }
    }

    private static void searchByGame() {
        String game = getStringInput("Enter Game: ").toLowerCase();
        List<eSportsTeam> teams = teamsByGame.getOrDefault(game, List.of());
        if (!teams.isEmpty()) {
            teams.forEach(System.out::println);
        } else {
            System.out.println("No teams found for that game.");
        }
    }

    private static void searchByConsole() {
        String console = getStringInput("Enter Console: ").toLowerCase();
        List<eSportsTeam> teams = teamsByConsole.getOrDefault(console, List.of());
        if (!teams.isEmpty()) {
            teams.forEach(System.out::println);
        } else {
            System.out.println("No teams found for that console.");
        }
    }

    private static void addTeam() {
        String teamName = getStringInput("Enter Team Name: ");
        if (!eSportsTeam.isTeamNameUnique(teamName)) {
            System.out.println("Team name already exists.");
            return;
        }
        String game = getStringInput("Enter Game: ");
        String console = getStringInput("Enter Console: ");

        eSportsTeam newTeam = new eSportsTeam(teamName, game, console);
        teamsByTeamName.put(teamName.toLowerCase(), newTeam);
        teamsByGame.computeIfAbsent(game.toLowerCase(), k -> new ArrayList<>()).add(newTeam);
        teamsByConsole.computeIfAbsent(console.toLowerCase(), k -> new ArrayList<>()).add(newTeam);
        System.out.println("Team added successfully.");
    }

    private static void removeTeam() {
        String teamName = getStringInput("Enter Team Name to remove: ").toLowerCase();
        eSportsTeam team = teamsByTeamName.remove(teamName);
        if (team != null) {
            teamsByGame.get(team.getGame().toLowerCase()).remove(team);
            teamsByConsole.get(team.getConsole().toLowerCase()).remove(team);
            eSportsTeam.removeTeamName(teamName);
            System.out.println("Team removed successfully.");
        } else {
            System.out.println("No team found with that name.");
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}