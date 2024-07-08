N=int(input())
A=list(map(int,input().split()))
limit=(N+1)//2

def f1(d,C,x):
    if len(C)<=1:
        return 0
    if d==-1:
        return len(C)//2
    c0=[]
    c1=[]
    for i in C:
        if (i>>d)&1==0:
            c0.append(i)
        else:
            c1.append(i)
    if len(c0)>len(c1):
        c0,c1=c1,c0
    if (x>>d)&1==0:
        return len(c0)+min((len(c1)-len(c0))//2,f1(d-1,c1,x))
    else:
        return f2(d-1,c0,c1,x)
    
def f2(dd,C,D,x):
    if min(len(C),len(D))==0:
        return 0
    if dd==-1:
        return min(len(C),len(D))
    c0=[]
    c1=[]
    for i in C:
        if (i>>dd)&1==0:
            c0.append(i)
        else:
            c1.append(i)
    d0=[]
    d1=[]
    for i in D:
        if (i>>dd)&1==0:
            d0.append(i)
        else:
            d1.append(i)
    if (x>>dd)&1==0:
        mc0d1=min(len(c0),len(d1))
        mc1d0=min(len(c1),len(d0))
        c0_r=len(c0)-mc0d1
        c1_r=len(c1)-mc1d0
        d0_r=len(d0)-mc1d0
        d1_r=len(d1)-mc0d1
        t=mc0d1+mc1d0
        if min(c0_r,d0_r)>0:
            t+=min(f2(dd-1,c0,d0,x),c0_r,d0_r)
        if min(c1_r,d1_r)>0:
            t+=min(f2(dd-1,c1,d1,x),c1_r,d1_r)
        return t
    else:
        return f2(dd-1,c0,d1,x)+f2(dd-1,c1,d0,x)
    
def func(C,x):
    r=f1(30,C,x)
    if r>=limit:
        return True
    else:
        return False
    
l=[0,2**30]
while l[1]-l[0]>=2:
    lm=(l[0]+l[1])//2
    if func(A,lm):
        l[0]=lm
    else:
        l[1]=lm

print(l[0])