package esports;

import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "teams.csv";

    public static void loadTeams(Map<String, eSportsTeam> teamsByTeamName,
                                 Map<String, List<eSportsTeam>> teamsByGame,
                                 Map<String, List<eSportsTeam>> teamsByConsole) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the header line
                }
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String teamName = parts[0].trim();
                    String game = parts[1].trim();
                    String console = parts[2].trim();
                    eSportsTeam team = new eSportsTeam(teamName, game, console);
                    teamsByTeamName.put(teamName.toLowerCase(), team);
                    teamsByGame.computeIfAbsent(game.toLowerCase(), k -> new ArrayList<>()).add(team);
                    teamsByConsole.computeIfAbsent(console.toLowerCase(), k -> new ArrayList<>()).add(team);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading teams: " + e.getMessage());
        }
    }

    public static void saveTeams(List<eSportsTeam> teams) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println("TeamName,Game,Console");
            for (eSportsTeam team : teams) {
                writer.println(team.getTeamName() + "," + team.getGame() + "," + team.getConsole());
            }
        } catch (IOException e) {
            System.out.println("Error saving teams: " + e.getMessage());
        }
    }
}