from itertools import permutations

N, H, W = map(int, input().split())
A, B = [None]*N, [None]*N
for i in range(N):
    A[i], B[i] = map(int, input().split())

def check(p, s):
    cnt = 0
    S = [[False for _ in range(W)] for _ in range(H)]
    for i in range(H):
        for j in range(W):
            if S[i][j] == False:
                if cnt == N:
                    return False
                if s & 1<<cnt:
                    a, b = A[p[cnt]], B[p[cnt]]
                else:
                    a, b = B[p[cnt]], A[p[cnt]]
                if 0 <= i+a <= H and 0 <= j+b <= W:
                    cnt += 1
                    for di in range(a):
                        for dj in range(b):
                            S[i+di][j+dj] = True
                else:
                    return False
    return True

for v in permutations(range(N)):
    for k in range(1<<N):
        if check(v, k):
            print('Yes')
            quit()
print('No')