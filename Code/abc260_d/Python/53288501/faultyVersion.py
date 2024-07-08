from sortedcontainers import SortedSet, SortedList, SortedDict
from collections import defaultdict
N, K = map(int,input().split())
P = list(map(int,input().split()))

INF = 10 ** 20
ans = [-1] * N
dic = defaultdict(list)
S = SortedSet([-INF, INF])

for i, value in enumerate(P):
    key = S[S.bisect_left(value)]
    if key == INF:
        print(0)
    if key is not INF and key is not - INF:
        dic[value] = dic.pop(key)
        S.discard(value)
        S.discard(key)
    dic[value].append(value)
    S.add(value)
    if len(dic[value]) == K:
        for y in dic.pop(value):
            ans[y - 1] = i + 1
        S.discard(value)

print(*ans)  
    

            
    

