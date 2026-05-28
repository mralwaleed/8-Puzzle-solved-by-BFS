package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleNodeTest {

    private static final int[] GOAL = {1, 2, 3, 4, 5, 6, 7, 8, 0};

    @Test
    void isGoal_returnsTrueWhenMatchingGoal() {
        PuzzleNode node = new PuzzleNode(GOAL);
        assertTrue(node.isGoal(GOAL));
    }

    @Test
    void isGoal_returnsFalseWhenNotMatchingGoal() {
        int[] state = {1, 2, 3, 4, 5, 6, 7, 0, 8};
        PuzzleNode node = new PuzzleNode(state);
        assertFalse(node.isGoal(GOAL));
    }

    @Test
    void isSamePuzzle_detectsEqualStates() {
        int[] state = {4, 1, 3, 5, 7, 2, 0, 8, 6};
        PuzzleNode a = new PuzzleNode(state);
        PuzzleNode b = new PuzzleNode(state);
        assertTrue(a.isSamePuzzle(b.getPuzzle()));
    }

    @Test
    void isSamePuzzle_detectsDifferentStates() {
        PuzzleNode a = new PuzzleNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0});
        PuzzleNode b = new PuzzleNode(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        assertFalse(a.isSamePuzzle(b.getPuzzle()));
    }

    @Test
    void expandMoves_generatesCorrectChildCountForCenter() {
        int[] state = {1, 2, 3, 4, 0, 5, 6, 7, 8};
        PuzzleNode node = new PuzzleNode(state);
        node.expandMoves();
        assertEquals(4, node.getChildren().size());
    }

    @Test
    void expandMoves_generatesCorrectChildCountForCorner() {
        int[] state = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        PuzzleNode node = new PuzzleNode(state);
        node.expandMoves();
        assertEquals(2, node.getChildren().size());
    }

    @Test
    void expandMoves_generatesCorrectChildCountForEdge() {
        int[] state = {1, 0, 2, 3, 4, 5, 6, 7, 8};
        PuzzleNode node = new PuzzleNode(state);
        node.expandMoves();
        assertEquals(3, node.getChildren().size());
    }

    @Test
    void expandMoves_setsParentOnChildren() {
        int[] state = {1, 2, 3, 4, 0, 5, 6, 7, 8};
        PuzzleNode node = new PuzzleNode(state);
        node.expandMoves();
        for (PuzzleNode child : node.getChildren()) {
            assertSame(node, child.getParent());
        }
    }

    @Test
    void format_containsBlankMarker() {
        int[] state = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        PuzzleNode node = new PuzzleNode(state);
        String formatted = node.format();
        assertTrue(formatted.contains("-"));
    }

    @Test
    void constructor_doesNotMutateOriginal() {
        int[] original = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        PuzzleNode node = new PuzzleNode(original);
        original[0] = 99;
        assertEquals(1, node.getPuzzle()[0]);
    }
}
