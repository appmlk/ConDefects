from itertools import accumulate
N, Q = map(int, input().split())
S = input()

A = [0]*N

for i in range(N-1):
    if S[i] != S[i+1]:
        A[i+1] = 1

B = list(accumulate(A))



for _ in range(Q):
    l, r = map(int, input().split())
    l -= 1
    r -= 1
    x = B[r]-B[l]
    if S[l] != S[r]:
        x += 1
    print(-(-x//2))