from collections import Counter, deque
import sys
sys.setrecursionlimit(2*10**5)

def dfs(v):
    for _ in range(D[v+1]-len(G[v])+1):
        ans.append(v+1)
        D[v+1]-=1
    seen[v] = True
    for nv in G[v]:
        if seen[nv]:
            continue
        dfs(nv)
        if D[v+1]>0:
            ans.append(v+1)
            D[v+1]-=1
    

T = int(input())
for _ in range(T):
    N = int(input())
    C = list(map(int, input().split()))

    cnt = Counter(C)

    if len(cnt)<=2:
        C.sort()
        print(*C)
    else:
        G = [[] for _ in range(N)]
        a = deque()
        b = deque()
        X = 0
        for c in cnt.most_common():
            if c[1]>=2:
                b.append([c[0]-1, c[1]])
                X += c[1]
            else:
                a.append(c[0]-1)

        if X+len(a)<2*len(cnt)-2:
            C.sort()
            print(*C)
        else:
            while len(b)>0 and len(a)>0:
                k, v = b.popleft()
                while v>1 and len(a)>0:
                    l = a.popleft()
                    G[k].append(l)
                    G[l].append(k)
                    v-=1
                if v==1:
                    a.append(k)
                elif v>1:
                    b.append([k, v])

            if len(b)>0:
                k, v = b.popleft()
                while len(b)>0:
                    kk, vv = b.popleft()
                    G[k].append(kk)
                    G[kk].append(k)
                    k = kk
            if len(a)>0:
                k = a.popleft()
                while len(a)>0:
                    kk = a.popleft()
                    G[k].append(kk)
                    G[kk].append(k)
                    k = kk
            #print(G)
            #print(cnt)

            for i in range(N):
                if len(G[i])>0:
                    break
            
            D = dict(cnt)
            #print(D)

            seen = [False for _ in range(N)]
            ans = []
            v = i
            dfs(v)
            #print(D)
            print(*ans)
