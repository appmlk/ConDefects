def f(n):
  return n**2+2*n+3
n=int(input())
print(f(f(f(n)+n))+f(f(n)))