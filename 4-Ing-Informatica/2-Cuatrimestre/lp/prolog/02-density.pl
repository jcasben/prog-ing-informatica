poblation(palma, 300000).
poblation(manacor, 50000).
poblation(santa_maria, 4000).
surface(palma, 10).
surface(manacor, 6).
surface(santa_maria, 2).

density(C, D) :- poblation(C, P), surface(C, S),
                 D is P/S.