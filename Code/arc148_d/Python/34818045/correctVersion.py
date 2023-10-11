from collections import Counter
N, M = map(int, input().split())
A = [int(i)%M for i in input().split()]
C = Counter([i*2 % M for i in A])
S = 0
for i in A:
    S = (S+i) % M

ans = 'Alice'
if all(i % 2 == 0 for i in C.values()):
    x = 0
    for i,j in C.items():
        x = (x + i*j//2) % M
    if x == S:
        ans = 'Bob'

print(ans)