W,B=map(int,input().split())

can_l=[]
Ans="No"
piano="wbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbwwbwbwwbwbwbw"
piano=list(piano)
for i in range(len(piano)):#何文字とるか
  i+=1
  for j in range(len(piano)-i):#何文字目からとるか
    A=[0,0]
    for k in range(i):#i文字のうち何文字目をとるか
      k+=1
      if piano[j+k]=="w":
        A[0]+=1
      if piano[j+k]=="b":
        A[1]+=1
      #print(i,j,k)
    if A not in can_l:
      can_l.append(A)

#print(can_l)
test_l=[W,B]

if test_l in can_l:
  Ans="Yes"
  
print(Ans)