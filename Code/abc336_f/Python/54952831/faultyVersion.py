
def rint():
    return list(map(int, input().split())) 

H, W = rint()
S = [[a for a in rint()] for _ in range(H)]
G = [[i*W+j+1 for j in range(W)] for i in range(H)]
# print(G)
def op(A, x,y):
    for i in range(H-1):
        for j in range(W-1):
            if i*W+j > (H-1)*(W-1)//2:
                return
            A[x+i][y+j], A[x+H-2-i][y+W-2-j] = A[x+H-2-i][y+W-2-j], A[x+i][y+j]
            

# print(*G, sep='\n')
# print()
# op(G, 0,0)
# print(*G, sep='\n')
# exit()
moves = [(0,0),(0,1),(1,0),(1,1)]

def freeze(A):
    return tuple(tuple(row) for row in A)
def thaw(A):
    return list(list(row) for row in A)

vis = dict()

from collections import deque
q = deque()
q.append((freeze(S), 0))
while q:
    A, cost = q.popleft()
    # if cost == 2:
    #     print(*A, sep='\n')
    #     print()
    
    vis[A] = cost
    if cost == 10:
        continue
    for move in moves:
        B = thaw(A)
        op(B,*move)
        B = freeze(B)
        if B not in vis:
            q.append((freeze(B), cost+1))


q = deque()
q.append((freeze(G), 0))

vis2 = set()
inf = float('inf')
ans = inf
while q:
    A, cost = q.popleft()

    vis2.add(A)
    if A in vis:
        ans = min(ans, vis[A] + cost)

    if cost == 10:
        continue
    for move in moves:
        B = thaw(A)
        op(B,*move)
        B = freeze(B)
        if B not in vis2:
            q.append((freeze(B), cost+1))

print(ans if ans != inf else -1)