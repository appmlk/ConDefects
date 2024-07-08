import heapq
from collections import Counter,deque,defaultdict
from functools import lru_cache,reduce
from heapq import heappush,heappop,heapify,heappushpop,_heappop_max,_heapify_max
def _heappush_max(heap,item):
    heap.append(item)
    heapq._siftdown_max(heap, 0, len(heap)-1)
def _heappushpop_max(heap, item):
    if heap and item < heap[0]:
        item, heap[0] = heap[0], item
        heapq._siftup_max(heap, 0)
    return item
from math import gcd as GCD

N,M=map(int,input().split())
graph=[[] for x in range(N)]
for m in range(M):
    l,d,k,c,a,b=map(int,input().split())
    a-=1;b-=1
    graph[b].append((a,l,d,k,c))
inf=1<<60
time=[-inf]*N
time[N-1]=inf
queue=[(inf,N-1)]
while queue:
    tx,x=_heappop_max(queue)
    if time[x]>tx:
        continue
    for y,l,d,k,c in graph[x]:
        if tx<l+c:
            continue
        if l+(k-1)*d+c<=tx:
            ty=l+(k-1)*d
        else:
            ty=tx-(tx-c-l)%d
        if time[y]<ty:
            time[y]=ty
            _heappush_max(queue,(ty,y))
for i in range(N-1):
    ans=time[i]
    if ans==-inf:
        ans="Unreachable"
    print(ans)