x,a,d,n=map(int,input().split())
if d<0:
    a=a+(n-1)*d
    d=d*-1
m=a+(n-1)*d
print(a-x if a>x or d==0 else x-m if m<x else min((x-a)%d,d-(x-a)%d))