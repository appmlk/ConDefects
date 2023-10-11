import sys

input = lambda: sys.stdin.readline().rstrip()


from collections import Counter, defaultdict, deque
from typing import DefaultDict, List

N, M = map(int, input().split())
P = list(map(int, input().split()))

G: DefaultDict[int, List[int]] = defaultdict(list)
for i, p in enumerate(P, 2):
    G[p].append(i)

ins = [0] * (N + 1)
for _ in range(M):
    x, y = map(int, input().split())
    ins[x] = max(ins[x], y + 1)

q = deque()
q.append(1)

while q:
    node = q.popleft()
    for nxt in G[node]:
        ins[nxt] = max(ins[nxt], ins[node] - 1)
        q.append(nxt)

print(N + 1 - ins.count(0))
