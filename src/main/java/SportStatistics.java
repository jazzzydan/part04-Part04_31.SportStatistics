
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SportStatistics {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int gamesPlayed = 0;
        int winCount = 0;

        System.out.println("File:");
        String file = scan.nextLine();

        ArrayList<Game> gameData = readGameData(file);
        System.out.println("Team:");
        String team = scan.nextLine();
        for (Game particularGame : gameData) {

            if (particularGame.getHomeTeam().equals(team)) {
                gamesPlayed++;
                if (particularGame.getHomeTeamPoints() > particularGame.getVisitingTeamPoints()) {
                    winCount++;
                }
            }
            if (particularGame.getVisitingTeam().equals(team)) {
                gamesPlayed++;
                if (particularGame.getHomeTeamPoints() < particularGame.getVisitingTeamPoints()) {
                    winCount++;
                }
            }

        }
        System.out.println("Games: " + gamesPlayed);
        System.out.println("Wins: " + winCount);
        System.out.println("Losses: " + (gamesPlayed - winCount));
    }

    public static ArrayList<Game> readGameData (String file) {

        ArrayList<Game> games = new ArrayList<>();
        try (Scanner fileReader = new Scanner(Paths.get(file))) {

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                String homeTeam = parts[0];
                String visitingTeam = parts[1];
                int homeTeamPoints = Integer.valueOf(parts[2]);
                int visitingTeamPoints = Integer.valueOf(parts[3]);

                games.add(new Game(homeTeam, visitingTeam, homeTeamPoints, visitingTeamPoints));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return games;
    }
}
