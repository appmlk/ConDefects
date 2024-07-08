s, a, b, m = input(), 0, 1, 998244353
for c in input():
    a = (a * 10 + b * (ord(c) - ord('0'))) % m
    b += a
    print(a)
  