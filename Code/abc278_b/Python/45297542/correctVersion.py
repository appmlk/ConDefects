def f(h , m) :
  a = h // 10
  b = h % 10
  c = m // 10
  d = m % 10
  h2 = a * 10 + c
  m2 = b * 10 + d
  if 0 <= h2 <= 23 and 0 <= m2 <= 59 :
    print(a * 10 + b , c * 10 + d)
    exit()
    
h , m = map(int,input().split())

while True :
  f(h , m)
  total = h * 60 + m + 1
  h = total // 60 % 24
  m = total % 60