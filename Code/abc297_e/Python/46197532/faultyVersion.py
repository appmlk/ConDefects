from heapq import heappush, heappop

n, k = map(int,input().split())
A = list(map(int,input().split()))

hq = []
ans = set()
for a in A:
    heappush(hq, a)
    ans.add(a)

for _ in range(k):
    u = heappop(hq)
    print(u)
    for a in A:
        if u + a not in ans:
            heappush(hq, u+a)
            ans.add(u+a)

print(u)


