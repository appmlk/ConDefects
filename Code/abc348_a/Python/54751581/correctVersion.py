n = int(input())

ans = ""
for i in range(1, n+1):
    ans += 'x' if i % 3 == 0 else 'o'
print(ans)