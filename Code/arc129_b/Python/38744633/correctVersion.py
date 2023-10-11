

def Main():
    n=int(input())
    maxl=-1000000000000000000
    minr=1000000000000000000
    for _ in range(n):
        l,r=map(int,input().split())
        maxl=max(maxl,l)
        minr=min(minr,r)
        if maxl<=minr:
            print(0)
        else:
            print(max(maxl-(maxl+minr)//2,(maxl+minr)//2-minr))            
Main()

