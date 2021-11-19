% CS 4700 HW 5
% Justin Reid
% A02276642

% The printList function prints any list element by element.
% INPUT: desired list
% OUTPUT: list printed to console
printList([]).
printList([H | T]) :- writeln(H), printList(T).

%---------------------------------------------------------
% The solve rooms function takes a castle and a list, and checks to see if you can reach the escape through with that list route.
% INPUT: Castle, List of desired rooms
% OUTPUT: printed route, and whether or not it was successful through the desired route.
solveRooms(Castle, L) :- tc(Castle, enter, exit, L, N), writeln(N), memberOf(L, N).
    
    tc(Castle, X, Y, L, [X|Y]) :- room(Castle, X, Y, _).
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
solveRoomsWithinCost(Castle, Limit) :- tcWithinCost(Castle, enter, exit, N, Cost), writeln(N), isWithinLimit(Limit, Cost).

    tcWithinCost(Castle, X, Y, [X|Y], Cost) :- room(Castle, X, Y, Cost).
    tcWithinCost(Castle, X, Y, [X|NL], Cost) :- room(Castle, X, W, CostX), tcWithinCost(Castle, W, Y, NL, CostY), Cost is CostX + CostY.

    isWithinLimit(X, Y) :- X > Y, write("The cost is "), write(Y), write(" within "), write(X), writeln(".\n").
    isWithinLimit(X, Y) :- X < Y, writeln("Fails").
    isWithinLimit(X, Y) :- X == Y, write("The cost is "), write(Y), write(" within "), write(X), writeln(".\n").
%---------------------------------------------------------
