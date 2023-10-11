n = int(input())
a = list(map(int, input().split()))
a.sort()
if a[-1] - a[-2] >= 2:
  print("Alice")
else:
  d = sum(t-s-1 for s,t in zip(a, a[1:])) + a[0]
  print("Bob" if d % 2 == 0 else "Alice")
