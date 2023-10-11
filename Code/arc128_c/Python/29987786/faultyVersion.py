import sys
input = lambda :sys.stdin.readline()[:-1]
ni = lambda :int(input())
na = lambda :list(map(int,input().split()))
yes = lambda :print("yes");Yes = lambda :print("Yes");YES = lambda : print("YES")
no = lambda :print("no");No = lambda :print("No");NO = lambda : print("NO")
#######################################################################

N,M,S = na()
A = na()
B = [0]
for i in range(N):
    B.append(B[-1]+A[i])
ans = 0
#[0,L),[L,R),[R,N)
for L in range(N+1):
    for R in range(L, N+1):
        if S-(N-R)*M<=0 or (S-(N-R)*M) >= M*(R-L):
            continue
        if R==L:
            z = (B[N]-B[R])*M

        else:
            z = (B[N]-B[R])*M+(B[R]-B[L])*(S-(N-R)*M)/(R-L)
        #print(L,R,z,(S-(N-R)*M)/(R-L),M,0<(S-(N-R)*M)/(R-L)<M)
        ans = max(ans,z)

print(ans)
