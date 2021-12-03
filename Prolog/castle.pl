% First castle for testing
% The castle is a set of room facts of the form
% room(Castle, FromRoom, ToRoom, cost).
room(dunstanburgh, enter, foyer, 1).
room(dunstanburgh, foyer, livingRoom, 1).
room(dunstanburgh, foyer, hall, 2).
room(dunstanburgh, hall, kitchen, 4).
room(dunstanburgh, hall, garage, 3).
room(dunstanburgh, kitchen, exit, 1).

% Second castle for testing
room(windsor, enter, foyer, 1).
room(windsor, foyer, hall, 2).
room(windsor, foyer, dungeon, 1).
room(windsor, hall, throne, 1).
room(windsor, hall, stairs, 4).
room(windsor, stairs, dungeon, 3).
room(windsor, throne, stairs, 1).
room(windsor, dungeon, escape, 5).
room(windsor, escape, exit, 1).

% Third castle for testing
room(alnwick, enter, foyer, 1).
room(alnwick, foyer, hall, 2).
room(alnwick, hall, throne, 1).
room(alnwick, hall, stairs, 4).
room(alnwick, stairs, dungeon, 3).
room(alnwick, dungeon, foundry, 5).
room(alnwick, foyer, passage, 1).
room(alnwick, passage, foundry, 1).
room(alnwick, foundry, exit, 4).

test1() :- writeln('solveRoomsWithinCost(dunstanburgh, 8)'),
           solveRoomsWithinCost(dunstanburgh, 8).
test2() :- writeln('solveRoomsWithinCost(windsor, 13)'),
           solveRoomsWithinCost(windsor, 13).
test3() :- writeln('solveRoomsWithinCost(alnwick, 15)'),
           solveRoomsWithinCost(alnwick, 15).
test4() :- writeln('solveRooms(dunstanburgh, [foyer, kitchen])'),
           solveRooms(dunstanburgh, [foyer, kitchen]).
test5() :- writeln('solveRooms(windsor, [stairs])'),
           solveRooms(windsor, [stairs]).
test6() :- writeln('solveRooms(alnwick, [foyer, hall])'),
           solveRooms(alnwick, [foyer, hall]).
test7() :- writeln('solveRooms(alnwick, [foyer, passage])'),
           solveRooms(alnwick, [foyer, passage]).
test8() :- writeln('fails: solveRooms(alnwick, [foyer, throne, escape])'),
           solveRooms(alnwick, [foyer, throne, passage]).
test9() :- writeln('fails: solveRoomsWithinCost(alnwick, 4)'),
           solveRoomsWithinCost(alnwick, 4).



% CS 4700 HW 5
% Justin Reid
% A02276642

% The printList function prints any list element by element.
% INPUT: desired list
% OUTPUT: list printed to console
printList([]) :- writeln("").
printList([H | T]) :- writeln(H), printList(T).

%---------------------------------------------------------
% The solve rooms function takes a castle and a list, and checks to see if you can reach the escape through with that list route.
% INPUT: Castle, List of desired rooms
% OUTPUT: printed route, and whether or not it was successful through the desired route.
solveRooms(Castle, L) :- tc(Castle, enter, exit, L, N), printList(N), memberOf(L, N).
    
    tc(Castle, X, Y, L, [X, Y]) :- room(Castle, X, Y, _).
    tc(Castle, X, Y, L, [X|NL]) :- room(Castle, X, W, _), tc(Castle, W, Y, L, NL).

    % Case for Empty List
    memberOf([], _) :- true.
    memberOf([H], [X | Y]) :- H == X, true.
    memberOf([H | T], [X | Y]) :- H == X, allMembersOf(T, Y).
    memberOf([H | T], [X | Y]) :- H \== X, memberOf([H | T], Y).

    allMembersOf([], _) :- writeln("true."), write("\n").
    allMembersOf([H | T], [X | Y]) :- H == X, allMembersOf(T, Y).
    allMembersOf([H | T], [X | Y]) :- H \== X, writeln("false."), write("\n").
%---------------------------------------------------------

%---------------------------------------------------------
% The solve rooms within cost function takes a list, and a cost, and chests to see if you can reach the escape within the desired cost.
% INPUT: Castle, desired cost limit
% OUTPUT: Printed route, and whether the route was within the desired cost.
solveRoomsWithinCost(Castle, Limit) :- tcWithinCost(Castle, enter, exit, N, Cost), printList(N), isWithinLimit(Limit, Cost).

    tcWithinCost(Castle, X, Y, [X, Y], Cost) :- room(Castle, X, Y, Cost).
    tcWithinCost(Castle, X, Y, [X|NL], Cost) :- room(Castle, X, W, CostX), tcWithinCost(Castle, W, Y, NL, CostY), Cost is CostX + CostY.

    isWithinLimit(X, Y) :- X > Y, write("The cost is "), write(Y), write(" within "), write(X), writeln(".\n").
    isWithinLimit(X, Y) :- X < Y, writeln("Fails").
    isWithinLimit(X, Y) :- X == Y, write("The cost is "), write(Y), write(" within "), write(X), writeln(".\n").
%---------------------------------------------------------
