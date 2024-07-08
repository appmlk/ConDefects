
n,m,k=list(map(int,input().split()))

nums1=[]
nums2=[]

for i in range(n):
    nums1.append(list(map(int,input().split())))
for i in range(m):
    nums2.append(list(map(int,input().split())))


l=0
r=1

while True:
    mid=(l+r)/2

    temp1=[]
    temp2=[]

    for i in range(n):
        temp1.append(nums1[i][0]-(nums1[i][0]+nums1[i][1])*mid)
    for i in range(m):
        temp2.append(nums2[i][0]-(nums2[i][0]+nums2[i][1])*mid)

    temp1.sort()
    temp2.sort()

    count=0
    for i in range(n):
        s=0
        t=m

        ng=s-1
        ok=t
        while (ok-ng)>1:
            mid2=(ng+ok)//2
            if temp2[mid2]+temp1[i]>=0:
                ok=mid2
            else:
                ng=mid2

        count+=m-ok
    
    if count>=k:
        l=mid
    else:
        r=mid

    if abs(l-r)<1e-12:
        print(l*100)
        exit()
    
