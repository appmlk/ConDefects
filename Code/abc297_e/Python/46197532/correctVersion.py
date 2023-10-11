from heapq import heappush, heappop

n, k = map(int,input().split())
A = list(map(int,input().split()))
A = list(set(A))

hq = []
ans = set()
for a in A:
    heappush(hq, a)
    ans.add(a)

for _ in range(k):
    u = heappop(hq)
    for a in A:
        if u + a not in ans:
            heappush(hq, u+a)
            ans.add(u+a)

print(u)


