from heapq import heappush,heappop

n = int(input())
P = list(map(int,input().split()))

ans = 0

cand_heapq = []
selled_heapq = []

for p in P:
    s1 = -1
    s2 = -1
    if cand_heapq and cand_heapq[0] < p:
        s1 = p-cand_heapq[0]
        
    elif selled_heapq and selled_heapq[0] < p:
        s2 = p-selled_heapq[0]
    
    if s1 == s2 == -1:
        heappush(cand_heapq,p)
        continue

    if s1 >= s2:
        ans += p-heappop(cand_heapq)
        heappush(selled_heapq,p)
    else:
        s = heappop(selled_heapq)
        ans += p-s
        heappush(cand_heapq,s)
        heappush(selled_heapq,p)

print(ans)
