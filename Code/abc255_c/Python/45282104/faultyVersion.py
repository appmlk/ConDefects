x, a, d, n = map(int, input().split())
def func(i):
  return d * (i - 1) + a

result = -1
left = 1
right = n
if d == 0:
    result = min(abs(a - x), abs(x - a))
elif d > 0:    
    while left < right:
      m = (left + right) // 2
      if func(m) < x:
         left = m + 1
      else:
         right = m - 1
    """     
    print(left, right)     
    
    print(f"func(left) = {func(left)}")
    print(f"func(right) = {func(right)}")
    """
    if 1 < left < n:
       result = min(min(min(abs(x - func(left)), abs(func(left) - x)), min(abs(x - func(left + 1)), abs(func(left + 1) - x))), min(abs(x - func(left - 1)), abs(func(left - 1) - x)))
    elif left == n:
       result = min(min(abs(x - func(left)), abs(func(left) - x)), min(abs(x - func(left - 1)), abs(func(left - 1) - x)))
    elif left == 1:
       result = min(min(abs(x - func(left)), abs(func(left) - x)), min(abs(x - func(left + 1)), abs(func(left + 1) - x)))
          

elif d < 0:
   while left < right:
      
      m = (left + right) // 2
      if func(m) < x:
         right = m - 1
      else:
         left = m + 1

   if 1 < left < n:
       result = min(min(min(abs(x - func(left)), abs(func(left) - x)), min(abs(x - func(left + 1)), abs(func(left + 1) - x))), min(abs(x - func(left - 1)), abs(func(left - 1) - x)))
   elif left == n:
       result = min(min(abs(x - func(left)), abs(func(left) - x)), min(abs(x - func(left - 1)), abs(func(left - 1) - x)))
   elif left == 1:
       result = min(min(abs(x - func(left)), abs(func(left) - x)), min(abs(x - func(left + 1)), abs(func(left + 1) - x)))
          

   

print(result)
                      