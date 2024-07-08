import sys
input=sys.stdin.readline
T=int(input())
MOD=998244353
def f(p):
    return 9*pow(10,p-1,MOD)

def g(p,q):
    if p==q:
        retval=(f(a)-pow(10,p-1,MOD))*\
                (f(a)-pow(10,p-1,MOD)+1)//2
        retval%=MOD
        return retval
    else:
        h=f(a)
        s=f(b)-pow(10,a-1,MOD)
        t=f(b)-pow(10,a,MOD)+1
        retval=(s+t)*h*pow(2,-1,MOD)
        retval%=MOD
        return retval

for i in range(T):
    a,b,c=map(int,input().split())
    if a>b:
        a,b=b,a
    if b<=c<=b+1:
        if a==b and b==c:
            ans=g(a,b)
        elif a<b and b==c:
            ans=g(a,b)
        elif b<c:
            M=f(b)*f(a)
            m=g(a,b)
            ans=M-m
            ans%=MOD
    else:
        ans=0
    print(ans)