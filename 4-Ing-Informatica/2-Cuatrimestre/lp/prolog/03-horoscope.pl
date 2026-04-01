horoscop(aries, 21, 3, 21, 4).
horoscop(toro, 21, 4, 21, 5).
horoscop(geminis, 21, 5, 21, 6).
horoscop(cancer, 21, 6, 21, 7).
horoscop(leo, 21, 7, 21, 8).
horoscop(virgo, 21, 8, 21, 9).
horoscop(libra, 21, 9, 21, 10).
horoscop(escorpi, 21, 10, 21, 11).
horoscop(sagitari, 21, 11, 21, 12).
horoscop(capricorn, 21, 12, 21, 1).
horoscop(aquari, 21, 1, 21, 2).
horoscop(peixos, 21, 2, 21, 3).

signe(D, M, S) :- horoscop(S, D1, M1, _, _), (M1 = M, D1 =< D).
signe(D, M, S) :- horoscop(S, _, _, D2, M2), (M2 = M, D2 > D).