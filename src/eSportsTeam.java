package esports;

import java.util.TreeSet;

public class eSportsTeam extends esports.Team {
    private static TreeSet<String> uniqueTeamNames = new TreeSet<>();
    private static TreeSet<String> uniqueGames = new TreeSet<>();
    private static TreeSet<String> uniqueConsoles = new TreeSet<>();

    public eSportsTeam(String teamName, String game, String console) {
        super(teamName, game, console);
        uniqueTeamNames.add(teamName.toLowerCase());
        uniqueGames.add(game.toLowerCase());
        uniqueConsoles.add(console.toLowerCase());
    }

    public static boolean isTeamNameUnique(String teamName) {
        return !uniqueTeamNames.contains(teamName.toLowerCase());
    }

    public static boolean isGameUnique(String game) {
        return !uniqueGames.contains(game.toLowerCase());
    }

    public static boolean isConsoleUnique(String console) {
        return !uniqueConsoles.contains(console.toLowerCase());
    }

    public static void removeTeamName(String teamName) {
        uniqueTeamNames.remove(teamName.toLowerCase());
    }
}