from heapq import heappop,heappush
import sys
read = sys.stdin.buffer.read

N,M,*DATA = map(int,read().split())
ST = DATA[:2*M]
it = iter(DATA[2*M:])
LR = [[0,0]]
for l,r in zip(it,it):
    LR.append([l,r])

links = [[] for _ in range(N+1)]
links_rev = [[] for _ in range(N+1)]
it = iter(ST)
for s,t in zip(it,it):
    links[s].append(t)
    links_rev[t].append(s)

# DAG判定 & L修正
deg = [len(x) for x in links_rev]
stack = []
for i in range(1,N+1):
    if deg[i] == 0:
        stack.append(i)

while stack:
    i = stack.pop()
    for j in links[i]:
        deg[j] -= 1
        if deg[j] == 0:
            stack.append(j)
            ls = []
            for k in links_rev[j]:
                ls.append(LR[k][0])
            ls.sort()

            for idx in range(len(ls)-1):
                if ls[idx+1] <= ls[idx]:
                    ls[idx+1] = ls[idx] + 1
            
            LR[j][0] = ls[-1]+1

if sum(deg) != 0:
    print('No')
    exit()


# 上から決めていく
deg = [len(x) for x in links]
stack_r = [[] for _ in range(N+1)]
for i in range(1,N+1):
    if deg[i] == 0:
        li,ri = LR[i]
        if li > ri:
            print('No')
            exit()
        stack_r[ri].append(i)

hq = []
ans = [0] * (N+1)
base = (1<<20) - 1
for x in range(N,0,-1):
    while stack_r[x]:
        i = stack_r[x].pop()
        li = LR[i][0]
        num = (li << 20) + i
        num *= -1
        heappush(hq, num)
    
    if len(hq) == 0:
        print('No')
        exit()
    
    num = heappop(hq) * -1
    li = num >> 20
    i = num & base
    if li > x:
        print('No')
        exit()
    
    ans[i] = x
    for j in links_rev[i]:
        deg[j] -= 1
        if deg[j] == 0:
            lj,rj = LR[j]
            if lj > rj:
                print('No')
                exit()
            
            if rj >= x:
                num = (lj << 20) + j
                num *= -1
                heappush(hq, num)
            else:
                stack_r[rj].append(j)

# check
for i in range(1,N+1):
    li,ri = LR[i]
    if li <= ans[i] <= ri:
        continue
    print('No')
    exit()


print('Yes')
print(*ans[1:])