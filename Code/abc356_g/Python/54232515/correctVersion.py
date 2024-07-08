N = int(input())
info = []
for _ in range(N):
  a, b = map(int,input().split())
  info.append([a / b, 1 / b])
  
info.sort(reverse = True)
#print(info)
task = []
section = []
for a, b in info:
  while task:
    p, q = task.pop()
    x, y = section.pop()
    if p == a: continue
    x_new =  (b - q) / (p - a)
    if x >= x_new: continue
    
    y_new = p * x_new + q
    task.append([p, q])
    section.append([x, y])
    break
  
  if not task:
    x_new = 0
    y_new = b
  task.append([a, b])
  section.append([x_new, y_new])
#print(task)

for _ in range(int(input())):
  c, d = map(int,input().split())
  f = c / d
  if f < task[-1][0]:
    print(-1)
    continue
  else:
    lt = -1
    rt = len(task) - 1
    while rt - lt > 1:
      ct = (rt + lt) // 2
      if task[ct][0] <= f:
        rt = ct
      else:
        lt = ct
    x, y = section[rt]
    print(d * y - c * x)
  