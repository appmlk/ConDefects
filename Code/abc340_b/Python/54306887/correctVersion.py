Q = int(input())
A = []

for i in range(Q):
  c, d = map(int, input().split())
  
  if c == 1:
    A.append(d)
    
  else:
    print(A[-d])