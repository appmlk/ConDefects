B=int(input())
Frag=-1
for i in range(1,200):
  X=i**i
  if B<X:
    break
  elif B==X:
    Frag=i
    break
print(Frag)