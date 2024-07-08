t = int(input())
for _ in range(t):
    A = list(map(int, input().split()))
    _, _, _, p4, p5 = map(int, input().split())

    target = 2 * A[0] + A[1] - A[3] - 2 * A[4]
    if target <= 0:
        ans = 0
    else:
        ans = min(target // 2 * p5, target * p4)
        if target % 2 != 0:
            ans += min(p5, p4)
    print(ans)
