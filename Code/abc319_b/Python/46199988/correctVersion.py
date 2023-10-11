n = int(input())

ans = ""
for i in range(n + 1):
    judge = False
    for j in range(1,10):
        if n % j == 0:
            if i % (n / j) == 0:
                judge = True
                ans += str(j)
                break
    if judge:
        pass
    else:
        ans += "-"

print(ans)