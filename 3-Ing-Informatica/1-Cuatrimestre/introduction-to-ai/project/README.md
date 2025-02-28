# Introduction to AI Project: Crossword Solver

## Description of the Solution
This project implements a solution for solving crossword puzzles as a Constraint Satisfaction Problem (CSP). Using backtracking and various heuristics, the program fills empty crossword grids with valid English words from a provided dictionary.

### CSP Formulation

- **Variables**: Each sequence of empty spaces (horizontal or vertical) in the grid that can hold a word.
- **Domains**: The set of words from `words.txt` that match the length of the variable.
- **Constraints**: 
  1. Words must fit in the grid without overlapping incorrectly.
  2. Intersecting words must share the same letter at their intersection.

### Steps to Solve
1. **Grid Parsing**:
   - The grid is represented as a 2D list of characters.
   - Possible positions for words (variables) are identified and stored as tuples `(start_row, start_col, length, direction)`.

2. **Domain Initialization**:
   - Words are grouped by their lengths for quick lookup.
   - Each variable's initial domain is the set of words matching its length.

3. **Backtracking Search**:
   - Variables are selected using the Minimum Remaining Values (MRV) heuristic to prioritize those with the smallest domains.
   - Words are assigned to variables, ensuring all constraints are satisfied.
   - If an assignment leads to a dead-end, the algorithm backtracks and tries a different word.

4. **Constraint Propagation**:
   - After assigning a word to a variable, domains of other variables are updated to maintain consistency.

5. **Output Generation**:
   - Once a grid is solved, it is stored in the output file.

### Key Functions
- **`can_write_word(position, word)`**: Checks if a word can be placed in a given position without violating constraints.
- **`write_word(position, word)`**: Writes a word into the grid.
- **`solve(crossword, words)`**: Implements the backtracking algorithm with heuristics.

### Heuristics
1. **Minimum Remaining Values (MRV)**: Selects the variable with the fewest valid words in its domain.
2. **Dynamic Domain Filtering**: Updates domains of unassigned variables after each assignment to maintain consistency.

This solution efficiently combines CSP techniques to solve crosswords of varying difficulty while adhering to constraints.

