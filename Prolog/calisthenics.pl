numbers(1).
numbers(3).
numbers(4).
real(3, 4).
list([1, 2, 3]).

addThree(X, Y) :- Y is X + 3.
adder(X, Y, Z) :- Z is X + Y.

triangleNumber(0, 0).
triangleNumber(X, Y) :- W is X - 1, triangleNumber(W, Z), Y is X + Z.

% Case for Empty List
memberOf(_, []) :- fail.
memberOf(X, [X | _]).
memberOf(X, [H | T]) :- X \== H, memberOf(X, T).

peek([], Num) :- fail.
peek([H], H).
peek([_|T], X) :- peek(T, X).

push(X, [], [X]).
push(X, [H | T], [H | W]) :- push(X, T, W). 

app([], [], []).
app([], Y, Y).
app(Y, [], Y).
app([H | T], Y, [H | F]) :- app(T, Y, F).

edge(slc, lax, 4).
edge(lax, sfo, 1).
edge(slc, sea, 2).
edge(sea, sfo, 1).
edge(sea, lax, 2).
edge(lax, sea, 2).

tc( From, To, Cost) :- tcWithCost( From, To, Cost).
tcWithCost(From, To, Cost) :- edge(From, To, Cost).
tcWithCost(From, To, Cost) :- edge(From, Y, CostX), tcWithCost(Y, To, CostY), Cost is CostX + CostY.

printList([]).
printList([H | T]) :- write(H), write('\n'), printList(T).