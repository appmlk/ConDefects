N, M=map(int, input().split())
goal=input().split()
nutrition=[]
check=[]
for i in range(N):
  nutrition.append(input().split())
def sum_nut(N,nut_num,goal,check):
  sum_n=[]
  for i in range(N):
    sum_n.append(int(nutrition[i][nut_num]))
  if int(goal[nut_num])<sum(sum_n):
    check.append(1)
  else:
    check.append(0)
for i in range(M):
  sum_nut(N,i,goal,check)
if sum(check)==M:
  print("Yes")
else:
  print("No")
