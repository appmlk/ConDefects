import sys
readline=sys.stdin.readline

T=int(readline())
for t in range(T):
    A=list(map(int,readline().split()))
    B=list(map(int,readline().split()))
    ans="Yes"
    if A[4]>B[4]:
        ans="No"
    B[4]-=A[4]

    mi=min(A[3],B[3])
    A[3]-=mi
    B[3]-=mi
    if A[3]>B[4]:
        ans="No"
    B[4]-=A[3]
    B[0]+=A[3]

    mi=min(A[2],B[2])
    A[2]-=mi
    B[2]-=mi
    mi=min(A[2],B[4])
    A[2]-=mi
    B[4]-=mi
    B[1]+=mi
    if A[2]>B[3]:
        ans="No"
    B[3]-=A[2]
    B[0]+=A[2]

    if A[1]>B[1]+B[2]+B[3]//2+B[4]//2 or A[0]+2*A[1]>B[0]+2*B[1]+3*B[2]+4*B[3]+5*B[4]:
        ans="No"
    print(ans)