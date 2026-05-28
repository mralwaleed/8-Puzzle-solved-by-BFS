# 8-Puzzle BFS Solver

A Java implementation that solves the [8-puzzle](https://en.wikipedia.org/wiki/15_puzzle) sliding tile problem using Breadth-First Search. Given an initial board state and a goal state, it finds the shortest sequence of moves to reach the solution.

## Prerequisites

- Java 11 or higher
- Maven 3.6+

## Tech Stack

- Java 11 (no external runtime dependencies)
- Maven (build and dependency management)
- JUnit 5 (testing)

## Installation

```bash
git clone https://github.com/your-username/8-Puzzle-solved-by-BFS.git
cd 8-Puzzle-solved-by-BFS
mvn clean package
```

## Usage

### Input Format

Create or edit `input.TXT` with two 3x3 grids separated by whitespace — first the initial state, then the goal state. Use `-` to represent the blank tile.

Example (`input.TXT`):

```
4 1 3
5 7 2
- 8 6

1 2 3
4 5 6
7 8 -
```

### Run

```bash
# Default: reads input.TXT, writes output.txt
mvn exec:java -Dexec.mainClass="puzzle.App"

# Or with custom input/output files
java -jar target/eight-puzzle-bfs-1.0.0.jar input.TXT output.txt
```

### Output

The program writes the solution path to `output.txt` (or the specified output file), including:

- Initial and goal state
- Execution time in milliseconds
- Number of moves
- Step-by-step board states

## Project Structure

```
.
├── input.TXT                   # Sample puzzle input
├── pom.xml                     # Maven build config
├── LICENSE
├── README.md
└── src/
    ├── main/java/puzzle/
    │   ├── App.java            # Entry point — parses input, runs search, writes output
    │   ├── BFSSearch.java      # BFS algorithm with result tracing
    │   └── PuzzleNode.java     # Represents a puzzle state with move generation
    └── test/java/puzzle/
        ├── AppTest.java        # Tests for input/output formatting
        ├── BFSSearchTest.java  # Tests for BFS correctness (solvable, unsolvable, edge cases)
        └── PuzzleNodeTest.java # Tests for state comparison, move expansion, immutability
```

## Running Tests

```bash
mvn test
```

## Current Features

- BFS guarantees the shortest solution path
- Reads puzzle configuration from a plain text file
- Outputs step-by-step solution with timing info
- Unsolvable puzzle detection (odd parity states terminate gracefully)

## Roadmap

**1. Solvability Check (Pre-Search Optimization)**
Not all 8-puzzle configurations are solvable — exactly half of all permutations have the wrong parity. Adding an inversion-count check before running BFS would immediately reject unsolvable inputs instead of exploring the entire state space and timing out. Implement as a static method `PuzzleNode.isSolvable(int[] state, int[] goal)` in `PuzzleNode.java`, called from `App.java` before invoking the search.

**2. A\* Search with Manhattan Distance Heuristic**
BFS explores all states at depth *d* before depth *d+1*, which works for the 8-puzzle but won't scale to the 15-puzzle. Adding an alternative `AStarSearch.java` using Manhattan distance as a heuristic would keep the solver fast for larger boards. The `PuzzleNode` class already has the grid structure needed — just add a `manhattanDistance(int[] goal)` method.

**3. Interactive Step-Through Mode**
Add a CLI flag (e.g., `--interactive`) that prints each state and waits for the user to press Enter before showing the next move. Useful for visual learning and debugging. Implement in `App.java` by iterating over `result.path` with a `Scanner` reading `System.in`.

## Key Refactoring Changes

If you're coming from the original codebase, here's what changed:

- **`puzzle.java` removed** — was a dead Swing-based A\* implementation never called from the working entry point
- **`Node.java` → `PuzzleNode.java`** — fixed typos (`pirent`→`parent`, `chlidern`→`children`, `isGaol`→`isGoal`, `MoveToLift`→`moveLeft`), added proper encapsulation, replaced manual array copying with `System.arraycopy`
- **`Search.java` → `BFSSearch.java`** — fixed a logic bug in the open/closed list check (`||` changed to `&&`), wrapped result in a `SearchResult` object instead of raw list returns
- **`test.java` → `App.java`** — renamed to reflect its role as the entry point, made file paths configurable via CLI args, removed duplicate print method
- **Added Maven build** with JUnit 5, proper `.gitignore`, and unit tests for all core classes

## License

See [LICENSE](LICENSE).
