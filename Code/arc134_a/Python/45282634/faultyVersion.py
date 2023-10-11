n,l,w=map(int,input().split())
a=list(map(int,input().split()))
tmp=0
ans=0
for i in a:
  ans+=max(0,(i-tmp+w-1)//w)
  tmp=i+l
ans+=max(0,(l-tmp+w-1)//w)
print (ans)