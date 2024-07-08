h,w,n = map(int,input().split())
a = list(map(int,input().split()))
l = [0 for i in range(26)]
for i in a:
  l[i] += 1
now = 0
for i in range(25,-1,-1):
  ii = 2**i
  now += l[i]*(ii**2)
  if now > (h//ii)*(w//ii)*(ii**2):
    print("No")
    exit()
print("Yes")
  
  
  