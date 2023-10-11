from bisect import*
n,l,*a=map(int,open(0).read().split())
t=4*l
for c in a:x=l-c;i=bisect(a,x);t=min(t,min(abs(x-a[min(max(j,0),n-1)])for j in[i,i-1]))
print(2*(t+l))