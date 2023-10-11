N=int(input())
list=[]
for i in range(21):
  list.append(abs(N-i*5))
In=list.index(min(list))
print(In*5)