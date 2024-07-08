n = int(input())
s = list(input())

for i in range(n):
    if i % 2 == 0:
        if s[i] == 'A':
            s[i] = 'B'
        else:
            s[i] = 'A'
print(s)
L = []
cur = s[0]
cnt = 0
for si in s:
    if si == cur:
        cnt += 1
    else:
        cur = si
        L.append(cnt)
        cnt = 1
L.append(cnt)
print(L)

ans = 1
for l in L:
    a = (l + 1) // 2
    ans *= a
    ans %= (10 ** 9) + 7

print(ans)
