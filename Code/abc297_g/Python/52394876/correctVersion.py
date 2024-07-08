n,l,r = map(int, input().split())
alist = list(map(int, input().split()))

s = l+r
xor = 0
lose = 0

for a in alist:
    a %= s
    a //= l
    xor ^= a

if xor == 0:
    print("Second")
else:
    print("First")