N=int(input())
#HA,WA=map(int,input().split())
A=list(map(int,input().split()))
#S=[(input(),i) for i in range(N)]


def solve():
    K=0
    ans=[]

    for i in range(2*N-1):
        if i%2==1: #should be A[i]>A[i+1]
            if A[i]<A[i+1]:
                if A[i]>A[i+2]:

                    A[i],A[i+1]=A[i+1],A[i]
                    ans.append(i+1)
                else:
                    A[i+1],A[i+2]=A[i+2],A[i+1]
                    ans.append(i+2)
                    
                K+=1
                

        else:
            if A[i]>A[i+1]:
                if i==2*N-2 or A[i]>A[i+2]:
                    A[i],A[i+1]=A[i+1],A[i]
                    ans.append(i+1)
                else:
                    A[i+1],A[i+2]=A[i+2],A[i+1]
                    ans.append(i+2)
                
                K+=1


    print(K)
    #print(A)
    print(*ans)

solve()

