import sys, random
input = lambda : sys.stdin.readline().rstrip()


write = lambda x: sys.stdout.write(x+"\n"); writef = lambda x: print("{:.12f}".format(x))
debug = lambda x: sys.stderr.write(x+"\n")
YES="Yes"; NO="No"; pans = lambda v: print(YES if v else NO); INF=10**18
LI = lambda : list(map(int, input().split())); II=lambda : int(input()); SI=lambda : [ord(c)-ord("a") for c in input()]
def debug(_l_):
    for s in _l_.split():
        print(f"{s}={eval(s)}", end=" ")
    print()
def dlist(*l, fill=0):
    if len(l)==1:
        return [fill]*l[0]
    ll = l[1:]
    return [dlist(*ll, fill=fill) for _ in range(l[0])]

# グラフの読み込み・重みあり
n,m,start = map(int, input().split())
start -= 1
ns = [[] for _ in range(2*n)]
rns = [[] for _ in range(2*n)]
odeg = [0]*(2*n)
for _ in range(m):
    u,v,c = map(int, input().split())
    u -= 1
    v -= 1
    ns[u+n].append((c,v))
    ns[u].append((c,n+v))
    rns[v+n].append((c,u))
    rns[v].append((c,u+n))
    odeg[u] += 1
    odeg[u+n] += 1
odeg0 = odeg[:]
# INF : 未確定 (終了しない)
# >=0 : 有限解で終了し、値は vals[u]
from heapq import heappop as hpp, heappush as hp
q = []
end = [0]*(2*n)
vals = [INF]*n + [-INF]*n
for u in range(2*n):
    if odeg[u]==0:
        q.append((0,u))
        vals[u] = 0
        end[u] = 1
while q:
    val,u = hpp(q)
    assert end[u]==1
    for val,v in rns[u]:
        if v<n:
            # 有限で終わらせたい
            end[v] = 1
            if val+vals[u]<vals[v]:
                vals[v] = val+vals[u]
                hp(q, (vals[v], v))
        else:
            # 無限にやりたい
            odeg[v] -= 1
            if odeg[v]==0:
                end[v] = 1
                tmp = -INF
                for c,u in ns[v]:
                    tmp = max(tmp, vals[u]+val)
                vals[v] = tmp
                hp(q, (vals[v], v))
                    
if end[start]!=1:
    print("INFINITY")
else:
    assert vals[start]!=-1
    print(vals[start])