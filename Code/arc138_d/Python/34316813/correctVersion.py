def popcount(n):
    cnt = 0
    while n:
        cnt += n & 1
        n //= 2
    return cnt

def basis01(N, K):
    lst = []
    lst2 = []
    for i in range(1 << N):
        if popcount(i) != K:
            continue
        temp = i
        for e in lst:
            temp = min(temp, e^temp)
        if temp != 0:
            lst.append(temp)
            lst2.append(i)
    return lst2
    
def make_grey(N):
    lst = []
    for i in range(1 << N):
        lst.append(i ^(i >> 1))
    return lst
        

N, K = map(int, input().split())
if K % 2 == 0 or (N != 1 and K == N):
    print("No")
    exit()

basis = basis01(N, K)
grey = make_grey(N)
ans = [0]
for n in grey:
    v = 0
    ind = 0
    while n:
        if n % 2:
            v ^= basis[ind]
        n //= 2
        ind += 1
    if v != 0:
        ans.append(v)
        
print("Yes")
print(*ans)