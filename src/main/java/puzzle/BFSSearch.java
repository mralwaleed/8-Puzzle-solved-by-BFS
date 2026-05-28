package puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BFSSearch {

    public static class SearchResult {
        public final List<PuzzleNode> path;
        public final boolean solved;

        public SearchResult(List<PuzzleNode> path, boolean solved) {
            this.path = path;
            this.solved = solved;
        }
    }

    public SearchResult solve(PuzzleNode root, int[] goal) {
        List<PuzzleNode> openList = new ArrayList<>();
        List<PuzzleNode> closedList = new ArrayList<>();
        openList.add(root);

        while (!openList.isEmpty()) {
            PuzzleNode current = openList.remove(0);
            closedList.add(current);

            current.expandMoves();

            for (PuzzleNode child : current.getChildren()) {
                if (child.isGoal(goal)) {
                    return new SearchResult(tracePath(child), true);
                }
                if (!contains(openList, child) && !contains(closedList, child)) {
                    openList.add(child);
                }
            }
        }

        return new SearchResult(Collections.emptyList(), false);
    }

    private List<PuzzleNode> tracePath(PuzzleNode node) {
        List<PuzzleNode> path = new ArrayList<>();
        PuzzleNode current = node;
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    private boolean contains(List<PuzzleNode> list, PuzzleNode node) {
        for (PuzzleNode item : list) {
            if (item.isSamePuzzle(node.getPuzzle())) {
                return true;
            }
        }
        return false;
    }
}
