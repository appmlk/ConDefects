a = []
while True:
  i = int(input())
  a.append(i)
  if i == 0: break
a.sort()
print(*a,sep='\n')