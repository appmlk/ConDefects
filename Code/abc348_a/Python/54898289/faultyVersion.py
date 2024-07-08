N = int(input())
list=[]
for i in range(N):
  if (i+1) % 3 == 0:
    list.append("x")
  else:
    list.append("o")
print(" ".join(list))