from collections import defaultdict

N, M = map(int, input().split())
A = list(map(int, input().split()))

count = defaultdict(int)
for a in A:
    count[a] += 1

S = set()
for a, n in count.items():
    if n%2:
        S.add(a)

if len(S) == 0:
    print('Bob')
    quit()

if M % 2:
    print('Alice')
    quit()

m = M // 2
large = set()
small = []
for a in S:
    if a < m:
        small.append(a)
    else:
        large.add(a)


if len(small) % 2:
    print('Alice')
    quit()

for a in small:
    if a+m in large:
        continue
    print('Alice')
    quit()

print('Bob')
