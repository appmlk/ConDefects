from bisect import *

N = int(input())
S = input()
X = [[] for _ in range(3)]
for i, s in enumerate(S):
    X[ord(s) - 65].append(i)
#print(X)
ans = ["0"] * (3 * N)

n = N

for i in range(1, 7):
    start = 3 * N
    for j in range(3):
        if X[j][0] < start:
            start = X[j][0]
            c1 = j
    stop = -1
    for j in range(3):
        if j == c1:
            continue
        if X[j][-1] > stop:
            stop = X[j][-1]
            c3 = j
    c2 = (3 ^ c1 ^ c3)

    for j in range(n):
        a1 = X[c1][j]
        a3 = X[c3][-j - 1]
        d1 = bisect_left(X[c2], a1)
        d2 = bisect_left(X[c2], a3)
        if d2 - d1 <= j:
            j -= 1
            break
        d0 = d1

    for k in range(j + 1):
        ans[X[c1][k]] = str(i)
        ans[X[c2][d0 + k]] = str(i)
        ans[X[c3][-k - 1]] = str(i)
    X[c1] = X[c1][j + 1:]
    X[c2] = X[c2][:d0] + X[c2][d0 + j + 1:]
    X[c3] = X[c3][:-j - 1]
    #print(j + 1, c1, c2, c3, " ", d0, X, ans)
    n = n - j - 1
    if n == 0: break
print("".join(ans))
'''
for j in range(1, i + 1):
    tmp = ""
    for k in range(3 * N):
        if ans[k] == str(j):
            tmp += S[k]
    print(j, tmp)
'''