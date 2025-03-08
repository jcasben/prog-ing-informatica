:- consult(query).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Implementation of trees2nodes
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Base case
trees2nodes(0, [], []).

% Recursive case
trees2nodes(n(L, Left, Right), [L, L1, L2], Nodes) :-
    (Left = 0 -> L1 = 0 ; Left = n(L1, _, _)),
    (Right = 0 -> L2 = 0; Right = n(L2, _, _)),

    trees2nodes(Left, _, LeftNodes),
    trees2nodes(Right, _, RightNodes),

    append([[L, L1, L2]], LeftNodes, TempNodes),
    append(TempNodes, RightNodes, Nodes).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Implementation of nodes2trees
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

nodes2trees(Nodes, Result) :-
    % Find the root node (the one whose label is not a child in any other node)
    find_root(Nodes, Root),

    % Build the tree starting from the root
    build_tree(Root, Nodes, Tree),

    Result = Tree.

find_root(Nodes, Root) :-
    member([Root, _, _], Nodes),  % Root is a label in the nodes
    \+ (member([_, Left, Right], Nodes), (Left = Root ; Right = Root)).  % Root is not a child in any other node

% build_tree/3: Builds the tree recursively
build_tree(0, _, 0).  % Base case: empty tree
build_tree(Root, Nodes, n(Root, LeftTree, RightTree)) :-
    % Find the 3-tuple for the current root
    member([Root, LeftChild, RightChild], Nodes),

    % Recursively build the left and right subtrees
    build_tree(LeftChild, Nodes, LeftTree),
    build_tree(RightChild, Nodes, RightTree).