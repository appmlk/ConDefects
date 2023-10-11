t = int(input())
for _ in range(t):
  s = input()
  ints = int(s)
  lens = len(s)
  ans = int('9' * (lens-1))
  for length in range(1, len(s)):
    if lens % length != 0:
      continue
    base = s[:length]
    num = int(base * (lens // length))
    while ints < num:
      base = str(int(base) - 1)
      num = int(base * (lens // length))
    ans = max(ans, num)
  print(ans)