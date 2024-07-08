N,L,R=map(int,input().split())
num=[]
for x in range(1,N+1):
    if x<L:
        num.append(x)
    elif x>=L:
        for y in range(R,L-1,-1):
            num.append(y)
        break
for z in range(R+1,N+1):
    num.append(z)
print(num)