n = int(input())
q = list(map(int, input().split()))
a = list(map(int, input().split()))
b = list(map(int, input().split()))

def solve(p):
  #ans = False
  for j in range(p+1):
    flag = True
    for i in range(n):
      if j*a[i]+(p-j)*b[i]>q[i]:
        flag = False
        break
    if flag ==True:
      return True
  return False
  
ok = 0
no = 2*10**6+1
mid = (ok+no)//2
while ok+1<no:
  if solve(mid):
    ok = mid
  else:
    no = mid
  mid = (ok+no)//2
    
print(ok)
      
        

  



  