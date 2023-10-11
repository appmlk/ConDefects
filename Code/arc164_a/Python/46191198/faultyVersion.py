t = int(input())
for i in range(t):
    n, k = map(int, input().split())
    l = 0
    for i in range(12):
        l += (n // 3**i) % 3
    if k >= l and (k - l) % 2 == 0:
        print("Yes")
    else:
        print("No")