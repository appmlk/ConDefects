x,y,n = map(int,input().split())

if x*3 > y:
  print(n//3 * y + n%3 * x)
else:
  print(x*n)