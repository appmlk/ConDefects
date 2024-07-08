T=int(input())
for t in range(T):
    B,K,Sx,Sy,Gx,Gy=map(int,input().split())
    ans=(abs(Sx-Gx)+abs(Sy-Gy))*K
    for a,b in ((Sx,Sy//B*B),(Sx,(Sy+B-1)//B*B),(Sx//B*B,Sy),((Sx+B-1)//B*B,Sy)):
        for c,d in ((Gx,Gy//B*B),(Gx,(Gy+B-1)//B*B),(Gx//B*B,Gy),((Gx+B-1)//B*B,Gy)):
            if a==c and a%B==0 or b==d and b%B==0:
                ans=min(ans,(abs(Sx-a)+abs(Sy-b)+abs(Gx-c)+abs(Gy-d))*K+abs(a-c)+abs(b-d))
    for a in (Sx//B*B,(Sx+B-1)//B*B):
        for b in (Sy//B*B,(Sy+B-1)//B*B):
            for c in (Gx//B*B,(Gx+B-1)//B*B):
                for d in (Gy//B*B,(Gy+B-1)//B*B):
                    ans=min(ans,min(abs(Sx-a),abs(Sy-b))*K+max(abs(Sx-a),abs(Sy-b))+min(abs(Gx-c),abs(Gy-d))*K+max(abs(Gx-c),abs(Gy-d))+abs(a-c)+abs(b-d))
    print(ans)