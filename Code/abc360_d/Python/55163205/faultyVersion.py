import bisect

n,t = map(int, input().split())
s = input()
x = list(map(int, input().split()))
x.sort()
r = []
l = []

for i in range(n):
    if s[i] == "1":
        r.append(x[i])
    else:
        l.append(x[i])

s = 0
for i in r:
    s += bisect.bisect_right(l, i+t*2) - bisect.bisect_right(l, i)
print(s)