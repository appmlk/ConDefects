from collections import deque


n=int(input())
s=list(input())
t=list(input())
if sorted(s)!=sorted(t):
    print(-1)
else:
    ok=n
    ng=0
    while True:
        #print(ng,ok)
        k=(ok+ng)//2
        j=0
        p=s[k:]
        g=0
        if k==n:
            g=1
        else:
            for i in range(n):
                if t[i]==p[j]:
                    j+=1
                if j==len(p):
                    g+=1
                    break
            # p=s[:n-k]
            # j=0
            # for i in range(n):
            #     if t[i]==p[j]:
            #         j+=1
            #     if j==len(p):
            #         g+=1
            #         break
        if g==1:
            ok=k
        else:
            ng=k
        if abs(ok-ng)<=1:
            #print(ng,ok)
            print((ok+ng)//2+1)
            break
            

