import sys

read = sys.stdin.read
readline = sys.stdin.readline
readlines = sys.stdin.readlines


N, M, *L = map(int, read().split())

left = max(L) - 1
right = 10 ** 18
while right - left > 1:
    mid = (left + right) // 2
    cnt = 1
    idx = 0
    for i, l in enumerate(L):
        if idx + l <= mid:
            idx += l
        else:
            cnt += 1
            idx = l
        idx += 1
        if i != N - 1 and idx > mid:
            cnt += 1
            idx = 0
    if cnt <= M:
        right = mid
    else:
        left = mid

print(right)
