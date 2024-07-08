from collections import deque 

n=int(input())
A=[]
for i in range(n+1):
    A.append(deque())
Q=[]
for i in range(n):
    t,x=map(int,input().split())
    Q.append([t,x])

S=set()
for i in range(n-1,-1,-1):
    t,x=Q[i][0],Q[i][1]
    
    if t==1:
        if len(A[x])>0:
            A[x].pop()
            S.add(i)

    if t==2:
        A[x].append(i)

for i in range(n+1):
    if len(A[i])>0:
        print(-1)
        exit()

cnt=0
k=0
ans=[]
for i in range(n):
    t,x=Q[i][0],Q[i][1]
    if t==1:
        if i in S:
            cnt+=1
            ans.append(1)
        else:
            ans.append(0)

    if t==2:
        cnt-=1

    k=max(k,cnt)
print(k,*ans)