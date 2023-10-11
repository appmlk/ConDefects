n = int(input())
if n%5 ==0:
  print(0)
elif n % 5 >=3:
  print(n+5-n%5)
else:
  print(n-n%5)