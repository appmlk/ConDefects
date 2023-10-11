n=int(input())
s=input()

dango=0
lv=0
x=0
for i in range(n):
  if s[i]=="-":
    lv=0
    dango=1
  else:
    if dango==1:
      lv+=1
  if lv>x:
    x=lv

dango=0
lv=0
y=0
for i in range(n):
  if s[n-1-i]=="-":
    lv=0
    dango=1
  else:
    if dango==1:
      lv+=1
  if lv>y:
    y=lv

if x!=0 or y!=0:
  print(max(x,y))
else:
  print(-1)