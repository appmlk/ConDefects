def find_first_empty_pos():
    for i in range(H):
        for j in range(W):
            if not G[i][j]:
                return i,j
    return -1,-1

def is_place(h,w,a,b):
    if h+a > H or w+b > W:
        return False
    for i in range(h,h+a):
        for j in range(w,w+b):
            if G[i][j] != 0:
                return False
    return True

def place_or_delete(h,w,a,b,v):
    for i in range(h,h+a):
        for j in range(w,w+b):
            G[i][j] = v

# 深さ優先探索
def dfs(r_node):

    i,j = find_first_empty_pos()
    if i == j == -1:
        print("Yes")
        exit()
    for idx in r_node:
        height,width = T[idx]
        for a,b in [(height,width),(width,height)]:
            if is_place(i,j,a,b):
                place_or_delete(i,j,a,b,1)
                dfs(r_node-{idx})
                place_or_delete(i,j,a,b,0)

N,H,W = map(int,input().split())
T = [tuple(map(int,input().split())) for _ in range(N)]
G = [[0]*W for _ in range(H)]
S = set(range(N))
dfs(S)
print("No")