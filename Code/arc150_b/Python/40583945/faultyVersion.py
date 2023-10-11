from math import ceil

for _ in range(int(input())):
    a, b = map(int, input().split())


    max_k = ceil((b-1)/a)

    ans = 10**18
    for k in range(1, max_k + 1):
        x = max(ceil(b / k) - a, 0)
        y = k * (a + x) - b
        if x+y < ans:
            ans = x+y

    print(ans)