n = int(input())
li = list(map(int,input().split()))

cnt = 0
per = n-2

for i in range(n-1):
  li[i] -= 1

  
while(True):
  if(li[per] == 0):
    cnt += 1
    print(cnt)
    break
  else:
    cnt += 1
    per = li[per]-1
