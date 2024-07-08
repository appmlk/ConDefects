from collections import defaultdict

n, t = map(int, input().split())

tate = defaultdict(set)
yoko = defaultdict(set)
naname = defaultdict(set)
for i in range(n):
    for j in range(n):
        tmp = n * i + j
        tate[i].add(tmp)
        yoko[j].add(tmp)
        if i == j:
            naname[0].add(tmp)
        if i + j == n - 1:
            naname[1].add(tmp)

A = list(map(int, input().split()))

cnt = 0
for a in A:
    a -= 1

    i = a // n
    j = a % n
    tate[i].discard(a)
    yoko[j].discard(a)
    if i == j:
        naname[0].discard(a)
    if i + j == n - 1:
        naname[1].discard(tmp)
    cnt += 1
    if (
        len(tate[i]) == 0
        or len(yoko[j]) == 0
        or len(naname[0]) == 0
        or len(naname[1]) == 0
    ):
        exit(print(cnt))

print(-1)
