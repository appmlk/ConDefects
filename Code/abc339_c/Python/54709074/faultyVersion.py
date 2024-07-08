n=int(input())
b=input().split()
a=[int(i) for i in b]
ans=a[0]
sum_=0
for i in a:
  sum_+=i
  ans=min(ans,sum_)
print(sum(a)-ans)
