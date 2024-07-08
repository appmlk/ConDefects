N,L,D = map(int,input().split())
M = max(N+10,L+D+10)

#ディーラがiを通る確率 &　累積ver
dpd = [0]*(M)
dpdr = [0]*(M)
dpd[0] = 1
dpdr[0] = 1

for i in range(1,L+D):
  l = max(0,i-D)
  r = min(i-1,L-1)
  if l != 0:
    dpd[i] = (dpdr[r]-dpdr[l-1])/D
  else:
    dpd[i] = (dpdr[r])/D
  dpdr[i] = dpdr[i-1]+dpd[i]
  
#ディーラが最終的にiにいる確率
dpdlast = dpd[:]
for i in range(L):
  dpdlast[i] = 0

d_bast = 0
for i in range(N,M):
  d_bast += dpd[i]

  
#ディーらがi未満で終わる確率
dpdmiman = [0]*M
for i in range(1,M):
  dpdmiman[i] = dpdmiman[i-1]+dpdlast[i-1]



#そのマスに自分がいて、最適戦略を取った時の勝率
dpwin = [0]*(M)
dprwin = [0]*(M)

for i in range(M-1,-1,-1):
  if i==M-1:
    if i>N:
      dpwin[i] = 0
      dprwin[i] = 0
    else:
      dpwin[i] = 1
    continue
  
  if i>N:
    dpwin[i] = 0
    dprwin[i] = dprwin[i+1]
    continue
    
  
  r = min(i+D,M-2)
  l = i+1
  
  #サイコロを振った時の勝ち確率
  dice_win = (dprwin[l]-dprwin[r+1])/D
  #サイコロ降らない時の勝ち確率
  stay_win = dpdmiman[i]+d_bast
  win = max(dice_win,stay_win)
  dpwin[i] = win
  dprwin[i] = win + dprwin[i+1]
  
print(dpwin[0])
