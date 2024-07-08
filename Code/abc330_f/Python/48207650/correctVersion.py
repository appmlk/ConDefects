import heapq
N,K = map(int, input().split())
X=[]
Y=[]
for _ in range(N):
  x,y = map(int, input().split())
  X.append(x)
  Y.append(y)
X.sort()
Y.sort()

def solve(A,d):
  h=A[:]
  heapq.heapify(h)
  for i in range(N):
    l=heapq.heappushpop(h, A[i]-d)
  ans=0
  for a in A:
    if a<l:
      ans+=l-a
    elif a>l+d:
      ans+=a-(l+d)
  return ans
  
#判定用関数
def judge(d):
  if solve(X,d)+solve(Y,d)<=K:
    return False
  else:
    return True
  
#二分探索
def binary(l,r):
  while r-l>1:
    mid=(l+r)//2
    #print(judge(mid))
    if judge(mid):
      l=mid
    else:
      r=mid
  return l
#print(judge(0))
print(binary(-1,max(max(Y)-min(Y)+2,max(X)-min(X)+2))+1)