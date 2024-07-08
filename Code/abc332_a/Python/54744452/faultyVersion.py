n,s,k = map(int,input().split())

p,q=[],[]
for _ in range(n):
  a,b = map(int,input().split())
  p.append(a)
  q.append(b)
  
pq = [x*y for x,y in zip(p,q)]
amount = int(sum(pq))
print(amount if amount > s else amount + k)