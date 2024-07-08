def main():
    N,M=map(int,input().split())
    A=list(map(int,input().split()))
    A.sort()
    B=[]

    for n in range(N-1):
        B.append(A[n+1]-A[n])
    
    kyori=0
    kazu=1
    i=0
    ans=0
    for b in B:
        kyori+=b
        kazu+=1
        if kyori>=M:
            while kyori>=M:
                kyori-=B[i]
                i+=1
                kazu-=1
        ans=max(ans,kazu)
    print(ans)

if __name__=="__main__":
    main()