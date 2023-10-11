a=int(input())
x=0
y=0
lot=[]
deli=[]
for i in range(a):
    x,y=map(int,input().split())
    lot.append(x)
    deli.append(y)
x=lot[deli.index(max(deli))]
y=deli.index(max(deli))
maxx=max(deli)
lot.pop(y)
deli.pop(y)
num=[]
for i in range(len(deli)):
    if lot[i]==x:
        num.append(deli[i]/2)
    else:
        num.append(deli[i])
print(maxx+max(num))