% === Printing the list of moves ===
% Print a list of [row, column] coordinates.
% Input: A list of lists.
% Output: None.
% Result: true.
% Side Effects: stdout.
printList([H]) :- write(H), write('\n').
printList([H | T]) :- write(H), write('\n'), printList(T).


% === Printing the maze ===
% Print the corners of the maze.
% Input: Maze, _, Row, Column.
% Output: None.
% Result: true.
% Side Effects: stdout.
printCell(_, _, 0, 0) :- write('+').
printCell(Maze, _, Row, 0) :- mazeSize(Maze, Rows, _), Row is Rows + 1, write('+').
printCell(Maze, _, 0, Column) :- mazeSize(Maze, _, Columns), Column is Columns + 1, write('+').
printCell(Maze, _, Row, Column) :- mazeSize(Maze, Rows, Columns), Row is Rows + 1, Column is Columns + 1, write('+').
% Print the top and bottom of the maze.
% Input: Maze, _, Row, _.
% Output: None.
% Result: true.
% Side Effects: stdout.
printCell(_, _, 0, _) :- write('-').
printCell(Maze, _, Row, _) :- mazeSize(Maze, Rows, _), Row is Rows + 1, write('-').
% Print the left and right of the maze.
% Input: Maze, _, _, Column.
% Output: None.
% Result: true.
% Side Effects: stdout.
printCell(_, _, _, 0) :- write('|').
printCell(Maze, _, _, Column) :- mazeSize(Maze, _, Columns), Column is Columns + 1, write('|').
% Print a barrier in the maze.
% Input: Maze, _, Row, Column.
% Output: None.
% Result: true if the cell is a barrier.
% Side Effects: stdout if true.
printCell(Maze, _, Row, Column) :- maze(Maze, Row, Column, barrier), write('x').
% Print a path in the maze.
% Input: Maze, List of path coordinates (row, column), Row, Column.
% Output: None.
% Result: true if the cell is on the path.
% Side Effects: stdout if true.
printCell(Maze, [[Y, X] | T], Row, Column) :- Y is Row, X is Column, write('*'); printCell(Maze, T, Row, Column).
% Print an empty cell in the maze.
% Input: Maze, _, Row, Column.
% Output: None.
% Result: true if the cell is empty.
% Side Effects: stdout if true.
printCell(Maze, _, Row, Column) :- maze(Maze, Row, Column, open), write(' ').

% Check if moving down is still inside the maze.
% Input: Maze, Row, Column.
% Output: NextRow, NextColumn.
% Result: true if moving down is inside the maze.
% Side Effects: None.
insideDown(Maze, Row, Column, NextRow, NextColumn) :- NextRow is Row + 1, NextColumn is Column, mazeSize(Maze, Rows, _), Row =< Rows.
% Check if moving right is still inside the maze.
% Input: Maze, Row, Column.
% Output: NextRow, NextColumn.
% Result: true if moving right is inside the maze.
% Side Effects: None.
insideRight(Maze, Row, Column, NextRow, NextColumn) :- NextRow is Row, NextColumn is Column + 1, mazeSize(Maze, _, Columns), Column =< Columns.

% Print the cells in a row of the maze.
% Input: Maze, List (path), Row, Column.
% Output: None.
% Result: false.
% Side Effects: stdout.
printRow(Maze, List, Row, Column) :- not(printCell(Maze, List, Row, Column));
    insideRight(Maze, Row, Column, NextRow, NextColumn), printRow(Maze, List, NextRow, NextColumn).
% Print the rows of the maze.
% Input: Maze, List (path), Row.
% Output: None.
% Result: false.
% Side Effects: stdout.
printMaze(Maze, List, Row) :- printRow(Maze, List, Row, 0); write('\n'),
    insideDown(Maze, Row, 0, NextRow, 0), printMaze(Maze, List, NextRow).
% Print the maze.
% Input: Maze, List (path).
% Output: None.
% Result: true.
% Side Effects: stdout.
printMaze(Maze, List) :- printMaze(Maze, List, 0); true.


% === Solving the maze ===
% Check if a set of [row, column] coordinates is in a list
% Input: Coordinates, List.
% Output: None.
% Result: true if coordinates are in the list.
% Side Effects: None.
inList(_, []) :- false.
inList([Y, X], [[LY, LX] | T]) :- X is LX, Y is LY; inList([Y, X], T).

% Check if you can move up.
% Input: Maze, List of moves, Row, Column.
% Output: NextRow, NextColumn.
% Result: true if the new location is open and is not in the list of moves.
% Side Effects: None.
tryUp(Maze, List, Row, Column, NextRow, NextColumn) :- NextRow is Row - 1, NextColumn is Column, NextRow > 0,
    maze(Maze, NextRow, NextColumn, open), not(inList([NextRow, NextColumn], List)).
% Check if you can move down.
% Input: Maze, List of moves, Row, Column.
% Output: NextRow, NextColumn.
% Result: true if the new location is open and is not in the list of moves.
% Side Effects: None.
tryDown(Maze, List, Row, Column, NextRow, NextColumn) :- NextRow is Row + 1, NextColumn is Column, mazeSize(Maze, Rows, _), NextRow =< Rows,
    maze(Maze, NextRow, NextColumn, open), not(inList([NextRow, NextColumn], List)).
% Check if you can move left.
% Input: Maze, List of moves, Row, Column.
% Output: NextRow, NextColumn.
% Result: true if the new location is open and is not in the list of moves.
% Side Effects: None.
tryLeft(Maze, List, Row, Column, NextRow, NextColumn) :- NextRow is Row, NextColumn is Column - 1, NextColumn > 0,
    maze(Maze, NextRow, NextColumn, open), not(inList([NextRow, NextColumn], List)).
% Check if you can move right.
% Input: Maze, List of moves, Row, Column.
% Output: NextRow, NextColumn.
% Result: true if the new location is open and is not in the list of moves.
% Side Effects: None.
tryRight(Maze, List, Row, Column, NextRow, NextColumn) :- NextRow is Row, NextColumn is Column + 1, mazeSize(Maze, _, Columns), NextColumn =< Columns,
    maze(Maze, NextRow, NextColumn, open), not(inList([NextRow, NextColumn], List)).
% Check if the cell is the goal.
% Input: Maze, Row, Column.
% Output: None.
% Result: true if the cell is the goal.
% Side Effects: None.
isGoal(Maze, Row, Column) :- mazeSize(Maze, Rows, Columns), Row is Rows, Column is Columns.
% Check if the cell is the goal. If it is not, then recursively try all possible directions to solve the maze.
% Input: Maze, List of moves, Row, Column.
% Output: OutList.
% Result: true if there is a path to the goal.
% Side Effects: None.
move(Maze, List, OutList, Row, Column) :- append(List, [[Row, Column]], NewList),
    (isGoal(Maze, Row, Column), append(NewList, [], OutList);
    (tryRight(Maze, List, Row, Column, NextRow, NextColumn);
    tryDown(Maze, List, Row, Column, NextRow, NextColumn);
    tryLeft(Maze, List, Row, Column, NextRow, NextColumn);
    tryUp(Maze, List, Row, Column, NextRow, NextColumn)),
    move(Maze, NewList, OutList, NextRow, NextColumn)).
% Solve a maze. If a solution is found, then print the maze and the path.
% Input: Maze.
% Output: None.
% Result: true if there is a path to the goal.
% Side Effects: stdout if true.
solve(Maze) :- move(Maze, [], MoveList, 1, 1), printList(MoveList), printMaze(Maze, MoveList).