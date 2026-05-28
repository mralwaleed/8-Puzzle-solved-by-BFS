package puzzle;

import java.util.ArrayList;
import java.util.List;

public class PuzzleNode {

    private static final int SIZE = 9;
    private static final int COLS = 3;

    private final int[] puzzle;
    private PuzzleNode parent;
    private final List<PuzzleNode> children;

    public PuzzleNode(int[] state) {
        this.puzzle = new int[SIZE];
        System.arraycopy(state, 0, this.puzzle, 0, SIZE);
        this.children = new ArrayList<>();
    }

    public int[] getPuzzle() {
        return puzzle;
    }

    public PuzzleNode getParent() {
        return parent;
    }

    public List<PuzzleNode> getChildren() {
        return children;
    }

    public boolean isGoal(int[] goal) {
        for (int i = 0; i < SIZE; i++) {
            if (puzzle[i] != goal[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isSamePuzzle(int[] other) {
        for (int i = 0; i < SIZE; i++) {
            if (puzzle[i] != other[i]) {
                return false;
            }
        }
        return true;
    }

    public void expandMoves() {
        int blankIndex = -1;
        for (int i = 0; i < SIZE; i++) {
            if (puzzle[i] == 0) {
                blankIndex = i;
                break;
            }
        }
        moveRight(blankIndex);
        moveLeft(blankIndex);
        moveUp(blankIndex);
        moveDown(blankIndex);
    }

    public String format() {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (int row = 0; row < COLS; row++) {
            for (int col = 0; col < COLS; col++) {
                sb.append(puzzle[idx] == 0 ? "-" : puzzle[idx]).append(" ");
                idx++;
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }

    private void addChild(int[] state) {
        PuzzleNode child = new PuzzleNode(state);
        child.parent = this;
        children.add(child);
    }

    private void moveRight(int blank) {
        if (blank % COLS < COLS - 1) {
            int[] copy = copyPuzzle();
            swap(copy, blank, blank + 1);
            addChild(copy);
        }
    }

    private void moveLeft(int blank) {
        if (blank % COLS > 0) {
            int[] copy = copyPuzzle();
            swap(copy, blank, blank - 1);
            addChild(copy);
        }
    }

    private void moveUp(int blank) {
        if (blank - COLS >= 0) {
            int[] copy = copyPuzzle();
            swap(copy, blank, blank - COLS);
            addChild(copy);
        }
    }

    private void moveDown(int blank) {
        if (blank + COLS < SIZE) {
            int[] copy = copyPuzzle();
            swap(copy, blank, blank + COLS);
            addChild(copy);
        }
    }

    private int[] copyPuzzle() {
        int[] copy = new int[SIZE];
        System.arraycopy(puzzle, 0, copy, 0, SIZE);
        return copy;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
