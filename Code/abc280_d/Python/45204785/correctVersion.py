import math
k=int(input())
for i in range(2,5*10**6):
    k//=math.gcd(k,i)
    if k==1:
        print(i)
        exit()
print(k)