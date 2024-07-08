N = int(input())
Q,A,B = list(map(int,input().split())),list(map(int,input().split())),list(map(int,input().split()))
ans = 0
Acnt,Bcnt = 0,0

while all(x >= 0 for x in Q):
  Q = [x - y for x, y in zip(Q, A)]
  Acnt += 1
Q = [x + y for x, y in zip(Q, A)]
Acnt -=1
ans = max(ans,Acnt)
print("A :",Acnt)

while Acnt>=0:
  while all(x >= 0 for x in Q):
    Q = [x - y for x, y in zip(Q, B)]
    Bcnt += 1
  Q = [x + y for x, y in zip(Q, B)]
  Bcnt -= 1
  ans = max(ans,Acnt+Bcnt)
  Acnt -=1
  Q = [x + y for x, y in zip(Q, A)]
print(ans)
    
    
  
  

  
  