package game;

import java.util.ArrayList;
import java.net.URL;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static ArrayList<String> players = new ArrayList<>();
    public static ArrayList<Integer> score = new ArrayList<>();
    private static HashMap<String,Integer> highscore = new HashMap<>();
    private static boolean quit = false;
    public static Game game;
    public static Game game2;
    public static int scoresize = 0;
   

    public static void main(String[] args) {
        System.out.println("\nEnter the player name:\n ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        game = new Game("");
        game.playername = name;

        if (args.length == 0) {
            String runeberg =
            "http://runeberg.org/words/ord.ortsnamn.posten";
            game.playHangman(game.download(runeberg, "ISO-8859-1"));
        } else {
            int rnd = (int) (Math.random() * args.length);
            game.playHangman(args[rnd]);
        }
        players.add(name);
        highscore.put(game.playername, 0);
        if (score.get(scoresize-1) > highscore.get(game.playername)) {
            highscore.put(game.playername, score.get(scoresize-1));
        }
        
        while (!quit) {
            System.out.println("Choose an option:");
            int choice = UserInterface.readChoice(new String[] {
                    "1. Play game",
                    "2. View high scores",
                    "3. View high scores for specific player",
                    "4. Quit"
            });

            switch (choice) {
                case 1:
                    System.out.println("\nEnter the player name:\n ");
                    Scanner scanner3 = new Scanner(System.in);
                    String name3 = scanner3.nextLine(); 
                    game2  = new Game("");
                    game2.playername = name3;
                    players.add(name3);
                    game2.run(); 
                    
                    if (highscore.get(game2.playername) == null){
                        highscore.put(game2.playername, 0);
                    }
                    if (score.get(scoresize-1) > highscore.get(game2.playername)) {
                        highscore.put(game2.playername, score.get(scoresize-1));
                    }
                    break;

                case 2:
                    UserInterface.showHighScores(highscore , score, players);
                    break; 

                case 3:
                    System.out.println("\nEnter the player name:\n ");
                    Scanner scanner2 = new Scanner(System.in);
                    String name2 = scanner2.nextLine();
                    UserInterface.showHighScores2(highscore, name2);
                    break;

                case 4:
                    quit = true;
                    break; 
            }
        } 


        
    }
}
