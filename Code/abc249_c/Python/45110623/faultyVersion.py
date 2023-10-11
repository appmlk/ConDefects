n,m=map(int,input().split())
s=[input() for i in range(n)]
a=0
for i in range(1,(1<<n)-1):
  c=[0]*26
  for j in range(n):
    if (i>>j)&1:
      for k in range(26):
        c[k]+=chr(k+ord("a")) in s[j]
  a=max(a,c.count(m))
print(a)