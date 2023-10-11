N = int(input())
S = list(input())
T = [0] * N
X = [[] for _ in range(26)]
cmin = 26
for i, s in enumerate(S):
    a = ord(s) - 97
    T[i] = a
    X[a].append(i)
    cmin = min(cmin, a)
j = N
i = 0
while i < j:
    if T[i] != cmin:
        if X[cmin]:
            if i < X[cmin][-1] < j:
                j = X[cmin].pop(-1)
                S[j] = S[i]
                S[i] = chr(cmin + 97)
                i += 1
            else:
                X[cmin] = []
        else:
            if cmin < 25:
                cmin += 1
            else:
                break
    else:
        i += 1
print("".join(S))
