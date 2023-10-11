#入力
n, m, p = map(int, input().split())

ans = 0

while m <= n:
    ans += 1
    m += p

print(ans)