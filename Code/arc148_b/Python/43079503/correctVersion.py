N=int(input())
S=input()
psta=[]
keep=N
cnt=0
for i in range(N-1,-1,-1):
  if S[i]=="p":
    if cnt==0:
      keep=i
      cnt=1
    else:
      cnt+=1
  else:
    if cnt!=0:
      psta.append((cnt,keep))
      cnt=0
if cnt!=0:
  psta.append((cnt,keep))
psta=sorted(psta)
if len(psta)==0:
  print(S)
else:
  cnt,back=psta[-1]
  ans=[]
  for sta in range(N):
    if S[sta]=="p":
      break
  #print(sta,back)
  T=S[sta:back+1]
  T="".join(list(reversed(T)))
  T=T.replace('d', 'X').replace('p', 'd').replace('X', 'p')
  ans.append(S[:sta]+T+S[back+1:])
  for i in range(len(psta)-2,-1,-1):
    a,back=psta[i]
    #print(sta,back)
    T=S[sta:back+1]
    T="".join(list(reversed(T)))
    T=T.replace('d', 'X').replace('p', 'd').replace('X', 'p')
    ans.append(S[:sta]+T+S[back+1:])
  #print(ans)
  print(min(ans))