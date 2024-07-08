#D - Divide Interval
l, r = map(int, input().split())

#l = 3 の時、(3,4)のみ
#l = 4の時、 (4,5)(4,6)(4,8)があるので(4,8)←4 = 2**2 (2**)
#l = 8の時、(8,9)(8,12)(8,16)
#l = 16の時、(16,17)(16,18)(16,20)(16,24)(16,32)
#l + 0, l + 2**0, l + 2**1, l + 2**3, ...
#lが奇数の時、l + 2**0のみ
#lが偶数の時、

lis = []
if l % 2 == 1:
  lis.append([l])
  l = l + 1

while l != r:
  i = 0
  while l % pow(2, i+1) == 0 and l + pow(2, i+1) <= r:
    i += 1
  lis.append([l, l + pow(2, i)])
  l += pow(2, i)
print(len(lis))

for k in range(len(lis)):
  print(*(lis[k]))


  

