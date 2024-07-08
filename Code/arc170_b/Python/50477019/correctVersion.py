N = int(input())
A = list(map(int,input().split()))

def hantei(lst):
    seen = set()
    out = set()
    for i in range(len(lst)):
        if lst[i] in out:
            return 0
        for s in seen:
            # out.add(s+(s-lst[i]))
            out.add(lst[i]+(lst[i]-s))
        seen.add(lst[i])
    return 1

bad = 0
for n in range(N):
    for m in range(1,min(21,N-n+1)):
        # print(*A[n:n+m],hantei(A[n:n+m]))
        bad += hantei(A[n:n+m])
        
print((N*(N+1))//2-bad)