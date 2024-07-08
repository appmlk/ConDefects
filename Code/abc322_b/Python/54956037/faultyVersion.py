n,m=map(int,input().split())
s=input()
t=input()

if t == s:
  print(0)
elif t.find(s)==0:
  print(1)
elif t.rfind(s)==m-n:
  print(2)
else:
  print(3)