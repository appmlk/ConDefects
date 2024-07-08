n,a = list(map(int,input().split()))
T = list(map(int,input().split()))
time = 0
for i in T:
  if (i>=time):
    time=i+a
    print(time)
  else:
    time+=a
    print(time)
  