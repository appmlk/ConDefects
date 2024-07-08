g=lambda:map(int,input().split());N,M=g();P=list(g());D=998244353;f=lambda i:i*i+i;dp=[0,0,1];h=lambda x:pow(max(1,x),-1,D);A=0
for m in range(M):a,b,c=dp;X=(N-2)*(N-3)//2+1;dp=[(a*(X+2*N-8)+b*(N-3))%D,(a*4+b*(X+N-2)+c*(2*N-4))%D,(b+c*X)%D]
for i,j in zip(P,P[1:]):s=f(i-1)+f(j-1)+f(N-i)+f(N-j);t=abs(i-j);A=(A+dp[2]*t+dp[1]*(s-t*4)*h(4*N-8)+dp[0]*((N**3-N)//3-s+t*2)*h(N*N-5*N+6))%D
print(A)