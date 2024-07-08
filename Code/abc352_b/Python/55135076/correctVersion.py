S = str(input())
T = str(input())

a = 0
b = []
c = []

for i in range(len(T)):
  if S[a] == T[i]:
    b.append(i+1)
    a+=1
  else:
    pass
  c.append(b)

print(*c[0])
