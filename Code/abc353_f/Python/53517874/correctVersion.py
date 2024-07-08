import sys

# sys.setrecursionlimit(1000005)
# sys.set_int_max_str_digits(200005)
int1 = lambda x: int(x)-1
pDB = lambda *x: print(*x, end="\n", file=sys.stderr)
p2D = lambda x: print(*x, sep="\n", end="\n\n", file=sys.stderr)
def II(): return int(sys.stdin.readline())
def LI(): return list(map(int, sys.stdin.readline().split()))
def LLI(rows_number): return [LI() for _ in range(rows_number)]
def LI1(): return list(map(int1, sys.stdin.readline().split()))
def LLI1(rows_number): return [LI1() for _ in range(rows_number)]
def SI(): return sys.stdin.readline().rstrip()

dij = [(0, 1), (-1, 0), (0, -1), (1, 0)]
# dij = [(0, 1), (-1, 0), (0, -1), (1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1)]
# inf = -1-(-1 << 31)
inf = -1-(-1 << 62)

# md = 10**9+7
md = 998244353

def tobig(x,y):
    i,j=x//k,y//k
    x%=k
    y%=k
    if i&1==j&1:
        res=[]
        res.append((i-1,j,x+1))
        res.append((i, j-1, y+1))
        res.append((i+1,j,k-x))
        res.append((i,j+1,k-y))
        return res
    return [(i,j,0)]

k=II()
sx,sy=LI()
tx,ty=LI()
ss=tobig(sx,sy)
tt=tobig(tx,ty)
ans=abs(sx-tx)+abs(sy-ty)
for a,b,d in ss:
    for i,j,e in tt:
        x,y=abs(a-i),abs(b-j)
        if x>y:x,y=y,x
        ans=min(ans,y*2+d+e)
        ans=min(ans,x*2+(y-x)//2*(k+1)+d+e)
print(ans)
