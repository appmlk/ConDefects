import sys
input = sys.stdin.readline
from collections import defaultdict
n=int(input())
dic=defaultdict(int)
for _ in range(n):
  a,b=map(int,input().split())
  dic[a]=max(dic[a],b)
ab=[]
for i in dic:
  ab.append((i,dic[i]))
def ConvexHull(point_list):
    pos2idx = {point_list[i]: i for i in range(len(point_list))}
    y_val = defaultdict(list)
    x_list = sorted(list(set([p[0] for p in point_list])))
    for x, y in point_list:
        y_val[x].append(y) 
    upper = [(x_list[0], max(y_val[x_list[0]]))]
    lower = [(x_list[0], min(y_val[x_list[0]]))]
    prev = float('inf')
    for xi in x_list[1:]:
        x0, y0 = upper[-1]
        x1, y1 = xi, max(y_val[xi])
        if (y1 - y0) / (x1 - x0) < prev:
            upper.append((x1, y1))
            prev = (y1 - y0) / (x1 - x0)
        else:
            while True:
                x0, y0 = upper[-1]
                if len(upper) == 1:
                    upper.append((x1, y1))
                    break
                x00, y00 = upper[-2]
                if (y1 - y0) / (x1 - x0) > (y1 - y00) / (x1 - x00):
                    upper.pop()
                else:
                    prev = (y1 - y0) / (x1 - x0)
                    upper.append((x1, y1))
                    break 
    prev = -float('inf')
    for xi in x_list[1:]:
        x0, y0 = lower[-1]
        x1, y1 = xi, min(y_val[xi])
        if (y1 - y0) / (x1 - x0) > prev:
            lower.append((x1, y1))
            prev = (y1 - y0) / (x1 - x0)
        else:
            while True:
                x0, y0 = lower[-1]
                if len(lower) == 1:
                    lower.append((x1, y1))
                    break
                x00, y00 = lower[-2]
                if (y1 - y0) / (x1 - x0) < (y1 - y00) / (x1 - x00):
                    lower.pop()
                else:
                    prev = (y1 - y0) / (x1 - x0)
                    lower.append((x1, y1))
                    break 
    return upper, lower
up,low=ConvexHull(ab)
l=[]
for i in up:
  if not l:
    l.append(i)
    continue
  if l[-1][1]<i[1]:
    l.append(i)
  else:
    break
up=l
l=[]
for i in up:
  if not l:
    l.append(i)
    continue
  while l:
    a1,b1=l[-1]
    a2,b2=i
    if b1*a2<=a1*b2:
      l.pop()
    else:
      break
  l.append(i)
l.reverse()
r=[]
for a,b in l:
  r.append(b/a)
from bisect import bisect_left, bisect_right, insort
q=int(input())
for _ in range(q):
  c,d=map(int,input().split())
  a,b=l[0]
  if b*c>=a*d:
    print(d/b)
    continue
  a,b=l[-1]
  if b*c<a*d:
    print(-1)
    continue
  idx=bisect_left(r,d/c)
  a1,b1=l[idx]
  a2,b2=l[idx-1]
  ans=(b2*c-a2*d-b1*c+a1*d)/(a1*b2-a2*b1)
  print(ans)