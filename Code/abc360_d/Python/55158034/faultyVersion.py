from bisect import bisect_right
N, T = map(int, input().split())
S = list(input())
X = list(map(int, input().split()))
LS = len(S)
ans = 0
Z = []
for i in range(LS):
    if S[i] == '0':
        Z.append(X[i])
Z.sort()
for i in range(LS):
    if S[i] == '1':
        ans += bisect_right(Z, X[i] * 2 * T) - bisect_right(Z, X[i])
print(ans)