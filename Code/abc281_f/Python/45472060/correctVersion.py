def dfs(digit,A,res):
  S = []
  T = []
  for a in A:
    if (a >> digit) & 1:
      S.append(a)
    else:
      T.append(a)
  if digit == 0:
    global ans
    if len(S) == 0:
      ans = min(ans,res)
    elif len(T) == 0:
      ans = min(ans,res)
    else:
      ans = min(ans,res+1)
    return
  if len(S) == 0:
    dfs(digit-1,T,res)
  elif len(T) == 0:
    dfs(digit-1,S,res)
  else:
    dfs(digit-1,S,res+2**digit)
    dfs(digit-1,T,res+2**digit)

N = int(input())
A = list(map(int,input().split()))
ans = 2**30
dfs(29,A,0)
print(ans)