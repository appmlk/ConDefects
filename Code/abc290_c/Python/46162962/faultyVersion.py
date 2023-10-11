N,K = map(int,input().split())
A = list(map(int,input().split()))


A.sort()
A_lim = list(set(A))
for i in range(len(A)-len(A_lim)):
  A_lim.append(A_lim[-1])

check = 0
ans = 0
flag = True

for i in range(K):
  if A_lim[i] == check:
    check += 1
  else:
    ans = i
    Flag = False
    break

if flag == True:
  print(K)
elif flag == False:
  print(ans)