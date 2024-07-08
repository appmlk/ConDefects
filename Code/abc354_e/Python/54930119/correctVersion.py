from functools import lru_cache
N = int(input())
A, B = [None]*N, [None]*N
for i in range(N):
    A[i], B[i] = map(int, input().split())

@lru_cache(maxsize = None)
def DFS(M, turn):
    res = False
    for i in range(N-1):
        for j in range(i+1, N):
            if M & 1<<i and M & 1<<j:
                if A[i] == A[j] or B[i] == B[j]:
                    res = res or turn == DFS(M ^ 1<<i ^ 1<<j, not turn)
    if res:
        return turn
    else:
        return not turn
print('Takahashi' if DFS((1<<N) - 1, True) else 'Aoki')