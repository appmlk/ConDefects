import bisect
from itertools import accumulate, permutations, combinations
from collections import deque, Counter, defaultdict
import heapq
from string import ascii_lowercase  # 'abcdefghijklmnopqrstuvwxyz'
from string import ascii_uppercase  # 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
# import pypyjit
# pypyjit.set_param('max_unroll_recursion=-1')
import sys

sys.setrecursionlimit(10 ** 6)

# ========================================================================
INF = pow(10, 16)

input = lambda: sys.stdin.readline().rstrip()

NI = lambda: int(input())  # N = int(input())
NMI = lambda: map(int, input().split())  # A, B, C = map(int, input().split())
NLI = lambda: list(map(int, input().split()))  # A = list(map(int, input().split()))

SI = lambda: input()
SMI = lambda: input().split()
SLI = lambda: list(input().split())

EI = lambda _n: [list(map(int, input().split())) for _ in range(_n)]
TI = lambda _n: list(zip(*[map(int, input().split()) for _ in range(_n)]))
# ========================================================================

MOD = 10**8

if __name__ == '__main__':
    N = NI()
    A = NLI()

    A.sort()

    count = 0
    SA = [0] + list(accumulate(A)) # 0, A1, A1+A2,
    for i in range(N-1):
        left_index = bisect.bisect_left(range(i+1, N), 10**8, key=lambda x:A[i] + A[x])
        count += SA[left_index+i+1] + left_index*A[i] - SA[i+1] # MODしない項
        count += (A[i]*(N-left_index-1-i) + SA[N]- SA[left_index+1+i]) - MOD*(N-left_index-1-i) # MODする項
    print(count)