import sys;input=sys.stdin.readline
def bsearch(mn, mx, func):
    #func(i)=False を満たす最大のi (mn<=i<mx)
    idx = (mx + mn)//2
    while mx-mn>1:
        if func(idx):
            idx, mx = (idx + mn)//2, idx
            continue
        idx, mn = (idx + mx)//2, idx
    return idx

def f(k):
#    print()
    sm = 0
    for i in range(N):
        a, b = X[i], X[N-1-i]
        if b-a < k:
            break
#        print(a,b)
        sm += b-a-k
#    print(sm)
    for i in range(N):
        a, b = Y[i], Y[N-1-i]
        if b-a < k:
            break
        sm += b-a-k
    return sm

N, K = map(int, input().split())
X = []
Y = []
for _ in range(N):
    x, y = map(int, input().split())
    X.append(x)
    Y.append(y)
X.sort()
Y.sort()
#print(f(3))
print(bsearch(-1, 10**9*1, lambda x:f(x)<=K)+1)
    
