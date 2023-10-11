T = int(input())
for _ in range(T):
    N, K = map(int, input().split())
    while N > 0:
        K -= N % 3
        N //= 3
    if K >=0:
        print("Yes")
    else:
        print("No")
