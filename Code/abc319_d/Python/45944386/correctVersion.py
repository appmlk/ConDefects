import sys
input = sys.stdin.readline

n, m = map(int, input().split())
L = list(map(int, input().split()))

ng, ok = max(L) - 1, sum(L) + n - 1
while ok - ng > 1:
    mid = (ok + ng) // 2
    res = 1
    temp = 0
    for i in range(n):
        temp += L[i]
        if temp > mid:
            res += 1
            temp = L[i] + 1
        else:
            temp += 1
    if res <= m:
        ok = mid
    else:
        ng = mid
print(ok)
