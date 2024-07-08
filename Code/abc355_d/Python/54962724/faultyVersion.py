import bisect
N=int(input())
A=[]
left=[]
for i in range(N):
    l,r=map(int,input().split())
    A.append((l,r))
A.sort()
for l,r in A:
    left.append(l)
res=0
for i in range(N):
    j=bisect.bisect_right(left,A[i][1])
    res+=j-1-1
print(res)
