import math
K = int(input())

for i in range(2,(10**6)*5):
    tmp=math.gcd(K,i)

    if tmp!=1:
        K//=tmp
        if K==1:
            print(i)
            exit()
print(K)
