import sys
readline = sys.stdin.buffer.readline


N, K = map(int, readline().split())
A = list(map(int, readline().split()))
if K >= 0:
    print('Yes')
    A.sort()
    print(*A)
else:
    if K > sum(A):
        print('No')
    else:
        print('Yes')
        A.sort(reverse=True)
        print(*A)
