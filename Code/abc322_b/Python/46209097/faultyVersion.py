n,m=map(int,input().split())
s=input()
t=input()
k=3
if t[0:n]==s:
  k-=2
elif t[-n::]==s:
  k-=1
print(k)