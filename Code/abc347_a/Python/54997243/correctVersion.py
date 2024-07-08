N,K = map(int,input().split())
A = list(map(int,input().split()))
B = list()
for i in A:
    if i%K==0:
        B.append(i//K)
B.sort()
for i in B:
    print(i,end=" ")