def rerooting():    
  # dfs1 bottom-up
  dp1=[E]*n
  for v in order[::-1]:
    dp1[v]=g(dp1[v],v)
    p=par[v]
    if p!=-1:
      dp1[p]=merge(dp1[p],f(dp1[v],p,v))
  
  # dfs2 top-down
  dp2=[E]*n
  ans=[E]*n
  for v in order:
    s=len(edge[v])
    cumR=[E]*(s+1)
    cumR[s]=E
    for i in range(s,0,-1):
      u=edge[v][i-1]
      if u==par[v]:
        cumR[i-1]=merge(cumR[i],f(dp2[v],v,u))
      else:
        cumR[i-1]=merge(cumR[i],f(dp1[u],v,u))
      
    cumL=E
    for i in range(s):
      u=edge[v][i]
      if u!=par[v]:
        val=merge(cumL,cumR[i+1])
        dp2[u]=g(val,v)
        cumL=merge(cumL,f(dp1[u],v,u))
      else:
        cumL=merge(cumL,f(dp2[v],v,u))
    
    ans[v]=calc_ans(cumL,v)

  return ans



E=(0,0,0,0) # cnt,depth,cost1,cost2

def f(res,v,v_child):
  cnt,depth,cost1,cost2=res
  return (cnt,depth,cost1,-(1+cost2)+cost1)

def g(res,v):
  cnt,depth,sum_cost1,diff=res
  cnt+=1
  depth+=1
  cost1=min(sum_cost1+2,2*cnt-depth)
  cost2=sum_cost1-diff
  return (cnt,depth,cost1,cost2)
 
def merge(a,b):
  return (a[0]+b[0],max(a[1],b[1]),a[2]+b[2],max(a[3],b[3]))


def calc_ans(res,v):
  return res
 
 
from sys import stdin
input=lambda :stdin.readline()[:-1]
 
n=int(input())
edge=[[] for i in range(n)]
for i in range(n-1):
  x,y=map(lambda x:int(x)-1,input().split())
  edge[x].append(y)
  edge[y].append(x)



# make order table
# root = 0

order=[]
par=[-1]*n
todo=[0]
while todo:
  v=todo.pop()
  order.append(v)
  for u in edge[v]:
    if u!=par[v]:
      par[u]=v
      todo.append(u)

ans=rerooting()
ANS=1<<30
for v in range(n):
  ANS=min(ANS,ans[i][2]-ans[i][3])
print(ANS)