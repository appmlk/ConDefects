N=int(input())
S=input()
T=input()
S1=list(S)
T1=list(T)
S1.sort()
T1.sort()
res=0
if S1!=T1:
    print(-1)
else:
    S2=list(S)
    T2=list(T)
    i=N-1
    while i>=0:
        a=S2.pop()
        while a!=T[i]:
            i-=1
            if i<0:
                break
        i-=1
        if i>=0:
            res+=1
    print(N-res)

