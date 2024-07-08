N,S,T,A,B=map(int,input().split())
ans=N*B
for i in range(1000000):
    cost=B*N
    cost+=(N-T)*ans
    line=min(T-1,ans//A)
    cost+=(T-line-1)*ans
    cost+=(line*(line+1)//2)*A
    cost/=N
    ans=cost
if T-S<0:
    print('{:f}'.format(ans))
else:
    print('{:f}'.format(min((T-S)*A,ans)))