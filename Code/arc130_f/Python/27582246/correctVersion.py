import sys
input = sys.stdin.readline

N=int(input())
A=list(map(int,input().split()))

def calc(l,r):
    x=abs(A[r]-A[l])
    q=x//(r-l)
    rr=x%(r-l)
    
    if A[l]<A[r]:
        for i in range(r-l):              
            if (r-(l+i))<=rr-1:
                A[l+i]=min(A[l+i],rr-(r-(l+i))+A[l]+q*i)
            else:
                A[l+i]=min(A[l+i],A[l]+q*i)
                
                
    else:
        for i in range(r-l):
            if ((r-i)-l)<=rr-1:
                A[r-i]=min(A[r-i],rr-((r-i)-l)+A[r]+q*i)
            else:
                A[r-i]=min(A[r-i],A[r]+q*i)

for tests in range(20):

    X=[(i,A[i]) for i in range(N)]

    Q=[]
    for i,a in X:
        if len(Q)<=1:
            Q.append((i,a))
        else:
            while len(Q)>=2:
                i1,k1=Q[-1]
                i2,k2=Q[-2]

                if (k1-k2)*(i-i2)>(a-k2)*(i1-i2):
                    Q.pop()
                else:
                    break
                
            Q.append((i,a))

    for i in range(len(Q)-1):
        calc(Q[i][0],Q[i+1][0])

print(sum(A))