from collections import Counter
M = 998244353
n, k = map(int, input().split())
a = sorted(map(int, input().split()))
res = 1
j = n-1
fact = [1] * (n+1)
for i in range(1, n+1):
    fact[i] = fact[i-1] * i % M


for i, x in enumerate(a):
    if x >= (k+1) // 2:
        break
    while a[j] + x >= k:
        res = res * max(0, n-j-i) % M
        j -= 1
    res = res * max(0, n-j-i) % M
while j >= i:
    res = res * max(0, n-j-i) % M
    j -= 1
for v in Counter(a).values():
    res = res * pow(fact[v], M-2, M) % M
print(res)
