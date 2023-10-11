INT = lambda : int(input())
MI = lambda : map(int, input().split())
MI_DEC = lambda : map(lambda x : int(x)-1, input().split())
LI = lambda : list(map(int, input().split()))
LI_DEC = lambda : list(map(lambda x : int(x)-1, input().split()))
INF = float('inf')

N = INT()
A = LI()
M = INT()
B = LI()
X = INT()

dp = [False] * (X+1)
dp[0] = True
cantMove = [False] * (X+1)
for i in range(M):
    cantMove[B[i]] = True


for i in range(X):
    if cantMove[i]:
        continue

    for a in A:
        if i + a <= X:
            dp[i+a] = True

print('Yes' if dp[X] else 'No')