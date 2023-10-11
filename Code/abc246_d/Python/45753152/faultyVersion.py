N=int(input())
def func(a,b):
    return (a+b)*(a**2+b**2)

def bs(a,N):
    left_b=a
    right_b=10**6+10
    center_b=(left_b+right_b)//2
    if func(a,left_b)>=N:
        return left_b
    while right_b-left_b>1:
        if func(a,center_b)<=N:
            left_b=max(center_b,left_b+1)
            center_b=(left_b+right_b)//2
        else:
            right_b=min(center_b,right_b-1)
            center_b=(left_b+right_b)//2
    return right_b
a1=2
a2=1
ans=func(0,bs(0,N))
for a in range(0,10**6+1):
    #print(a,bs(a,N))
    ans=min(ans,func(a,bs(a,N)))

print(ans)


#print(func(2,1))