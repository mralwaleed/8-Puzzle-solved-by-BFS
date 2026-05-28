package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BFSSearchTest {

    private static final int[] GOAL = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    @Test
    void solve_findsSolutionForAlreadySolvedPuzzle() {
        PuzzleNode root = new PuzzleNode(GOAL);
        BFSSearch search = new BFSSearch();
        BFSSearch.SearchResult result = search.solve(root, GOAL);

        assertTrue(result.solved);
        assertEquals(1, result.path.size());
    }

    @Test
    void solve_findsSolutionForOneMoveAway() {
        int[] oneAway = {1, 2, 3, 4, 5, 6, 7, 0, 8};
        PuzzleNode root = new PuzzleNode(oneAway);
        BFSSearch search = new BFSSearch();
        BFSSearch.SearchResult result = search.solve(root, GOAL);

        assertTrue(result.solved);
        assertEquals(2, result.path.size());
    }

    @Test
    void solve_findsSolutionForMultiStepPuzzle() {
        int[] initial = {4, 1, 3, 5, 7, 2, 0, 8, 6};
        PuzzleNode root = new PuzzleNode(initial);
        BFSSearch search = new BFSSearch();
        BFSSearch.SearchResult result = search.solve(root, GOAL);

        assertTrue(result.solved);
        assertTrue(result.path.size() > 2);
    }

    @Test
    void solve_returnsPathFromStartToGoal() {
        int[] initial = {1, 2, 3, 4, 0, 5, 7, 8, 6};
        PuzzleNode root = new PuzzleNode(initial);
        BFSSearch search = new BFSSearch();
        BFSSearch.SearchResult result = search.solve(root, GOAL);

        assertTrue(result.solved);
        assertArrayEquals(initial, result.path.get(0).getPuzzle());
        assertArrayEquals(GOAL, result.path.get(result.path.size() - 1).getPuzzle());
    }

    @Test
    void solve_returnsEmptyForUnsolvablePuzzle() {
        int[] unsolvable = {1, 2, 3, 4, 5, 6, 8, 7, 0};
        PuzzleNode root = new PuzzleNode(unsolvable);
        BFSSearch search = new BFSSearch();
        BFSSearch.SearchResult result = search.solve(root, GOAL);

        // Unsolvable: swapping 7 and 8 creates odd permutation parity
        assertFalse(result.solved);
        assertTrue(result.path.isEmpty());
    }
}
