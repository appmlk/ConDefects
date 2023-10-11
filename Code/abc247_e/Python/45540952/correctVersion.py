import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


N, X, Y, *A = map(int, read().split())

index_X = -1
index_Y = -1
index_ng = -1
ans = 0

for i in range(N):
    if A[i] == X:
        index_X = i
    if A[i] == Y:
        index_Y = i
    elif A[i] > X or A[i] < Y:
        index_ng = i
    ans += max(0, min(index_X, index_Y) - index_ng)

print(ans)
