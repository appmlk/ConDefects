import sys
ipt = sys.stdin.readline

N,Q = map(int,ipt().split())
A = list(map(int,ipt().split()))
query = []
for i in range(Q):
    q = list(map(int,ipt().split()))+[i]
    query.append(q)
query.sort(key=lambda x:x[1])

ans = [None]*Q

B = [0]*60
S = [0]*60
D = []

def GEM(X,idx,L=None):
    """
    Gaussian Elimination Method
    B: basis space
    X: # to reduce
    L: dim of the basis space (len(B) if not mentioned)
    """
    if L is None:
        L = len(B)
    M = -1
    T = 0
    for i in range(L-1,-1,-1):
        if X&(1<<i):
            if B[i]:
                X ^= B[i]
                T ^= S[i]
            elif M == -1:
                M = i
    if M == -1:
        #従属
        mi = 10**7
        for i in range(L):
            if T&(1<<i):
                mi = min(mi,D[i])
        out = D.index(mi)
        D[out] = idx
        for i in range(L):
            if S[i]&(1<<out):
                S[i] ^= T
                S[i] |= 1<<out
    else:
        #独立
        B[M] = X
        S[M] = T+(1<<len(D))
        D.append(idx)
        for i in range(L):
            if i == M:
                continue
            if B[i]&(1<<M):
                B[i] ^= X
                S[i] ^= S[M]
    return X

def GEM2(X,l,a,L=None):
    """
    Gaussian Elimination Method
    B: basis space
    X: # to reduce
    L: dim of the basis space (len(B) if not mentioned)
    """
    if L is None:
        L = len(B)
    M = -1
    T = 0
    for i in range(L-1,-1,-1):
        if X&(1<<i):
            if B[i]:
                X ^= B[i]
                T ^= S[i]
            elif M == -1:
                M = i
    if M == -1:
        #従属
        mi = 10**7
        for i in range(L):
            if T&(1<<i):
                mi = min(mi,D[i])
        if mi >= l:
            ans[a] = "Yes"
        else:
            ans[a] = "No"
    else:
        ans[a] = "No"

last = -1
for l,r,x,a in query:
    l,r = l-1,r-1
    while last < r:
        last += 1
        GEM(A[last],last)
    GEM2(x,l,a)

for a in ans:
    print(a)