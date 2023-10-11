import itertools
S = []
N,M = map(int,input().split())
for _ in range(N):
  s = list(input())
  S.append(s)
com = itertools.product(S,repeat = N)
for co in com:
  ans = "Yes"
  for k in range(len(co)-1):
    no = 0
    for j in range(M):
      if co[k][j] != co[k+1][j]:
        no+=1
    if no != 1:
      ans = "No"
  if ans == "Yes":
    print(ans)
    exit()
print(ans)