import math

T = int(input())
for _ in range(T):
    A, B = map(int, input().split())

    def f(k):
        return (k + 1) * max(((B + k - 1) // k), A) - (A + B)

    bo = math.floor((B - 1) ** 0.5)
    if B == 1:
        print(f(1))
        continue
    ans = 10 ** 18
    for k in range(1, bo + 1):
        ans = min(ans, f(k))
    for t in range(bo + 1):
        k = (B - 1) // (t + 1) + 1
        ans = min(ans, f(k))
    print(ans)
