N,M=map(int,input().split(' '))
A=list(map(int,input().split(' ')))
sumlist=[]
nolist=[]
result=[]
for i in range(N):
    temp=input()
    sum=0
    nokai=[]

    for index,e in enumerate(temp):
        if e=="o":
            sum+=A[index]
        else:
            nokai.append(A[index])
    
    sort_nokai =sorted(nokai, reverse=True)

    nolist.append(sort_nokai)   

    if sum>0:
        sumlist.append(sum+(i+1))
    else:
        sumlist.append(sum+(i+1))

maxsum=max(sumlist)
# print(maxsum,sumlist,nolist)

k=0
for iindex,ee in enumerate(sumlist):
    sabun=maxsum-ee
    k=0
    if sabun>0:
        while sabun>0:
            if k==0 and ee==0:
                #sabun=sabun-nolist[iindex][k]-(iindex+1)
                sabun=sabun-nolist[iindex][k]
            else:
                sabun=sabun-nolist[iindex][k]
            k+=1
        result.append(k)
    elif maxsum==0:
        result.append(1)    
    else:
        result.append(0)

for eee in result:
    print(eee)                

        



