n, m, p = map(int, input().split())
ans = 0
day = m
for i in range(3*(10**5)):
    if day > n:
        ans = i
        break
    day += p
print(ans)