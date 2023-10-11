from math import gcd

k = int(input())
for i in range(2, 2000001):
    d = gcd(k, i)
    if d > 1:
        k //= d
    if k == 1:
        print(i)
        exit()
print(k)