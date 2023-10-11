N=int(input())
A=[]
for i in range(N):
    C,D=[],[]
    for j in range(1,N+1):
        n=N*i+j
        if len(C)<(N+1)//2:
            C.append(n)
        else:
            D.append(n)
    ans=''
    for j in range(1,N+1):
        if j%2==1:
            ans+=str(C[j//2])
        else:
            ans+=str(D[(j-1)//2])
        ans+=' '
    print(ans)