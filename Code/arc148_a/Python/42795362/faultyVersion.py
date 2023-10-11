from math import gcd
n = int(input())
a = list(map(int,input().split()))
g = 0
for i in range (n-1):
  g = gcd(g,abs(a[i]-a[i+1]))
if g >= 2:
  ans = 1
else:
  ans = 2
print(ans)
            
	
