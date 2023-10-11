S=input()
T=input()
 
W='abcdefghijklmnopqrstuvwxyz'
 
ans=True
N=W.find(T[0])-W.find(S[0])
for i in range(len(S)):
  n=W.find(T[i])-W.find(S[i])
  if n!=N:
    if abs(n)!=26+N:
       ans=False
 
if ans==True:
  print('Yes')
else:
  print('No')