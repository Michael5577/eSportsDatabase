package esports;

public class Team {
    protected String teamName;
    protected String game;
    protected String console;

    public Team(String teamName, String game, String console) {
        this.teamName = teamName;
        this.game = game;
        this.console = console;
    }

    // Getters and setters
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getGame() { return game; }
    public void setGame(String game) { this.game = game; }
    public String getConsole() { return console; }
    public void setConsole(String console) { this.console = console; }

    @Override
    public String toString() {
        return "Team Name: " + teamName + ", Game: " + game + ", Console: " + console;
    }
}