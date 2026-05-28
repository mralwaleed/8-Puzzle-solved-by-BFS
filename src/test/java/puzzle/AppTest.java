package puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void formatBoard_rendersZeroAsBlank() {
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        String result = App.formatBoard(board);
        assertTrue(result.contains("-"));
        assertTrue(result.contains("1"));
        assertFalse(result.contains("0"));
    }

    @Test
    void formatBoard_rendersThreeByThree() {
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        String result = App.formatBoard(board);
        String[] lines = result.trim().split("\n");
        assertEquals(3, lines.length);
    }
}
