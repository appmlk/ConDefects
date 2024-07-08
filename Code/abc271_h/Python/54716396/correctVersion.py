T=int(input())
for t in range(T):
    A,B,S=input().split()
    A=int(A)
    B=int(B)
    S=list(map(int,S))
    inf=1<<60
    dx=[1,1,0,-1,-1,-1,0,1]
    dy=[0,1,1,1,0,-1,-1,-1]
    def solve(A,B,S):
        if (A,B)==(0,0):
            return 0
        S=S[:]
        if A<0:
            A=-A
            S=[S[4],S[3],S[2],S[1],S[0],S[7],S[6],S[5]]
        if B<0:
            B=-B
            S=[S[0],S[7],S[6],S[5],S[4],S[3],S[2],S[1]]
        if A<B:
            A,B=B,A
            S=[S[2],S[1],S[0],S[7],S[6],S[5],S[4],S[3]]
        ans=inf
        if S[0] and B==0:
            ans=min(ans,A)
        if S[1] and A==B:
            ans=min(ans,A)
        if S[0] and S[1]:
            ans=min(ans,A)
        if S[0] and S[2]:
            ans=min(ans,A+B)
        if S[0] and S[3]:
            ans=min(ans,A+2*B)
        if S[1] and S[6]:
            ans=min(ans,2*A-B)
        if S[1] and S[7] and A%2==B%2:
            ans=min(ans,A)
        if S[7] and S[2]:
            ans=min(ans,2*A+B)
        return ans
    ans=solve(A,B,S)
    for i in range(8):
        if S[i]:
            ans=min(ans,solve(A-dx[i],B-dy[i],S)+1)
    if ans==inf:
        ans=-1
    print(ans)