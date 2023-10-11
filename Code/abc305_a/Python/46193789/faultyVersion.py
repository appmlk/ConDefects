N=int(input())
list=[]
for i in range(20):
  list.append(abs(N-i*5))
In=list.index(min(list))
print(In*5)