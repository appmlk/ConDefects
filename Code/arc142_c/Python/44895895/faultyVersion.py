
N=int(input())

d=[]
for i in range(N-2):
    print("?", 1, i+3)
    d.append(int(input()))

for i in range(N-2):
    print("?",2,i+3)
    d.append(int(input()))

ans=[]
for i in range(N-2):
    ans.append([d[i]+d[i+N-2],i+1])
ans.sort()
if ans[0][0]!=3:
    print("!",ans[0][0])
elif ans[1][0]==3:
    print("?",ans[0][1],ans[1][1])
    n=int(input())
    if n==1:
        print("!",3)
    else:
        print("!",1)
else:
    print("!",1)
