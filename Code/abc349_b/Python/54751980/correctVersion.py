S = list(input())
num = [0]*1000

for s in S:
  num[S.count(s)] += 1
for n in num:
  if  2 * num.index(n) != n:
    print('No')
    exit()

print('Yes')