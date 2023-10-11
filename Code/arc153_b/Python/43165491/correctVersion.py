import sys
readline = sys.stdin.readline

H, W = map(int, readline().split())
A = [list(readline().strip()) for _ in range(H)]

x, y = 0, 0
Q = int(readline())
for q in range(Q):
    a, b = map(int, readline().split())
    if y < a:
        y = a - 1 - y
    else:
        y = H - 1 - (y-a)
    if x < b:
        x = b - 1 - x
    else:
        x = W - 1 - (x-b)

        

if Q&1:
    B = [[A[-(i-y)%H][-(j-x)%W] for j in range(W)] for i in range(H)]
else:        
    B = [[A[(i-y)%H][(j-x)%W] for j in range(W)] for i in range(H)]
for b in B:
    print("".join(b))