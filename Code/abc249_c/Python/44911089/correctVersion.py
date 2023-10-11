from collections import defaultdict
from itertools import product

N,K=map(int,input().split())
S=[list(input()) for _ in range(N)]

ans=0
for pro in product((1,0),repeat=N):
    d=defaultdict(int)
    for i in range(N):
        if pro[i]==1:
            for j in range(len(S[i])):
                d[S[i][j]]+=1
    check=0
    for key,value in d.items():
        if value==K:
            check+=1

    ans=max(ans,check)

print(ans)