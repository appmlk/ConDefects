N,L = map(int,input().split())
A = list(map(int,input().split()))
A = [0]+A+[L]
amin = 2*10**9
for i in range(1,N+1):
    s = A[i]
    high = N
    low = 1
    while high-low>1:
        mid = (high+low)//2
        t = A[mid]
        dr = L-s+L-t
        dl = s+t
        if dr>=dl:
            low = mid
        else:
            high = mid
    t1 = A[low]
    dr1 = L-s+L-t1
    dl1 = s+t1
    d1 = abs(dr1-dl1)
    t2 = A[high]
    dr2 = L-s+L-t2
    dl2 = s+t2
    d2 = abs(dr2-dl2)
    amin = min(amin,min(d1,d2))
print(2*L+amin)