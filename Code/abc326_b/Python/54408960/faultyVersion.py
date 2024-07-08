N = int(input())

for n in range(N, 920) :
  s = str(n)
  a = int(s[0])
  b = int(s[1])
  c = int(s[2])
  if a * b == b * c :
    print(n)
    break