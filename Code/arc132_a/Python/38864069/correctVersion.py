
def solve():
    n=int(input())
    r=list(map(int,input().split()))
    c=list(map(int,input().split()))
    q=int(input())
    for i in range(q):
        x,y=map(int,input().split())
        x-=1
        y-=1
        if r[x]+c[y]>n:
            print("#",end="")
        else:
            print(".",end="")




for test in range(1):
    solve()
