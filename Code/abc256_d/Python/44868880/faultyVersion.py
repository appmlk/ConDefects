N=int(input())
L=[]
R=[]
for i in range(N):
    l,r=map(int,input().split())
    L.append(l)
    R.append(r)
L.sort()
R.sort()
c=0
ans=0
now_l=0
now_r=0
is_in=[False]*(max(max(L),max(R))+1)
for i in range(1,max(max(L),max(R))+1):
    print(c,now_l,now_r)
    while now_l<len(L) and i>=L[now_l]:
        c+=1
        now_l+=1

    while now_r<len(R) and i>=R[now_r]:
        c-=1
        now_r+=1
    if(c>0):
        is_in[i]=True
is_left=False
l=0
r=0
# print(is_in)
for i in range(1,max(max(L),max(R))+1):
    if(is_left):
        if(not is_in[i]):
            is_left=False
            r=i
            print(l,r)
    else:
        if(is_in[i]):
            is_left=True
            l=i