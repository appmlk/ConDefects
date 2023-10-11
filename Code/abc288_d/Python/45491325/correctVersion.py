
N, K = map(int, input().split())
A = list(map(int, input().split()))
Q = int(input())
queries = []
for i in range(Q):
    l, r = map(lambda x:int(x)-1, input().split())
    queries.append([l, r])

S = [[0] for i in range(K)]
for k in range(K):
    for i in range(N):
        if ((i*K+k)<N):
            new = S[k][-1] + (A[i*K+k])
            S[k].append(new)

for i in range(Q):
    now = set()
    l, r = queries[i]
    for k in range(K):
        m = (r-k)%K
        lk = l//K
        if m < l%K:
            lk += 1
        res = S[m][(r-k)//K+1] - S[m][lk]
        now.add(res)
        # print(now, res, (r-k)//K+1, lk, m)
        
    if len(now)==1:
        print('Yes')
    else:
        print('No')
    