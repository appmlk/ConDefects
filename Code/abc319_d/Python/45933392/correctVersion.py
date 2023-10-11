n, m = list(map(int, input().split()))
l = list(map(int, input().split()))

def check(x):
    cnt = 1
    s = l[0]
    for i in range(1, n):
        if s + l[i] + 1 > x:
            cnt += 1
            s = l[i]
        else:
            s += l[i] + 1
        if cnt > m:
            return False
    return True

min_x = max(l)-1
max_x = sum(l) + n - 1
while True:
    mid = (min_x + max_x) // 2
    if check(mid):
        max_x = mid
    else:
        min_x = mid
    if max_x - min_x <= 1:
        break
print(max_x)