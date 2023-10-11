from collections import deque
li=[i*(i+1)//2 for i in range(1,2000)]
N=int(input())
ans=[]
now=N
for j in range(1998,-1,-1):
  for k in range(now//li[j]):  
    ans.append(j+1)
  now%=li[j]
fin_ans=deque()
for ai in ans:
  for _ in range(ai):
    fin_ans.appendleft("7")
  fin_ans.appendleft(str(pow(3,len(fin_ans)*5,7)))
print("".join(fin_ans))