n = int(input())
s = [int(x) for x in input()]
s.sort()
ans = 0
for x in range(10**7):
    t = [int(c) for c in str(x * x)]
    if len(t) > n:
        break
    while len(t) < n:
        t.append(0)
    t.sort()
    if s == t:
        ans += 1
print(ans)
