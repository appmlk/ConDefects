n=int(input())
S=input()
s={(0,0)}
x=0
y=0
for c in S:
    if c=='U':
        y+=1
    if c=='D':
        y-=1
    if c=='R':
        x+=1
    if c=='L':
        x-=1
    s.add((x,y))
if len(s)==n+1:
    print('No')
else:
    print('Yes')