S = list(input())
num = [0]*1000

for s in S:
  num[S.count(s)] += 1
  
for n in num:
  if n % 2 != 0:
    print('No')
    exit()
    
print('Yes')