N, L, R = map(int, input().split())
n = format(N, 'b')
ans = 0
l = len(n)
x = 2 ** l
for i in range(len(n)):
    x = x // 2
    if n[i] == '1':
        ans += min(R, 2 * x - 1) - max(L, x) + 1
print(ans)