n,a,b,*D = map(int,open(0).read().split())
s = sum(D)
p,q = abs(a),abs(b)
if p+q>s or (s+p+q)%2:
  print("No")
  exit()
x,y = (s+p+q)//2,(s+p-q)//2
dp = [0]*(n+1)
dp[0] = 1
for i in range(n):
  dp[i+1] = dp[i]|dp[i]<<D[i]
if not dp[n]>>x&dp[n]>>y&1:
  print("No")
  exit()
L = []
for i in range(n)[::-1]:
  s,t = (dp[i]>>x&1)^1,(dp[i]>>y&1)^1
  L.append("DLRU"[s*2+t])
  if s:
    x -= D[i]
  if t:
    y -= D[i]
ans = "".join(L[::-1])
if a < 0:
  ans = ans.translate(str.maketrans({"L":"R","R":"L"}))
if b < 0:
  ans = ans.translate(str.maketrans({"U":"D","D":"U"}))
print("Yes")
print(ans)