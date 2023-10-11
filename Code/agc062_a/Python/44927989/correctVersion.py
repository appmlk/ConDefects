n=int(input())
for _ in range(n):
  t=input()
  s=input()
  if('A'==s[-1] or 'BA' in s):
    print('A')
  else:
    print('B')
    