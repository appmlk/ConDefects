s, a, b, m = input(), 0, 1, 998244353
for c in input()[::-1]:
    a += b * (ord(c) - ord('0'))
    b = (b * 10 + a) % m
print(a)