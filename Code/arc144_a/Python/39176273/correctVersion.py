N=int(input())

M=2*N
print(M)

tmp=M
X=[]
tmp1=M//8
tmp2=M%8
for i in range(tmp1):
    X.append(8)
tmp3=tmp2//6
tmp4=tmp2%6
for i in range(tmp3):
    X.append(6)
tmp5=tmp4//4
tmp6=tmp4%4
for i in range(tmp5):
    X.append(4)
tmp7=tmp6//2
for i in range(tmp7):
    X.append(2)
X.sort()
ans2=str()
for i in X:
    ans2+=str(i)
ansex=int(ans2)
print(ansex//2)