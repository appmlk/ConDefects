n,s,t,a,b=map(int,input().split())

def f(l:int)->float:
    if l<=s<=t:return (t-s)*a
    sm=t*(t-l+1)-(t+l)*(t-l+1)/2
    w=t-l+1
    return a*sm/w+b+(n-w)*b/w

l=1
r=t
while r-l>2:
    n1=(2*l+r)//3
    n2=(l+2*r)//3
    if f(n1)>=f(n2):l=n1
    else:r=n2
ans=10**50
for i in range(l-5,r+5):
    if 1<=i<=t:
        ans=min(ans,f(i))
print("{:.50f}".format(ans))