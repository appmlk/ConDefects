N, X = map(int, input().split())
S = list(map(int, input().split()))

count  = 0

for s in S:
  if s <= X:
    count += s

print(count)