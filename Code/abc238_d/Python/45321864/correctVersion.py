T=int(input())

for _ in range(T):
    a,s=map(int,input().split())
    b=s-2*a
    if b>=0 and a&b==0:
        print('Yes')
    else:
        print('No')