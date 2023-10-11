from itertools import permutations
N, M = map(int, input().split())
S = [input() for _ in range(N)]
T = {input() for _ in range(M)}
stack = []
def rec(i, rest):
    if i == N - 1:
        for p in permutations(list(range(N)), N):
            res = []
            for j in range(N):
                res.append(S[p[j]])
                if j < N - 1: res.append('_' * stack[j])
            ans = ''.join(res)
            if ans not in T:
                print(ans)
                exit()
        return
    for j in range(1, rest - (N - 1 - i) + 2):
        stack.append(j)
        rec(i + 1, rest - j)
        stack.pop()

rec(0, 16 - sum(len(s) for s in S))
print(-1)