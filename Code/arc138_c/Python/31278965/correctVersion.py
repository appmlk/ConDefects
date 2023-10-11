import sys

n=int(input())
a=list(map(int,input().split()))
sa=sorted(a)
ans=0
for i in range(n//2,n):
	ans+=sa[i]
med,s,id,mins,c=sa[n//2-1],0,0,n,0
for i,x in enumerate(a):
	if x<=med and c*2<n:
		s+=1
		c+=1
	else:
		s-=1
	if s<mins:
		id,mins=i,s
print((id+1)%n,ans)
