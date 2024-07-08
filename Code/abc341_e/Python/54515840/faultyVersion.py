from atcoder.segtree import SegTree
def op(x,y):
  return max(x,y)
N,Q = map(int,input().split())
S = input()
check = SegTree(op,-float("inf"),[0 for i in range(N-1)])
for i in range(N-1):
  if S[i]==S[i+1]:
    check.set(i,1)
for i in range(Q):
  query = list(map(int,input().split()))
  q = query.pop(0)
  if q == 1:
    if query[0]!=1:
      hoge = check.get(query[0]-2)
      check.set(query[0]-2,(hoge+1)%2)
    if query[1]!=N:
      hoge = check.get(query[1]-1)
      check.set(query[1]-1,(hoge+1)%2)
  else:
    if N == 1:
      print("Yes")
    else:
      
      hoge = check.prod(query[0]-1,query[1]-1)
      if hoge == 0:
        print("Yes")
      else:
        print("No")
    
