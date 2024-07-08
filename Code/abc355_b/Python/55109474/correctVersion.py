n, m = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
c = []
for aa in a:
    c.append((aa, 1))
for bb in b:
    c.append((bb, 2))

c.sort()
for i in range(n + m - 1):
    if c[i][1] == 1 and c[i+1][1] == 1:
        print("Yes")
        exit()
print("No")