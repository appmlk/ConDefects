N, K = map(int,input().split())
A=list(map(int,input().split()))
Q=int(input())
S = [[0] for i in range(K)]
for i in range(N):
    S[i%K].append(S[i%K][-1]+A[i])
    
for i in range(Q):
    l, r = map(lambda x:int(x)-1,input().split())
    s=set()
    for k in range(K):
        # s.add(S[k][(r-k)//K+1] - S[k][(l-k-1)//K+1])
        # print(S[k][(r-k)//K+1],S[k][(l-k-1)//K+1])
        s.add(S[k][(r-k)//K+1] - S[k][(l-k-1)//K+1])
        # print(S[k][(r-k)//K+1],S[k][(l-k-1)//K+1])
    if len(s)==1:
        print('Yes')
    else:
        print('No')