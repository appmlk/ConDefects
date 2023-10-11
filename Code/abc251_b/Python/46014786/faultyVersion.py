import itertools
N,W = list(map(int,input().split()))
A = list(map(int,input().split()))
ans = set()
for n in range(1,4):
    for ss in itertools.combinations(A,n):
        m = sum(ss)
        if(m < W):
            ans.add(m)
print(len(ans))