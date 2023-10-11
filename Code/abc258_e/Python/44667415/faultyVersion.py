from collections import deque

N, Q, X = map(int, input().split())
W = list(map(int, input().split()))
right = [0] * N
SUM = sum(W)
A = X // SUM
B = X % SUM
count = [N * A] * N

deq = deque([])
r = 0
tmp = 0
# while tmp < B:
#     deq.append(W[r])
#     tmp += W[r]
#     r += 1
#     r %= N
# right[0] = r
# count[0] += r
# if r == 0:
#     count[0] = N
for l in range(N):
    if deq:
        l_w = deq.popleft()
        tmp -= l_w
    while tmp < B:
        deq.append(W[r])
        tmp += W[r]
        r += 1
        r %= N
    right[l] = r
    count[l] += (r - l) % N
    if r == l:
        count[l] += N


M = 41
dp = [ [0] * N for _ in range(M)]
for i in range(N):
    dp[0][i] = right[i]

for i in range(1, M):
    for j in range(N):
        dp[i][j] = dp[i - 1][dp[i - 1][j]]

def query(k : int):
    p = 0
    for i in range(M):
        if k >> i & 1:
            p = dp[i][p]
    return count[p]

for _ in range(Q):
    k = int(input()) - 1
    ans = query(k)
    print(ans)