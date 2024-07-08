from collections import defaultdict
H,W,E=map(int,input().split())
A=list(map(int,input().split()))

D=defaultdict(int)
for a in A:
    D[a]+=1
D=sorted(D.items(),reverse=True, key=lambda x: x[0])
#print(D)

flag=True
area=0
for a,n in D:
    N=(H//(2**a))*(W//(2**a))
    area+=(2**a)**2*n
    #print(a,n,N,area)
    if area>(2**a)**2*N:
        flag=False
if flag:
    print("Yes")
else:
    print("No")