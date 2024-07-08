H, W, K = map(int,input().split())
sh, sw = map(int,input().split())
sh, sw = sh-1, sw-1
A = []
for _ in range(H):
    A.append(list(map(int,input().split())))
    
trial = min(H*W-1,K)
# dp[i][h][w]:i回目に(h,w)にいるときの「楽しさ」の最大
dp = [[[-1 for _ in range(W)] for _ in range(H)] for _ in range(trial+1)]
dp[0][sh][sw] = 0

mv = [(0,1),(1,0),(0,-1),(-1,0)]

def enable(h,w):
    return 0 <= h < H and 0 <= w < W

for i in range(trial):
    for h in range(H):
        for w in range(W):
            if dp[i][h][w] == -1:
                continue
            for dh, dw in mv:
                nh, nw = h+dh, w+dw
                if enable(nh,nw):
                    dp[i+1][nh][nw] = max(dp[i+1][nh][nw],dp[i][h][w]+A[nh][nw])
                    
maxv = -1
for i in range(trial+1):
    for h in range(H):
        for w in range(W):
            maxv = max(maxv,dp[i][h][w]+A[h][w]*(K-i))
            
print(maxv)