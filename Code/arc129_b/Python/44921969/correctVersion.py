n=int(input())
a=-10**10
b=10**10
for _ in range(n):
    l,r=map(int,input().split())
    a=max(a,l)
    b=min(b,r)
    if a<=b:
        print(0)
    else:
        print((a-b+1)//2)