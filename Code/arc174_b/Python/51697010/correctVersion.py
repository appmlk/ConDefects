t=int(input())
for _ in range(t):
    A = list(map(int, input().split()))
    P = list(map(int, input().split()))
    s=sum(A)*3
    ans=0
    for i,j in enumerate(A):
        s-=(i+1)*j
    if s<=0:
        print(0)
        continue
    if P[3]*2<=P[4]:
        print(s*P[3])
    else:
        if s%2==0:
            print((s//2)*P[4])
        else:
            print((s//2)*P[4]+min(P[3],P[4]))