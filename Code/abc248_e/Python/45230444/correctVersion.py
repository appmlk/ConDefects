N,K=map(int,input().split())
if K==1:
    print("Infinity")
    exit()
S=set()

def gcd(a,b):
    if b==0:
        return a
    else:
        return gcd(b,(a%b))
    
XY=[list(map(int,input().split())) for i in range(N)]
for i in range(N):
    x0,y0=XY[i]
    for j in range(i+1,N):
        x1,y1=XY[j]
        dx=x1-x0
        dy=y1-y0
        g=gcd(dx,dy)
        dx//=g
        dy//=g
        if dx<0:
            dx*=-1
            dy*=-1
        elif dx==0 and dy<0:
            dy*=-1
            
        z=dx*y0-dy*x0
        cnt=0
        for x,y in XY:
            if dx*y-dy*x==z:
                cnt+=1
                
        if cnt>=K:
            S.add((dx,dy,z))
            
print(len(S))