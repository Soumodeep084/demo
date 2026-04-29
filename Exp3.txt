% Facts for Male
male(ramesh).
male(soumodeep).
male(kaushik).
male(shivam).
male(ankit).
male(tarak).
male(suresh).
male(bhimchandra).
male(brindabon).

% Facts for Female
female(sumi).
female(riya).
female(putul).
female(jaya).
female(rani).
female(shantilata).

% Facts for Father
father(ankit,ramesh).
father(tarak,soumodeep).
father(tarak,sumi).
father(suresh,kaushik).
father(suresh,shivam).
father(bhimchandra,riya).
father(bhimchandra,tarak).
father(brindabon,putul).
father(brindabon,jaya).

% Facts for Mother
mother(riya,ramesh).
mother(putul,soumodeep).
mother(putul,sumi).
mother(jaya,kaushik).
mother(jaya,shivam).
mother(rani,riya).
mother(rani,tarak).
mother(shantilata,putul).
mother(shantilata,jaya).
% Rules
parent(F, M, C) :- father(F, C), mother(M, C).

grandfather(GF, C) :- father(GF, F), father(F, C); 
father(GF, M), mother(M, C).

grandmother(GM, C) :- mother(GM, P), (father(P, C); mother(P, C)).

siblings(X, Y) :- father(F, X), father(F, Y), mother(M, X), mother(M, Y), X \= Y.

brother(X, Y) :- male(X), siblings(X, Y).

sister(X, Y) :- female(X), siblings(X, Y).

uncle(U, C) :- male(U), siblings(U, P), (father(P, C); mother(P, C)).

aunt(A, C) :- female(A), siblings(A, P), (father(P, C); mother(P, C)).

cousin(X, Y) :- (father(P1, X); mother(P1, X)),  (father(P2, Y); mother(P2, Y)),  siblings(P1, P2).
