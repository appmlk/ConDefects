n=int(input())
ans=[i for i in range(1,n+2)]
while True:
  print(ans.pop(0), flush=True)
  tmp=int(input())
  if tmp==0:
    exit()
  else:
    ans.remove(tmp)