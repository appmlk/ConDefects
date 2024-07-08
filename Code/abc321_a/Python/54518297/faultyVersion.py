S=str(input());
o=10;
p=len(S);
b=0
for i in range(p):
  N=int(S[i]);
  if o<N:
    o=N
    b=b+1
if b==p:
  print("Yes");
else:
  print("No")