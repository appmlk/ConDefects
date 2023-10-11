n, m = map(int, input().split())
L = list(map(int, input().split()))
left, right = max(L), sum(L)
while left < right:
    mid = (left + right) // 2
    cur = 0
    level = 0
    # print("lr", left, right, mid)
    flag = False
    for i in range(n):
        # print("lc", level, cur, L[i])
        if L[i] > mid:
            left = mid + 1
            flag = True
            break
        if cur + L[i] > mid:
            level += 1
            cur = 0
        cur += L[i] + 1
        if level >= m:
            left = mid + 1
            flag = True
            break

    if not flag:
        right = mid
print(right)
