import sys
readline = sys.stdin.readline


def calc(N, M, K):
    if K > N:
        return pow(2, N, 10)
    if K == N:
        if M == K+1:
            return 0
        else:
            return pow(2, N, 10)
    if K > 0:
        a = calc(N-K, M-K, 0)
        return a*pow(2, K, 10)%10
    # K == 0
    if N < M:
        return pow(2, N, 10)
    if N == M:
        if N == 1:
            return 0
        return 1
    # N > M
    return calc(M-(-N%M), M, 0)
    


T = int(readline())

ans = [None]*T
for qu in range(T):
    N, M, K = map(int, readline().split())
    ans[qu] = calc(N, M, K)


print('\n'.join(map(str, ans)))