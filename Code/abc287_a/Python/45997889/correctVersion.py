n = int(input())
count = 0
for i in range(n):
    a = input()
    if a == 'For':
        count= count + 1
    else :
        count=count 
if count > n/2 :
   print('Yes')
else:
    print('No')