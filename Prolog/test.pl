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
