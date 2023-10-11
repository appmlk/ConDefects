n = int(input())
m = 2*n

x = []
while n > 4:
  x.append('4')
  n -= 4
if n == 3:
  x.append('3')
if n == 2:
  x.append('2')
if n == 1:
  x.append('1')
x.sort()  
x = ''.join(x)

print(m)
print(x)
  
  