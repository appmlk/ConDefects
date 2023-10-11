A,X,M = map(int,input().split())
if A == 1:
    print(X%M)
else:
    print(pow(A,X,M*(A-1))//(A-1)%M)