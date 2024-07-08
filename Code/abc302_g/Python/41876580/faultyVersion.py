import sys
read=sys.stdin.buffer.read;readline=sys.stdin.buffer.readline;input=lambda:sys.stdin.readline().rstrip()
import bisect,string,math,time,functools,random,fractions
from bisect import*
from heapq import heappush,heappop,heapify
from collections import deque,defaultdict,Counter
from itertools import permutations,combinations,groupby
import itertools
rep=range;R=range
def I():return int(input())
def LI():return [int(i) for i in input().split()]
def SLI():return sorted([int(i) for i in input().split()])
def LI_():return [int(i)-1 for i in input().split()]
def S_():return input()
def IS():return input().split()
def LS():return [i for i in input().split()]
def NI(n):return [int(input()) for i in range(n)]
def NI_(n):return [int(input())-1 for i in range(n)]
def NLI(n):return [[int(i) for i in input().split()] for i in range(n)]
def NLI_(n):return [[int(i)-1 for i in input().split()] for i in range(n)]
def StoLI():return [ord(i)-97 for i in input()] 
def ItoS(n):return chr(n+97)
def LtoS(ls):return ''.join([chr(i+97) for i in ls])
def RLI(n=8,a=1,b=10):return [random.randint(a,b)for i in range(n)]
def RI(a=1,b=10):return random.randint(a,b)
def GI(V,E,ls=None,Directed=False,index=1):
    org_inp=[];g=[[] for i in range(V)]
    FromStdin=True if ls==None else False
    for i in range(E):
        if FromStdin:
            inp=LI()
            org_inp.append(inp)
        else:
            inp=ls[i]
        if len(inp)==2:a,b=inp;c=1
        else:a,b,c=inp
        if index==1:a-=1;b-=1
        aa=a,c,;bb=b,c,;g[a].append(bb)
        if not Directed:g[b].append(aa)
    return g,org_inp
def RE(E):
    rt=[[]for i in range(len(E))]
    for i in range(len(E)):
        for nb,d in E[i]:
            rt[nb]+=(i,d),
    return rt
def RLE(it):
    rt=[]
    for i in it:
        if rt and rt[-1][0]==i:rt[-1][1]+=1
        else:rt+=[i,1],
    return rt
def GGI(h,w,search=None,replacement_of_found='.',mp_def={'#':1,'.':0},boundary=1):
    #h,w,g,sg=GGI(h,w,search=['S','G'],replacement_of_found='.',mp_def={'#':1,'.':0},boundary=1) # sample usage
    mp=[boundary]*(w+2);found={}
    for i in R(h):
        s=input()
        for char in search:
            if char in s:
                found[char]=((i+1)*(w+2)+s.index(char)+1)
                mp_def[char]=mp_def[replacement_of_found]
        mp+=[boundary]+[mp_def[j] for j in s]+[boundary]
    mp+=[boundary]*(w+2)
    return h+2,w+2,mp,found
def TI(n):return GI(n,n-1)
def accum(ls):
    rt=[0]
    for i in ls:rt+=[rt[-1]+i]
    return rt
def bit_combination(n,base=2):
    rt=[]
    for tb in R(base**n):s=[tb//(base**bt)%base for bt in R(n)];rt+=[s]
    return rt
def gcd(x,y):
    if y==0:return x
    if x%y==0:return y
    while x%y!=0:x,y=y,x%y
    return y
def YN(x):print(['NO','YES'][x])
def Yn(x):print(['No','Yes'][x])
def show(*inp,end='\n'):
    if show_flg:print(*inp,end=end)

inf=float('inf')
FourNb=[(-1,0),(1,0),(0,1),(0,-1)];EightNb=[(-1,0),(1,0),(0,1),(0,-1),(1,1),(-1,-1),(1,-1),(-1,1)];compas=dict(zip('WENS',FourNb));cursol=dict(zip('UDRL',FourNb));HexNb=[(-1,0),(-1,-1),(0,1),(0,-1),(1,1),(1,0)]
alp=[chr(ord('a')+i)for i in range(26)]
#sys.setrecursionlimit(10**7)

def gcj(t,*a):
    print('Case #{}:'.format(t+1),*a)

def INP():
    N=80
    n=random.randint(1,N)
    x=random.randint(1,N)
    n,d=RLI(2,1,10)
    k=RI(1,n)
    return n,d,k
def Rtest(T):
    case,err=0,0
    for i in range(T):
        inp=INP()
        #show(inp)
        a1=naive(*inp)
        a2=solve(*inp)
        if a1!=a2:
            print(inp)
            n,d,k=inp
            #a,b=bin(n)[2:],bin(x)[2:]
            show(n,d,k)
            print('naive',a1)
            print('solve',a2)
            err+=1
        case+=1
    print('Tested',case,'case with',err,'errors')

def graph():
    g=[[]for i in range(n)]
    for i in range(m):
        u,v=LI()
        g[u]+=v,
        g[v]+=u,
    
mo=998244353
#mo=10**9+7

show_flg=False
show_flg=True


ans=0

n=I()
a=LI_()

n=8
a=RLI(n,0,3)

b=sorted(a)
d=defaultdict(int)
for i in range(n):
    if a[i]==b[i]:
        continue
    d[(a[i],b[i])]+=1

p=[]
for i in range(4):
    for j in range(i+1,4):
        p+=(i,j,j,i),

q=[]
for i in range(4):
    for j in range(4):
        if i==j:
            continue
        for k in range(4):
            for l in range(4):
                if k==l:
                    continue
                if k!=j and i!=l:
                    continue
                if (i,j)==(k,l):
                    continue
                if (i,j)==(l,k):
                    continue
                q+=(i,j,k,l),
for _ in range(n):
    f=False
    for i,j,k,l in p:
        x,y=d[(i,j)],d[(k,l)]
        if x==0 or y==0:
            continue
        f=True
        d[(i,j)]-=1
        d[(k,l)]-=1
        ans+=1
        break
    if f:
        continue
    
    for i,j,k,l in q:
        x,y=d[(i,j)],d[(k,l)]
        if x==0 or y==0:
            continue
        f=True
        d[(i,j)]-=1
        d[(k,l)]-=1
        if i==l:
            d[(k,j)]+=1
        elif k==j:
            d[(i,l)]+=1
        ans+=1
        break

print(ans)