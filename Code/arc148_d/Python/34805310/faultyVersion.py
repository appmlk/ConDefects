from collections import Counter
n, m = map(int, input().split())
a = list(map(int, input().split()))
s = sum(a)
for i in range(n * 2):
    a[i] = a[i] * 2 % m
a.sort()
x = 0
for i in range(0, n * 2, 2):
    if a[i] != a[i + 1]:
        print('Alice')
        break
    x += a[i]
else:
    if x == s:
        print('Bob')
    else:
        print('Alice')