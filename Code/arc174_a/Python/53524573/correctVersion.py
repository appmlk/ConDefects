N, C = map(int, input().split())
A = list(map(int, input().split()))

S = [0]*(N + 1)
for i in range(N):
    S[i + 1] = S[i] + A[i]
INF = 10**18

if C > 1:
    m = 0
    ans = -INF
    for i in range(N):
        m = min(m, S[i + 1])
        ans = max(ans, S[i + 1] - m)
    print(S[N] + ans*(C - 1))
elif C <= 1:
    m = 0
    ans = INF
    for i in range(N):
        m = max(m, S[i + 1])
        ans = min(ans, S[i + 1] - m)
    print(S[N] + ans*(C - 1))