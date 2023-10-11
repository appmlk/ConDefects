N,M=map(int,input().split())
L=list(map(int,input().split()))
def isok(n):
    dist=-1
    count=0
    for i in range(N):
        if L[i]>n:
            return False
        if dist+1+L[i]>n:
            dist=-1
            count+=1
        dist+=(1+L[i])
    if count+1<=M:
        return True
    return False

left,right=0,10**15
while right-left>1:
    mid=(right+left)//2
    if isok(mid):
        right=mid
    else:
        left=mid
print(right)