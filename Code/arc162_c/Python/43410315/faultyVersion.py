from collections import deque

T = int(input())
for _ in range(T):
    N,K = map(int, input().split())
    P = [int(p) for p in input().split()]
    A = [int(a) for a in input().split()]
    E = [[] for _ in range(N)]
    for i in range(N-1):
        E[P[i]-1].append(i+1)

    ans = "Bob"
    for i in range(N):
        cnt = 0
        memo = [0]*(K+1)
        q = deque([i])
        if -1 < A[i] <= K:
            memo[A[i]] = 1
        elif A[i] == -1:
            cnt += 1
        while q:
            u = q.pop()
            for v in E[u]:
                if -1 < A[v] <= K:
                    memo[A[v]] += 1
                elif A[v] == -1:
                    cnt += 1
                q.append(v)

        if memo[K] == 1:
            continue
        if cnt < 2 and sum(memo) == K:
            ans = "Alice"
        elif cnt == 1 and sum(memo) == K-1:
            ans = "Alice"
    print(ans)