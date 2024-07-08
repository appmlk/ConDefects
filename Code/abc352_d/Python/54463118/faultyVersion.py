
N, K = map(int, input().split())
P = list(map(int, input().split()))

C = [-1 for _ in range(N)]

for i, p in enumerate(P):
    C[p-1] = i+1

ans = 100000000000

from sortedcontainers import SortedList

s = SortedList(sorted(C[:K]))

for i in range(N-K):
    s.remove(C[i])
    s.add(C[i+K])
    ans = min(ans, s[-1]-s[0])
print(ans)