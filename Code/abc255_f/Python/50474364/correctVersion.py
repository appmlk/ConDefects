import pypyjit
pypyjit.set_param('max_unroll_recursion=-1')
import sys
sys.setrecursionlimit(10**8)
N=int(input())
A=list(map(int,input().split()))
B=list(map(int,input().split()))
if A[0]!=1:
  print(-1)
  exit()
result=[[0]*2 for i in range(N+1)]
place=[0]*(N+1)
for i in range(N):
  place[B[i]]=i
def dfs(l1,r1,l2,r2):
  if l1>r1:
    return
  if l1==r1:
    if A[l1]!=B[l2]:
      print(-1)
      exit()
    return
  pos=place[A[l1]]
  if pos<l2 or pos>r2:
    print(-1)
    exit()
  count=pos-l2
  if count>0:
    result[A[l1]][0]=A[l1+1]
    dfs(l1+1,l1+count,l2,l2+count-1)
  if l1+count+1<=r1:
    result[A[l1]][1]=A[l1+count+1]
    dfs(l1+count+1,r1,l2+count+1,r2)
dfs(0,N-1,0,N-1)
k=0
for B in result:
  k+=1
  if k==1:
    continue
  print(B[0],B[1])
  