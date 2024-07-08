import sys, math, string
from collections import Counter
from bisect import bisect_right as upper_bound, bisect_left as lower_bound

input = lambda: sys.stdin.readline().rstrip()
read = lambda: int(input())
reads = lambda: map(int, input().split())
readlist = lambda n=None: (
    list(map(int, input().split()))
    if n == None
    else [list(map(int, input().split())) for _ in range(n)]
)
readstr = lambda n=None: (
    list(input()) if n == None else [list(input()) for _ in range(n)]
)

if __name__ == "__main__":
    N, T = reads()
    S = input()
    A = readlist()

    a, b = [], []
    for i, x in enumerate(A):
        if S[i] == "1":
            a.append(x)
        else:
            b.append(x)

    ans = 0
    for i in a:
        ans += upper_bound(b, i + 2 * T) - lower_bound(b, i)

    print(ans)

    pass
