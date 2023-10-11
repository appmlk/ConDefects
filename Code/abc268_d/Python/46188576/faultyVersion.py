N,M = map(int, input().split())

l = [input() for i in range(N)]
s = set([])
for i in range(M):
    s.add(input())
pt = []
free = 16
for i in l:
    free -= len(i)
free-=N-1
#print(free)

from itertools import permutations
per_all = list(permutations(range(N)))
per = []
def solve(now,free,le):
   # print(now,le)
    le += l[per[now]]
    now+=1
    if now == N:
        pt.append(le)
        return
    
    for i in range(free+1):
        solve(now,free-i,le+"_"*(i+1))
    return
for per in per_all:
    solve(0,free,"")
ans=False
for i in pt:
    if i not in s:
        print(i)
        exit()
print(-1)