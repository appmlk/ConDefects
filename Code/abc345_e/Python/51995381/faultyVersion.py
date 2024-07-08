import sys
input = sys.stdin.readline ###

n, k = map(int, input().split())
cv = [ list(map(int, input().split())) for _ in range(n) ]

INF = 1<<62
dp = [[-1,-1,-1,-1] for _ in range(k+1)]
dp[0] = [0, 0, -1,-1]
for c, v in cv:
  np = [[-1,-1,-1,-1] for _ in range(k+1)]
  for i in range(k):
    np[i+1] = dp[i]
  for i in range(k+1):

    w = dp[i][0] if dp[i][1] !=c else dp[i][2]
    if w == -1:
      continue
    w += v
    ni = np[i]
    if ni[1] == c:
      if ni[0] < w:
        np[i][0] = v
    elif  ni[3] ==c:
      if ni[0] >= ni[2] >= w: continue
      if ni[0] >= w > ni[2]:
        np[i] = [ni[0], ni[1], w, c]
      if w > ni[0] >= ni[2]:
        np[i] = [w, c, ni[0], ni[1]]
    else:
      if ni[0] >= ni[2] >= w: continue
      if ni[0] >= w > ni[2]:
        np[i] = [ni[0], ni[1], w, c]
      if w > ni[0] >= ni[2]:
        np[i] = [w, c, ni[0], ni[1]]


  dp = np

print(dp[-1][0])