h, m = map(int, input().split())
while True:
  if h // 10 * 10 + m // 10 < 24 and h % 10 * 10 + m % 10 < 60:
    exit(print(h, m))
  m += 1
  if m % 60 == 0:
    h += 1
    m = 0
    if h == 24:
      h = 0