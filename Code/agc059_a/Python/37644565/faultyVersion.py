n, q = map(int, input().split())
s = input()
pre = [0] * n
for i in range(1, n):
    pre[i] = pre[i-1] + (s[i] != s[i-1])
for i in range(q):
    l, r = map(int, input().split())
    l -= 1
    r -= 1
    ans = pre[r] - pre[l]
    ans += s[r] != s[l]
    print(ans)
