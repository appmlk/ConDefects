from functools import*
def ask(i,j,k):
  print('?',i+1,j+1,k+1)
  return input() == "No"
n = int(input())
p = 0
for i in range(1,n):
  if ask(i,i,p):
    p = i
P = [0]*n
def cmp(i,j):
  return 1-2*ask(p,i,j)
for p,i in enumerate(sorted(range(n),key=cmp_to_key(cmp)),1):
  P[i] = p
print('!',*P)