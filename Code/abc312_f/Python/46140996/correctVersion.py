N,M = map(int, input().split())
T0 = []
T1 = []
T2 = []
for i in range(N):
    t,x = map(int, input().split())
    if t==0:
        T0.append(x)
    elif t==1:
        T1.append(x)
    elif t==2:
        T2.append(x)
T0.sort(reverse=True)
T1.sort()
T2.sort(reverse=True)
import heapq
ans = 0
que = T0[:min(len(T0),M)]
ans = sum(que)
#print(ans)
now = ans
heapq.heapify(que)
for cnt in T2:
    for i in range(cnt):
        if len(T1)==0:
            break

        tmp = T1.pop()
        heapq.heappush(que,tmp)
        now += tmp
    M = max(0,M-1)
    while len(que)>M:
        now-=heapq.heappop(que)
   # print(now,que)
    ans = max(now,ans)
print(ans)



