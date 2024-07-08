from collections import defaultdict


N, M = map(int, input().split())
A = list(map(int, input().split()))
idx = defaultdict(list)
for i in range(N - 1, -1, -1):
    idx[A[i]].append(i)

used = set()
ans = []
for a in A:
    idx[a].pop()
    if a in used:
        continue
    while ans and ans[-1] > a and idx[ans[-1]]:
        used.discard(ans.pop())
    ans.append(a)
    used.add(a)
print(*ans)