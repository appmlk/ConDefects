import heapq
inf = float("inf")
N, K = map(int, input().split())

A = [0]
B = []
T = []
for _ in range(N):
    t, y = map(int, input().split())
    if t == 1:
        A.append(y)
        B.append(T)
        T = []
    else:
        T.append(y)
B.append(T)


ans = -float("inf")
hq = []
S = 0
sute = []
while A:
    a = A.pop()
    X = B.pop()
    for x in X:
        S += x
        heapq.heappush(hq, x)
    
    while len(sute) < K + 1000 and hq and hq[0] < 0:
        t = heapq.heappop(hq)
        S -= t
        heapq.heappush(sute, -t)
    
    while sute and hq and -sute[0] > hq[0]:
        a = -heapq.heappop(sute)
        b = heapq.heappop(hq)
        
        S += a - b
        
        heapq.heappush(hq, a)
        heapq.heappush(sute, -b)
    
    
    while len(sute) > K:
        t = -heapq.heappop(sute)
        S += t
        heapq.heappush(hq, t)
    
    
    ans = max(ans, a + S)
    
    
    K -= 1
    if K < 0:
        break

print(ans)