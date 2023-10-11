from bisect import*
n,l,*a=map(int,open(0).read().split())
t=4*l
for c in a:x=l-c;i=bisect(a,x);t=min(t,min(x-a[i-1],a[min(i,n-1)]-x))
print(2*(t+l))