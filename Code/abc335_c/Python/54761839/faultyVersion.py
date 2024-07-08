s=input().split()
n=int(s[0])
a=[[n-i,0] for i in range(n)]
q=int(s[1])
str="LURD"
d=[1,0,-1,0]
for _ in range(q):
  s=input().split()
  if s[0]=='1':
    a.append([a[-1][0]+d[str.index(s[1])],a[-1][1]+d[str.index(s[1])-1]])
  else:
    print(a[-int(s[1])][0],a[-int(s[1])][1])