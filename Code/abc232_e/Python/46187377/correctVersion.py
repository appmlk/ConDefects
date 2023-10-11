h,w,k = map(int, input().split())
x1,y1,x2,y2 = map(int, input().split())
MOD = 998244353

dp = [0 for _ in range(4)]
dp[0] = 1

for _ in range(k):
    
    dp = [
        dp[1]*(w-1) + dp[2]*(h-1),
        dp[1]*(w-2) + dp[0] + dp[3]*(h-1),
        dp[2]*(h-2) + dp[0] + dp[3]*(w-1),
        dp[1] + dp[2] + dp[3]*(h+w-4),
    ]
    dp = [x%MOD for x in dp]

if x1==x2 and y1==y2:
    ans = dp[0]
elif y1==y2:
    ans = dp[2]
elif x1==x2:
    ans = dp[1]
else:
    ans = dp[3]
print(ans)