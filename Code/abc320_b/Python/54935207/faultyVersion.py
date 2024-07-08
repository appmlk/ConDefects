
S = input()
N = len(S)

ans = 0

for i in range(N):
    for j in range(i + 1, N + 1):
        T = S[i:j]
        if T == T[::-1]:
            ans = max(ans, len(T))

print(T)