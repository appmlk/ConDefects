x,k=map(int,input().split())
for i in range(k):
  if x%10**i<5*10**i:
    x=x//10**(1+i)*10**(1+i)
  else:
    x=(x//10**(1+i)+1)*10**(1+i)
print(x)