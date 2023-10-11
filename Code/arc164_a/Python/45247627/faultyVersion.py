T=int(input())
for i in range(T):
    ternary=[]
    N,K=map(int,input().split())
    N2=N
    while N2>0:
        ternary.append(N2%3)
        N2//=3
    print(ternary)
    print(sum(ternary))
    if N%2!=K%2:
        print("No")
    else:
        if K<sum(ternary):
            print("No")
        else:
            print("Yes")
            