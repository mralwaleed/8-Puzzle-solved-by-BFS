package puzzle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final int SIZE = 9;
    private static final String DEFAULT_INPUT = "input.TXT";
    private static final String DEFAULT_OUTPUT = "output.txt";

    public static void main(String[] args) throws IOException {
        String inputFile = args.length > 0 ? args[0] : DEFAULT_INPUT;
        String outputFile = args.length > 1 ? args[1] : DEFAULT_OUTPUT;

        int[] initial = new int[SIZE];
        int[] goal = new int[SIZE];

        parseInput(inputFile, initial, goal);

        PuzzleNode root = new PuzzleNode(initial);
        BFSSearch search = new BFSSearch();

        long start = System.currentTimeMillis();
        BFSSearch.SearchResult result = search.solve(root, goal);
        long elapsed = System.currentTimeMillis() - start;

        writeOutput(outputFile, initial, goal, result, elapsed);
        System.out.println("Done. Output written to " + outputFile);
    }

    static void parseInput(String filename, int[] initial, int[] goal) throws IOException {
        StringBuilder raw = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                raw.append(scanner.nextLine());
            }
        }

        String compact = raw.toString().replaceAll("\\s", "");

        for (int i = 0; i < SIZE; i++) {
            String ch = compact.substring(i, i + 1);
            initial[i] = ch.equals("-") ? 0 : Integer.parseInt(ch);
        }

        for (int i = 0; i < SIZE; i++) {
            String ch = compact.substring(SIZE + i, SIZE + i + 1);
            goal[i] = ch.equals("-") ? 0 : Integer.parseInt(ch);
        }
    }

    static void writeOutput(String filename, int[] initial, int[] goal,
                            BFSSearch.SearchResult result, long elapsed) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Initial state:\n");
            writer.write(formatBoard(initial));
            writer.write("Goal state:\n");
            writer.write(formatBoard(goal));

            if (result.solved) {
                writer.write("Solution found\n");
                writer.write("Execution time: " + elapsed + " ms\n");
                writer.write("Moves: " + (result.path.size() - 1) + "\n\n");
                for (int i = 0; i < result.path.size(); i++) {
                    writer.write("Step " + i + ":\n");
                    writer.write(result.path.get(i).format());
                }
            } else {
                writer.write("No solution found\n");
            }
        }
    }

    static String formatBoard(int[] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            sb.append(board[i] == 0 ? "-" : board[i]).append(" ");
            if ((i + 1) % 3 == 0) sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
