Q=int(input())
A=[list(map(int,input().split())) for i in range(Q)]
B=[]

for i in range(Q):
    if A[i][0] == 1:
        B.append(A[i][0])
    else:
        print(B[-A[i][1]])
