N=int(input())
C=list(map(int,input().split()))
mn=min(C)
cnt=N//mn
ans=[]
#print(cnt,mn)

for i in range(cnt):
    for j in range(8,-1,-1):
        if C[j]+(cnt-(i+1))*mn<=N:
            ans.append(j+1)
            N-=C[j]
            break
print(''.join(map(str, ans)))