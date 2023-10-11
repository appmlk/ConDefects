N, M = map(int, input().split())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

def check(X):
    a = 0
    b = 0
    
    for i in range(N):
        if A[i] <= X:
            a += 1
    
    for i in range(M):
        if B[i] >= X:
            b += 1
    
    if a >= b:
        flag = True
    else:
        flag = False
    
    return flag

left = 0
right = 1000000001

while (right - left) > 1:
    mid = (right + left)//2
    flag = check(mid)
    
    if flag:
        right = mid
    else:
        left = mid

print(right)