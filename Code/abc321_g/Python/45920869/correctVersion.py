n,m,*E=map(int,open(0).read().split())
*X,=*D,=C=[0]*2**n
*F,P=1,998244353
while m:m-=1;C[1<<E[m]-1]+=1;D[1<<E[~m]-1]+=1;F+=F[-1]*len(F)%P,
for i in range(1<<n):
 j=k=i-1&i;c=C[i]=C[j]+C[i^j];d=D[i]=D[j]+D[i^j];x=F[c]
 while j:x-=X[i^j]*F[C[j]];j=j-1&k
 X[i]=x*(c==d)%P
print(sum(x*F[c]for x,c in zip(X,C[::-1]))*pow(F[-1],P-2,P)%P-1)