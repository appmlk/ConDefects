S, T = map(str, input().split())

for w in range(1, len(S)):
    S_cut = []
    for i in range(w, len(S) + 1, w):
        S_cut.append(S[i - w : i])
        if i + w > len(S):
            S_cut.append(S[i:])
    #    print(S_cut)
    for c in range(w):
        column = ""
        for s in S_cut:
            if c < len(s):
                column += s[c]
        #        print(c, column)
        if len(column) >= c and column == T:
            print("Yes")
            exit()

print("No")
