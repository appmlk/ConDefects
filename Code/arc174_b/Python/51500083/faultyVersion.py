n = int(input())

for _ in range(n):
  arr = list(map(int, input().split()))
  brr = list(map(int, input().split()))
  
  avg = 0
  sm = sum(arr)
  
  for i in range(5):
    avg += arr[i] * (i+1)
    
  #print(avg / sm)
  
  best_cost = 10**99
  best = 10**99
  l,r = 0, 10**20
  
  for j in range(100):
    mid = (l+r) // 2
    sav = avg + mid * 4
    sam = sm + mid
    
    if sav / sam >= 3:
      l,r = l, mid
      best = min(best, mid)
    else:
      l,r = mid+1, r
      
  
  best_cost = min(best_cost, best * brr[3])
  #print(best_cost, "BEST")
  
  best = 10**99
  l,r = 0, 10**20
  
  for j in range(100):
    mid = (l+r) // 2
    sav = avg + mid * 5
    sam = sm + mid
    
    if sav / sam >= 3:
      l,r = l, mid
      best = min(best, mid)
    else:
      l,r = mid+1, r
      
  
  best_cost = min(best_cost, best * brr[4])
  #print(best_cost, "BEST")
  
  
  
  best = 10**99
  l,r = 0, 10**20
  
  for j in range(100):
    mid = (l+r) // 2
    sav = avg + mid * 5 + 4
    sam = sm + mid + 1
    
    if sav / sam >= 3:
      l,r = l, mid
      best = min(best, mid)
    else:
      l,r = mid+1, r
      
  
  best_cost = min(best_cost, best * brr[4] + brr[3])
  print(best_cost, "BEST")