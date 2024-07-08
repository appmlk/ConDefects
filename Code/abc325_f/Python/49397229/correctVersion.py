def solve():
    n=int(input())
    A=list(map(int,input().split()))
    l1,c1,k1=map(int,input().split())
    l2,c2,k2=map(int,input().split())
    dp=[[0,0]]
    for a in A:
        cur=[]
        d1=(a+l1-1)//l1
        for x in range(d1+1):
            y=max(0,(a-l1*x+l2-1)//l2)
            for nx,ny in dp:
                if nx+x<=k1 and ny+y<=k2:
                    cur.append([nx+x,ny+y])
        cur.sort()
        dp=[]
        for x,y in cur:
            if not dp or dp[-1][1]>y:
                dp.append([x,y])
    if not dp:
        print(-1)
        return
    ans=10**20
    for a,b in dp:
        ans=min(ans,a*c1+b*c2)
    print(ans)




for _ in range(1):
    solve()