N,Q = map(int,input().split())
li = [i for i in range(N+1)]
ind = [i for i in range(N+1)]
for i in range(Q):
    A = int(input())
    B = ind[A]
    
    if B != N:
        temp = li[B]
        li[B] = li[B+1]
        li[B+1] = temp
        ind[A] = B+1
        ind[li[B]] = B
    else:
        temp = li[B]
        li[B] = li[B-1]
        li[B-1] = temp
        ind[A] = B-1
        ind[li[B]] = B

print(*li[1:])

