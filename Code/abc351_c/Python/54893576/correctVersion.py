N=int(input())
A=list(map(int,input().split()))
Ada=[]
for i in range(N):
    Ada.append(A[i])
    while True:
        if len(Ada)==1:
            break
        if Ada[-1]!=Ada[-2]:
            break
        else:
            d1=Ada.pop()
            d2=Ada.pop()
            Ada.append(d1+1)
print(len(Ada))