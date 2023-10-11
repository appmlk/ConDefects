import math
n,a,b = map(int,input().split())
total = (n*(n+1))//2
rma = n//a
rmb = n//b
plab= n//((a*b)//math.gcd(a,b))
total-=a*(rma*(rma+1))//2
total-=b*(rmb*(rmb+1))//2
total+=((a*b)//math.gcd(a,b))*(plab*(plab+1))//2
print(total)