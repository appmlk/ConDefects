def calc(a, b):
    return a * a * a + a * a * b + a * b * b + b * b * b

N = int(input())
ans = 10 ** 20
for a in range(10 ** 6):
    lb = a
    ub = 10 ** 6
    while ub - lb > 1:
        mid = (ub + lb) // 2
        if calc(a, mid) >= N:
            ub = mid
        else:
            lb = mid
    ans = min(ans, calc(a, ub))

print(ans)
