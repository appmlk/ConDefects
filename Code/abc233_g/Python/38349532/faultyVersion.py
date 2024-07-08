N=int(input())
S=[input() for _ in range(N)]

#i行目のl列目からr列目までに"#"は含まれるか
pre_h = [0]*N**3
for i in range(N):
    for l in range(N):
        for r in range(N):
            for k in range(l,r+1):
                if S[i][k]=="#":
                    pre_h[i*N**2+l*N+r]=1
                    
#i列目のl行目からr行目までに"#"は含まれるか
pre_w = [0]*N**3
for i in range(N):
    for l in range(N):
        for r in range(N):
            for k in range(l,r+1):
                if S[k][i]=="#":
                    pre_w[i*N**2+l*N+r]=1

INF=N+10
di=[INF]*N**4

def dp(lx,ly,rx,ry):
  if lx>rx or ly>ry: return 0
  C=lx*N**3+ly*N**2+rx*N+ry
  if di[C]!=INF: return di[C]
  ans=max(rx-lx+1,ry-ly+1)
  for i in range(lx,rx+1):
    if not pre_h[i*N**2+ly*N+ry]:
      ans=min(ans,dp(lx,ly,i-1,ry)+dp(i+1,ly,rx,ry))
  for j in range(ly,ry):
    if not pre_w[j*N**2+lx*N+rx]:
      ans=min(ans,dp(lx,ly,rx,j-1)+dp(lx,j+1,rx,ry))
  di[C]=ans ; return ans

print(dp(0,0,N-1,N-1))