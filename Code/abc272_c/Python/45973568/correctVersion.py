N = int(input())
A = [int(x) for x in input().split()]
odd = [-10000000000, -10000000000]
even = [-10000000000, -10000000000]
for ai in A:
  if ai % 2 == 0:
    even.append(ai)
  else:
    odd.append(ai)
odd.sort()
even.sort()
print(max(odd[-1] + odd[-2], even[-1] + even[-2], -1))