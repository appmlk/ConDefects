n, a, b, *d = map(int, open(0).read().split())
s = sum(d)
x = a+b+s
y = b-a+s
res = ""
if x >= 0 <= y and x & 1 == 0:
    x >>= 1
    y >>= 1
    dp = [1] 
    for j in d:
        dp += dp[-1] << j | dp[-1],
    if dp[n] >> x & 1 and dp[n] >> y & 1:
        while n:
            n -= 1
            i = ~dp[n] >> x & 1
            j = ~dp[n] >> y & 1
            res += "DLRU"[2*i+j]
            x -= i * d[n]
            y -= j * d[n]
print(res and "Yes" or "No",res)
