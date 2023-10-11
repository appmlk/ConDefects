n, m = map(int, input().split())

p = []
c = []
f = []

for i in range(n):
    l = list(map(int, input().split()))
    p.append(l[0])
    c.append(l[1])
    f.append(set(l[2:]))

for i in range(n):
    for j in range(n):
        if p[i] >= p[j]:
            if f[j].issuperset(f[i]) and (p[i] > p[j] or len(f[j]) > len(f[i])):
                print("Yes")
                exit(0)


print("No")