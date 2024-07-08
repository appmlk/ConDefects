def extended_euclid(a, b):
    c, d, e, f = 1, 0, 0, 1
    while b != 0:
        c, d = d, c - a // b * d
        e, f = f, e - a // b * f
        a, b = b, a % b
    return (c, e)
        
def gcd(a,b):
  if a%b==0:
    return b
  else:
    return gcd(b,a%b)
  
X,Y=list(map(int,input().split()))
if X==0:
  if abs(Y)==1 or abs(Y)==2:
    print(2//abs(Y),0)
  else:
    print(-1)
elif Y==0:
  if abs(X)==1 or abs(X)==2:
    print(0,2//abs(X))
  else:
    print(-1)
elif gcd(abs(X),abs(Y))>2:
  print(-1)
else:
  a,b=extended_euclid(X,-Y)
  print(2*b,2*a)