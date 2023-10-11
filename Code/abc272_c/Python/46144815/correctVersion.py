N = int(input())
A = list(map(int, input().split()))
o = []
e = []
for a in A:
    if a % 2:
        o.append(a)
    else:
        e.append(a)
if len(o) < 2 and len(e) < 2:
    print(-1)
    exit()
o.sort()
e.sort()
co = o[-1] + o[-2] if len(o) >= 2 else -1
ce = e[-1] + e[-2] if len(e) >= 2 else -1
print(max(co,ce))