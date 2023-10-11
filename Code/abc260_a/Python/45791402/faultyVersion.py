S = input()

if len(set(S)) == 1:
    print(-1)
elif len(set(S)) == 3:
    print(list(S)[0])
elif len(set(S)) == 2:
    S = list(S)
    s = S.count(S[0])
    if s == 1:
        print(S[0])
    else:
        print(S[1])

