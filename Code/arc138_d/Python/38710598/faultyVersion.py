import sys

# sys.setrecursionlimit(200005)
int1 = lambda x: int(x)-1
pDB = lambda *x: print(*x, end="\n", file=sys.stderr)
p2D = lambda x: print(*x, sep="\n", end="\n\n", file=sys.stderr)
def II(): return int(sys.stdin.readline())
def LI(): return list(map(int, sys.stdin.readline().split()))
def LLI(rows_number): return [LI() for _ in range(rows_number)]
def LI1(): return list(map(int1, sys.stdin.readline().split()))
def LLI1(rows_number): return [LI1() for _ in range(rows_number)]
def SI(): return sys.stdin.readline().rstrip()

# dij = [(0, 1), (-1, 0), (0, -1), (1, 0)]
dij = [(0, 1), (-1, 0), (0, -1), (1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1)]
inf = (1 << 63)-1
# inf = (1 << 31)-1
# md = 10**9+7
md = 998244353

from itertools import combinations

n,k=LI()

base=[]
aa={}
s=1
for ii in combinations(range(n),k):
    a0=a=sum(1<<i for i in ii)
    for b in base:a=min(a,b^a)
    if a:
        base.append(a)
        aa[s]=a
        s<<=1
    if len(base)==n:break

if len(base)!=n:
    print("No")
    exit()

ans=[0]*(1<<n)
for i in range((1<<n)-1):
    s=i^i>>1^i+1^i+1>>1
    ans[i+1]=ans[i]^aa[s]

print("Yes")
print(*ans)
