n = int(input())
xy = []
for i in range(n):
    i, j = map(int, input().split())
    xy.append((i, j))
s = input()
from collections import defaultdict
r_ok = defaultdict(bool)
l_ok = defaultdict(bool)

for i in range(n):
    x, y = xy[i]

    if s[i] == "R":
        if not r_ok[y]:
            r_ok[y] = x
        else:
            r_ok[y] = min(r_ok[y], x)
    else:
        if not l_ok[y]:
            l_ok[y] = x
        else:
            l_ok[y] = max(l_ok[y], x)

for x, y in xy:
    if y not in r_ok or y not in l_ok:
        continue
    if r_ok[y] < l_ok[y]:
        print("Yes")
        exit()
print("No")