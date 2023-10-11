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
    elif S[0] == S[1]:
        print(S[2])
    else:
        print(S[1])