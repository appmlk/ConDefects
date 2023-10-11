n=int(input())
l=list(input())

lacnt=[]
lbcnt=[]
a=0
b=0
for i in l:
  if i == "A":
    a += 1
  if i == "B":
    b += 1
  lacnt.append(a)
  lbcnt.append(b)

for i in range(1,n+1):
  if lacnt[i] >= lbcnt[i]:
    print("Arice")
  else:
    print("Bob")
