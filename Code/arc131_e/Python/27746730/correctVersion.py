# cook your dish here
import sys
import bisect
from bisect import bisect_left as lb
from bisect import bisect_right as rb
input_=lambda: sys.stdin.readline().strip("\r\n")
from math import log
from math import gcd
from math import atan2,acos
from random import randint
from queue import Queue
sa=lambda :input_()
sb=lambda:int(input_())
sc=lambda:input_().split()
sd=lambda:list(map(int,input_().split()))
sflo=lambda:list(map(float,input_().split()))
se=lambda:float(input_())
sf=lambda:list(input_())
flsh=lambda: sys.stdout.flush()
sys.setrecursionlimit(10**6)
mod=10**9+7
mod1=998244353
gp=[]
cost=[]
dp=[]
mx=[]
ans1=[]
ans2=[]
special=[]
specnode=[]
a=0
kthpar=[]
def dfs2(root,par):
    if par!=-1:
        dp[root]=dp[par]+1
    for i in range(1,20):
        if kthpar[root][i-1]!=-1:
            kthpar[root][i]=kthpar[kthpar[root][i-1]][i-1]
    for child in gp[root]:
        if child==par:continue
        kthpar[child][0]=root
        dfs(child,root)
        
ans=0
b=[]
vis=[]
tot=0
time=[]
time1=[]
adj=[]
mx=-1
eps=0.0000001
a=[]
def check(a,b):
    if a=='G' and b=="C":
        return 0
    if a=="C" and b=="G":
        return 1
    if a=="C" and b=="P":
        return 0
    if a=="P" and b=="C":
        return 1
    if a=="P" and b=="G":
        return 0
    if a=="G" and b=="P":
        return 1
    return 2
gp=[]
vis=[]
def dfs(root):
    global gp,vis
    for i in gp[root]:
        if vis[i]==0:
            vis[i]=1
            dfs(i)
dx=[1,0,-1,0]
dy=[0,1,0,-1]
def hnbhai(tc):
    n=sb()
    edge=n*(n-1)
    edge//=2
    if edge%3!=0 or n<=5:
        print("No")
        return
    ans=""
    #multiple of 6 so 5 sum each
    if n%6==0:
        #54321
        ans="RWBBW"
    if n%6==3:
        #87654321
        ans="RWBBRWWB"
    if n%6==4:
        #987654321
        ans="RWWRBBBBB"
    while(len(ans)!=n-1):
        #654321
        ans="RWBBWR"+ans
    print("Yes")
    for i in range(n-1):
        print(ans[i]*(n-1-i))
for _ in range(1):
    hnbhai(_+1)
