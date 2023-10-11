N = int(input())
A = list(map(int,input().split()))

if sum(A) != 0:
    print(-1)
    exit()

B = [0]
for a in A:
    B.append(B[-1] + a)
sb = sum(B)

if sb%N != 0:
    print(-1)
    exit()
ans = abs(sb)//N

if sb > 0:
    for i in range(1,N):
        B[i] -= sb//N
    B[N-1] -= sb//N
if sb < 0:
    for i in range(1,N):
        B[i] -= sb//N
    B[1] -= sb//N

def is_ok(k):
    if k < 0: return False
    B[1] += k
    C = [0]
    for b in B:
        C.append(C[-1] + b)
        if C[-1] < 0:
            B[1] -= k
            return False
    B[1] -= k
    return True

ng = -1
ok = 10**12
while ok-ng > 1:
    m = (ok+ng)//2
    if is_ok(m):
        ok = m
    else:
        ng = m

B[1] += ok
C = [0]
for b in B:
    C.append(C[-1] + b)
ans += ok*2 + sum(C[:-2])
print(ans)
