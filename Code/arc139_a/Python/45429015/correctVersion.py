n=int(input())
a=list(map(int,input().split()))
ans=0
for i in range(n):
  ans2=(ans>>a[i])+1
  ans2=(ans2|1)<<a[i]
  ans=ans2
print (ans)