H,W,K = map(int,input().split())
S = [list(input()) for _ in [0]*H]

dic = {".":0,"o":1}

ans = -1


for s in S:
  mx = -1
  p = 0
  leng = 0
  for i in range(W):
    if s[i] == "x" :
      p,leng=0,0
    else:
      leng += 1
      if 0<=leng<=K : p += dic[s[i]]
      else :
        p += dic[s[i]] - dic[s[i-K]]
      if leng >= K and mx < p : mx = p
    # print(s,s[i],mx,p,leng)
  if ans < mx : ans = mx

S_t = [list(x) for x in zip(*S)]
# print(S)
# print(S_t)


for s in S_t:
  mx = -1
  p = 0
  leng = 0
  for i in range(H):
    if s[i] == "x" :
      p,leng=0,0
    else:
      leng += 1
      if 0<=leng<=K : p += dic[s[i]]
      else :
        p += dic[s[i]] - dic[s[i-K]]
        if mx < p : mx = p
  if mx > ans : ans = mx
print(K-ans if ans!=-1 else -1)