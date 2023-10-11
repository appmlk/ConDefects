import bisect

N=int(input())
x=list(map(int,input().split()))
x.sort()
b=bisect.bisect(x,0)
M=x[:b]
P=x[b:]

C=[]
for i in range(len(M)):
    if i<3 or len(M)-3<=i:
        C.append(M[i])
for i in range(len(P)):
    if i<3 or len(P)-3<=i:
        C.append(P[i])

D=[]
for i in range(len(C)-2):
    for j in range(i+1,len(C)-1):
        for k in range(j+1,len(C)):
            D.append((C[i]+C[j]+C[k])/(C[i]*C[j]*C[k]))
D.sort()
print(D[0])
print(D[-1])