a,b,c,x=list(map(int,input().split()))
ans=1.0
if x>=a+1 and x<=b:
    ans=c/(b-a)
elif x>b:
    ans=0.0
print('%.10f'%ans)
