s=[]
for i in range(9):
  s.append(input())
ans=0
for k in range(8):
  for i in range(8-k):
    for j in range(8-k):
      if s[i][j]=="#" and s[i+k+1][j]=="#" and s[i][j+k+1]=="#" and s[i+k+1][j+k+1]=="#":
        ans+=1
for k in range(4):
  for i in range(7-2*k):
    for j in range(7-2*k):
      if s[i+k+1][j]=="#" and s[i][j+k+1]=="#" and s[i+k+1][j+2*(k+1)]=="#" and s[i+2*(k+1)][j+k+1]=="#":
        ans+=1
for i in range(6):
  for j in range(6):
    if s[i+1][j]=="#" and s[i][j+2]=="#" and s[i+2][j+3]=="#" and s[i+3][j+1]=="#":
      ans+=1
    if s[i][j+1]=="#" and s[i+2][j]=="#" and s[i+1][j+3]=="#" and s[i+3][j+2]=="#":
      ans+=1
for i in range(3):
  for j in range(3):
    if s[i+4][j]=="#" and s[i][j+2]=="#" and s[i+6][j+4]=="#" and s[i+2][j+6]=="#":
      ans+=1
    if s[i+2][j]=="#" and s[i][j+4]=="#" and s[i+6][j+2]=="#" and s[i+4][j+6]=="#":
      ans+=1
for i in range(5):
  for j in range(5):
    if s[i][j+1]=="#" and s[i+1][j+4]=="#" and s[i+3][j]=="#" and s[i+4][j+3]=="#":
      ans+=1
    if s[i+1][j]=="#" and s[i+4][j+1]=="#" and s[i][j+3]=="#" and s[i+3][j+4]=="#":
      ans+=1
if s[0][2]=="#" and s[2][8]=="#" and s[6][0]=="#" and s[8][6]=="#":
  ans+=1
if s[2][0]=="#" and s[8][2]=="#" and s[0][6]=="#" and s[6][8]=="#":
  ans+=1
for k in range(4):
  for i in range(4-k):
    for j in range(4-k):
      if s[i][j+1]=="#" and s[i+1][j+5+k]=="#" and s[i+4+k][j]=="#" and s[i+5+k][j+4+k]=="#":
        ans+=1
      if s[i+1][j]=="#" and s[i+5+k][j+1]=="#" and s[i][j+4+k]=="#" and s[i+4+k][j+5+k]=="#":
        ans+=1
for k in [3,5]:
  for i in range(9-2-k):
    for j in range(9-2-k):
      if s[i][j+2]=="#" and s[i+2][j+2+k]=="#" and s[i+k][j]=="#" and s[i+2+k][j+k]=="#":
        ans+=1
      if s[i+2][j]=="#" and s[i+2+k][j+2]=="#" and s[i][j+k]=="#" and s[i+k][j+2+k]=="#":
        ans+=1
for k in [4,5]:
  for i in range(9-3-k):
    for j in range(9-3-k):
      if s[i][j+3]=="#" and s[i+3][j+3+k]=="#" and s[i+k][j]=="#" and s[i+3+k][j+k]=="#":
        ans+=1
      if s[i+3][j]=="#" and s[i+3+k][j+3]=="#" and s[i][j+k]=="#" and s[i+k][j+3+k]=="#":
        ans+=1
print(ans)