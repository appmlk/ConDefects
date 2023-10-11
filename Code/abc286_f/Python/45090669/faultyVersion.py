def extgcd(a, b):
    if b:
        d, y, x = extgcd(b, a % b)
        y -= (a // b) * x
        return d, x, y
    return a, 1, 0

def remainder(V):
    x = 0; d = 1
    for X, Y in V:
        g, a, b = extgcd(d, Y)
        x, d = (Y*b*x + d*a*X) // g, d*(Y // g)
        x %= d
    return x, d

lis = [4, 9, 5, 7, 11, 13, 17, 19, 23]
now = 0
a = []
check = []
for num in lis:
    now+=1
    check.append(now)
    a.append(now+num-1)
    for i in range(num-1):
        a.append(now)
        now+=1

print(len(a))
print(*a)

b = list(map(int, input().split()))

V = []
for i in range(len(check)):
    amari = b.index(check[i]) - check[i] + 1
    m = check[i]
    V.append((amari,m))

x,d = remainder(V)

print(x)