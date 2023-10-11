N,M=map(int,input().split())
open_cans=[]
closed_cans=[]
openers=[]

for _ in range(N):
    T,X=map(int,input().split())
    if T==0:
        open_cans.append(X)
    elif T==1:
        closed_cans.append(X)
    else:
        openers.append(X)

open_cans.sort()
closed_cans.sort(reverse=True)
openers.sort(reverse=True)
l1=len(open_cans)
l2=len(closed_cans)
l3=len(openers)

index_1=0
index_2=0
index_3=0
opener_count=0
count=l1

if l1<=M:
    tmp=sum(open_cans)
    while count<M:
        if opener_count==0:
            if index_2<l2 and index_3<l3:
                opener_count+=openers[index_3]
                index_3+=1
            else:
                break
        else:
            if index_2<l2:
                tmp+=closed_cans[index_2]
            else:
                break
            index_2+=1
            opener_count-=1
        count+=1
else:
    open_cans=open_cans[-M:]
    tmp=sum(open_cans)

answer=tmp
for i,open_can in enumerate(open_cans):
    count-=1
    tmp-=open_can
    if opener_count==0:
        if index_2<l2 and index_3<l3:
            opener_count+=openers[index_3]
            index_3+=1
        else:
            break
    else:
        if index_2<l2:
            tmp+=closed_cans[index_2]
        else:
            break
        index_2+=1
        opener_count-=1
    answer=max(answer,tmp)

answer=max(answer,tmp)
print(answer)