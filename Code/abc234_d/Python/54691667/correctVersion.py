import heapq

n,k = map(int, input().split())
P = list(map(int, input().split()))
q = P[:k]
heapq.heapify(q)
# print(q)
a = heapq.heappop(q)
prev = a
print(a)
for i in range(k,n):
  heapq.heappush(q, P[i])
  a = heapq.heappop(q)
  if prev < a:
    prev = a
    print(a)
  else:
    print(prev)